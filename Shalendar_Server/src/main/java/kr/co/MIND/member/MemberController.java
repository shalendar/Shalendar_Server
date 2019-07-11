package kr.co.MIND.member;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	
	//ȸ������ ����
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
	
//	@RequestMapping("select.do")
//	public void select(MemberDTO MemberDTO, Model model) {
//		MemberDTO dto = dao.select(MemberDTO);
//		model.addAttribute("dto", dto);
//		System.out.println("id="+MemberDTO.getId());
//		System.out.println("pw="+MemberDTO.getPw());
//		System.out.println("name="+MemberDTO.getUserName());
//	}
	//�α��� ����
	@RequestMapping(value="/signin",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
	public JSONObject login(@RequestBody MemberDTO dto,HttpServletResponse response) {
		JSONObject json = new JSONObject();
		JwtServiceImpl jwt = new JwtServiceImpl();
		boolean result = memberService.loginCheck(dto,response);
		if(result) {
			String token = jwt.create(dto.getId(),dto, "user");
			System.out.println(token);
			if(jwt.isUsable(token)) {
				json.put("message","login success");
				json.put("token", token);
				System.out.println(json);
				return json;
			}
			return json;
		}
		else {
			json.put("message", "wrong password");
			return json;
		}
	}


}






