package kr.co.MIND.shareList;

import java.io.IOException;
import java.util.ArrayList;
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

import kr.co.MIND.Invite.InviteService;
import kr.co.MIND.member.JwtService;
import kr.co.MIND.member.MemberDTO;
import kr.co.MIND.member.MemberService;
import kr.co.MIND.schedule.ScheduleDTO;
import kr.co.MIND.Invite.InviteDTO;

@Controller
@RequestMapping
public class ShareListController {

	@Inject
	ShareListService shareListService;
	@Inject
	MemberService memberService;
	@Inject
	JwtService jwtService;
	@Inject
	InviteService inviteService;
	
	
	
	
	// Ķ���� ����� �߰�
	// cid�� �ִ� shareList.id�� �߿���
	// Ķ������ �ʴ�� ����鸸 �� �߰� ���� (token.id == shareList.id)
	// �̹� �ʴ�Ǿ��ִ»���� �߰� x
	
	//서버키 
	//AAAAhPVesjc:APA91bGb7MLL_jj5JRWh7rV3BWlCk_ZVBi2erViQXB4xU3BHALwmXKMMslB4VU7P-nnVao5it35zZ-5r6J26SrkcVeUPqn2gGIHJJFp_1hm3w0gSqi4gsp5T3TN5DmDvmxwbw0STA-V3
	//sender ID
	//571052306999
	@ResponseBody
	@RequestMapping(value = "/pushInvitation", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
		public JSONObject pushInvitation(@RequestBody JSONObject json) throws IOException {
			
			PostInvitation push = new PostInvitation();
			
			List <String> li= (List<String>) json.get("receiver");
		
		   ArrayList<String> deviceToken = new ArrayList<String>(); // 회원가입시 받아온 device Token
			for(int i=0;i<li.size();i++) {
				deviceToken.add(memberService.invite(li.get(i)));
			}
			for(int i=0;i<li.size();i++) { //초대 테이블에 저장 
				InviteDTO invDto = new InviteDTO();
				invDto.setCid((int) json.get("cid"));
				invDto.setcName((String)json.get("cName"));
				invDto.setReceiver(li.get(i));
				invDto.setSender((String)json.get("sender"));
				invDto.setSender_img((String)json.get("sender_img"));
				invDto.setSenderName((String)json.get("senderName"));
				inviteService.storeInvitation(invDto);
			}
			JSONObject send = new JSONObject();
			try {
				push.push(deviceToken,(String)json.get("senderName"),(String)json.get("cName"));
				send.put("message","success");
			}catch(IOException e) {
				send.put("message", "fail");
				e.printStackTrace();
			}
			
			
			return send;
		
	}
	
	public Map<String, Object> addUserCal(ShareListDTO ShareListDTO,String sender) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			MemberDTO mdto = new MemberDTO();
			mdto.setId(ShareListDTO.getId());  //초대 대상 receiver
			ShareListDTO dto = new ShareListDTO();
			ShareListDTO dto2 = new ShareListDTO();
			ShareListDTO dto3 = new ShareListDTO();
			dto.setId(sender); // 보낸 사람 sender 
			dto.setCid(ShareListDTO.getCid());
			dto2 = shareListService.userCheck(dto);
			dto3 = shareListService.userCheck(ShareListDTO);
			boolean joinCheck = memberService.joinCheck(mdto);
			System.out.println(joinCheck);
			if(dto2!=null && dto3==null && !joinCheck) {
				shareListService.addUserCal(ShareListDTO);
				map.put("message", "accept");
			}else {
				map.put("message", "fail");
			}
		} catch (RuntimeException e) {
			System.out.println("@@@@");
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
