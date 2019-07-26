package kr.co.MIND.member;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.MIND.member.MemberDTO;
import kr.co.MIND.member.MemberService;

@Controller
@RequestMapping
public class MemberController {

	@Inject
	MemberService memberService;

	@Inject
	JwtService jwt;
	//ȸ������ ����
	@ResponseBody
	@RequestMapping(value="/emailCheck",produces="application/json;charset=UTF-8", method=RequestMethod.POST)
	public JSONObject emailCheck(@RequestBody MemberDTO memberDTO) {
		JSONObject json = new JSONObject();
		
		
		if(memberService.emailCheck(memberDTO)==null) {
			json.put("message", "available");
		}else {
			json.put("message", "please check email");
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
		boolean result = memberService.loginCheck(dto,response);
		if(result) {

			String token = jwt.create("userID",dto, "User");
			System.out.println(token);
			response.setHeader("Authorization", token);
			json.put("message","login success");
			json.put("token", token);
			System.out.println(json);
			
			if(jwt.isUsable(token)) {
				json.put("message","login success");
				json.put("token", token);
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
	@RequestMapping(value="/imageChange",produces="application/json;charset=UTF-8", method=RequestMethod.POST)
	public JSONObject imageChange(@RequestBody MemberDTO dto) {
		JSONObject json = new JSONObject();
		try {
			String id = jwt.getUserID();
			dto.setId(id);
			memberService.imageChange(dto);
			json.put("message", "image change success");
		}catch(RuntimeException e) {
			System.out.println(e);
			json.put("message","image change fail");
		}

		
		return json;
	}
}






