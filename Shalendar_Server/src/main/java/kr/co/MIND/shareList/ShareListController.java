package kr.co.MIND.shareList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.MIND.member.JwtService;
import kr.co.MIND.member.MemberDTO;
import kr.co.MIND.member.MemberService;

@Controller
@RequestMapping
public class ShareListController {

	@Inject
	ShareListService shareListService;
	@Inject
	MemberService memberService;
	@Inject
	JwtService jwtService;

	// Ķ���� ����� �߰�
	// cid�� �ִ� shareList.id�� �߿���
	// Ķ������ �ʴ�� ����鸸 �� �߰� ���� (token.id == shareList.id)
	@ResponseBody
	@RequestMapping(value = "/addUserCal", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public Map<String, Object> addUserCal(@RequestBody ShareListDTO ShareListDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			MemberDTO mdto = new MemberDTO();
			mdto.setId(ShareListDTO.getId());
			ShareListDTO dto = new ShareListDTO();
			ShareListDTO dto2 = new ShareListDTO();
			dto.setId(jwtService.getUserID());
			dto.setCid(ShareListDTO.getCid());
			dto2 = shareListService.userCheck(dto);
			boolean joinCheck = memberService.joinCheck(mdto);
			System.out.println(joinCheck);
			if(dto2!=null && !joinCheck) {
				shareListService.addUserCal(ShareListDTO);
				map.put("message", "success");
			}else {
				map.put("message", "fail");
			}
		} catch (RuntimeException e) {
			System.out.println(e);
			map.put("message", "fail");
		}
		
		return map;
	}
	// Ķ������ ����ϰ� �ִ� ����� ��ȸ ����
	// Ķ������ ����ϰ� �ִ� ����ڵ��� ��ȸ
	@ResponseBody
	@RequestMapping(value = "/readUserCal", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public Map<String, Object> readUserCal(@RequestBody ShareListDTO ShareListDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			ShareListDTO dto = new ShareListDTO();
			ShareListDTO dto2 = new ShareListDTO();
			dto.setId(jwtService.getUserID());
			dto.setCid(ShareListDTO.getCid());
			dto2 = shareListService.userCheck(dto);
			if(dto2!=null) {
				List<ShareListDTO> result = shareListService.readUserCal(ShareListDTO);
				if(!result.isEmpty()) {
					map.put("message","success");
					map.put("data", result);
				}else {
					map.put("message", "fail");
				}
			}
		} catch (RuntimeException e) {
			System.out.println(e);
			map.put("message", "fail");
		}
		
		
		
		
		
		return map;
	}
	
}
