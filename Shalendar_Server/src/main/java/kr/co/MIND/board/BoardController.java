package kr.co.MIND.board;

import java.util.HashMap;
import java.util.Iterator;
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
import kr.co.MIND.member.MemberService;
import kr.co.MIND.schedule.ScheduleDTO;
import kr.co.MIND.schedule.ScheduleService;
import kr.co.MIND.calendar.CalendarService;
import kr.co.MIND.calendar.CalendarDTO;
import kr.co.MIND.shareList.ShareListService;
import kr.co.MIND.shareList.ShareListDTO;
@Controller
@RequestMapping
public class BoardController {

	@Inject
	JwtService jwtService;

	@Inject
	BoardService boardService;
	
	@Inject
	CalendarService calendarService;
	@Inject
	ShareListService shareListService;
	@Inject
	ScheduleService scheduleService;
	
	@Inject
	MemberService memberService;

	// ��� ����
	// cid, sid, token(id), ��۳����� ���� ������� �Ѵ�.
	@ResponseBody
	@RequestMapping(value = "/createComments", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public Map<String, Object> createComments(@RequestBody BoardDTO BoardDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BoardDTO.setId(jwtService.getUserID());
			boardService.createComments(BoardDTO);
			
			map.put("message", "success");
		} catch (RuntimeException e) {
			System.out.println(e);
			map.put("message", "fail");
		}
		return map;
	}

	// ��� ����
	// cid, sid, token(id)�� ���� ������� �Ѵ�.
	@ResponseBody
	@RequestMapping(value = "/updateComments", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public Map<String, Object> updateComments(@RequestBody BoardDTO BoardDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BoardDTO.setId(jwtService.getUserID());
			BoardDTO check = new BoardDTO();
			check = boardService.commentCheck(BoardDTO);
			if(check !=null) {
				boardService.updateComments(BoardDTO);
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

	// ��� ����
	// cid, sid, token(id)�� ���� ������� �Ѵ�.

	@ResponseBody
	@RequestMapping(value = "/deleteComments", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public Map<String, Object> deleteComments(@RequestBody BoardDTO BoardDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BoardDTO.setId(jwtService.getUserID());
			BoardDTO check = new BoardDTO();
			check = boardService.commentCheck(BoardDTO);
			if(check !=null) {
				boardService.deleteComments(BoardDTO);
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

	// ��� ��ü ��ȸ
	// cid, sid, token(id)�� ���� ������� �Ѵ�.
	@ResponseBody
	@RequestMapping(value = "/readComments", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public Map<String, Object> readComments(@RequestBody BoardDTO BoardDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BoardDTO.setId(jwtService.getUserID());
			List<BoardDTO> result = boardService.readComments(BoardDTO);

			map.put("data", result);
			map.put("message", "success");
			
		} catch (RuntimeException e) {
			System.out.println(e);
			map.put("message", "fail");
		}
		return map;
	}
	@ResponseBody
	@RequestMapping(value="/initBoard",produces="application/json;charset=UTF-8", method=RequestMethod.POST)
	public JSONObject initBoard(@RequestBody BoardDTO dto) {
		JSONObject json = new JSONObject();
		CalendarDTO cdto = new CalendarDTO();
		cdto.setCid(dto.getCid());
		cdto = calendarService.getCalInfo(cdto); // calName,calContent,img_url 
		
		ShareListDTO ddto = new ShareListDTO();
		ddto.setCid(dto.getCid());
		int cnt = shareListService.numOfUser(ddto); // 해당 달력 공유 사람 수 	
		
		//sid ,numOfComments 
		List<ScheduleDTO>sdto=boardService.numOfComments(dto);
		
		for(ScheduleDTO object:sdto) {
			scheduleService.updateNumOfComments(object);
		}
		json.put("shareUserData",memberService.readMemCal(ddto));

		
		ScheduleDTO sdto2 = new ScheduleDTO();
		int temp = Integer.parseInt(dto.getCid());
		sdto2.setCid(temp);
		json.put("calendarData", cdto);
		json.put("sharePeopleNum", cnt);
		json.put("scheduleData", scheduleService.showAllSchedule(sdto2));
		
		if(scheduleService.showAllSchedule(sdto2)==null) {
			json.put("message","no schedule");
			
		}else {
			json.put("message", "success");
		}
		return json;
	}
}
