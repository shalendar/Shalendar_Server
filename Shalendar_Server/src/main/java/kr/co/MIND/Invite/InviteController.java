package kr.co.MIND.Invite;



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
import kr.co.MIND.shareList.ShareListController;
import kr.co.MIND.shareList.ShareListDTO;

@Controller
@RequestMapping
public class InviteController {
	@Inject
	InviteService inviteService;
	
	@Inject
	JwtService jwtService;
	
	@Inject
	ShareListController shareListControl;
	
	@ResponseBody
	@RequestMapping(value = "/showInvitation", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
		public JSONObject showInvitation() {
			JSONObject json = new JSONObject();
			
			String id = jwtService.getUserID();
			try {
				List<InviteDTO> list = inviteService.showInvitation(id);
				if(list!=null) {
					json.put("invitation", list);
					json.put("message", "success");
				}else {
					json.put("message", "nothing");
				}
				
			}catch(Exception e){
				json.put("message", "fail");
				e.printStackTrace();
			}
			
			
			
		return json;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/acceptInvitation",produces = "application/json;charset=UTF-8",method=RequestMethod.POST)
	public Map<String,Object> acceptInvitation(@RequestBody JSONObject json) {
		Map<String,Object> message = new JSONObject();
		InviteDTO dto = new InviteDTO();
		dto.setCid((int)json.get("cid"));
		dto.setReceiver((String)json.get("receiver"));
		dto.setSender((String)json.get("sender"));
		
		
		int flag = (int)json.get("flag"); // 1 수락 0 거절 
		
		try {
			if(flag==1) {
				ShareListDTO sDto = new ShareListDTO();
//				sDto.setCid(json.get("cid"));
				sDto.setCid(Integer.toString((int)json.get("cid")));
				sDto.setId((String)json.get("receiver"));
				message = shareListControl.addUserCal(sDto,(String)json.get("sender")); // accept or fail 로 return 됨
				
				
			}else {
				message.put("message", "reject");
				
			}
			inviteService.deleteInvitation(dto); // 초대 목록 삭제
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		return message;
	}
}













