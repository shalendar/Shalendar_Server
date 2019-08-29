	package kr.co.MIND.member;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.MIND.member.MemberDTO;
import kr.co.MIND.member.MemberService;
import kr.co.MIND.util.S3Util;
import kr.co.MIND.util.UploadFileUtils;

@Controller
@RequestMapping
public class MemberController {

	@Inject
	MemberService memberService;

	@Inject
	JwtService jwt;
	//ȸ������ ����
	
	S3Util s3 = new S3Util();
	private String bucketName = "shalendarmind";
	
	@ResponseBody
	@RequestMapping(value="/emailCheck",produces="application/json;charset=UTF-8", method=RequestMethod.POST)
	public JSONObject emailCheck(@RequestBody MemberDTO memberDTO) {
		JSONObject json = new JSONObject();
		MemberDTO tempDto=memberService.emailCheck(memberDTO);
		
		if(tempDto==null) {
			json.put("message", "available");
		}else {
			json.put("message", "please check email");
			json.put("id", tempDto.getId());
			json.put("img_url", tempDto.getImg_url());
			
		}

		return json;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/signup",produces="application/json;charset=UTF-8", method=RequestMethod.POST)
	public JSONObject joinMember(@RequestBody MemberDTO memberDTO) {
		JSONObject json = new JSONObject();
		boolean result = memberService.joinCheck(memberDTO);

		if(result == true) {
			memberService.joinMember(memberDTO);
			json.put("message","success");
		}else {
			json.put("message", "please check email");
		}
		return json;
	}

	@ResponseBody
	@RequestMapping(value="/signin",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
	public JSONObject login(@RequestBody MemberDTO dto,HttpServletResponse response) {
		JSONObject json = new JSONObject();
		MemberDTO result = memberService.loginCheck(dto,response);
		if(result!=null) {
			
			memberService.setDeviceToken(dto);
			String token = jwt.create("userID",dto, "User");
			System.out.println(token);
			response.setHeader("Authorization", token);
			json.put("message","login success");
			json.put("token", token);
			System.out.println(json);
			
			if(jwt.isUsable(token)) {
				json.put("message","login success");
				json.put("token", token);
				json.put("userName", result.getUserName());
				json.put("img_url", result.getImg_url());
				System.out.println(json);
			}
			return json;


		}
		else {
			json.put("message", "wrong password");
			return json;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/imageChange", produces = "application/json;charset=UTF-8", method = RequestMethod.POST, consumes = {
	"multipart/form-data" })
	public JSONObject imageChange(MultipartFile file) {
		if(file==null) System.out.println("@@@@@@@@@@@");
		System.out.println("file@@"+file);
		JSONObject json = new JSONObject();
		MemberDTO dto = new MemberDTO();
		try {
			String uploadpath = "profileImage";
			ResponseEntity<String> img_path = new ResponseEntity<>(
					UploadFileUtils.uploadFile(uploadpath, file.getOriginalFilename(), file.getBytes()),
					HttpStatus.CREATED);
			String img_url = (String) img_path.getBody();
			System.out.println(img_url);
			String url = s3.getFileURL(bucketName, uploadpath+img_url);
			String id = jwt.getUserID();
			dto.setId(id);
			dto.setImg_url(url);
			memberService.imageChange(dto);
			json.put("message", "image change success");
		}catch(RuntimeException e) {
			System.out.println(e);
			json.put("message","image change fail");
		}catch(Exception e) {
			System.out.println(e);
		}

		
		return json;
	}
}






