package kr.co.MIND.calendar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.MIND.member.JwtService;
import kr.co.MIND.shareList.*;

@Controller
@RequestMapping
public class CalendarController {

	@Inject
	ShareListService shareListService;

	@Inject
	CalendarService calendarService;

	@Inject
	JwtService jwtService;

	// 공유달력 생성
	@ResponseBody
	@RequestMapping(value = "/createCal", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public Map<String, Object> createCalendar(@RequestBody CalendarDTO CalendarDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			CalendarDTO.setId(jwtService.getUserID());
			calendarService.createCalendar(CalendarDTO);

			// ShareList에도 공유달력 생성자가 자동으로 추가되어야 한다.
			ShareListDTO dto = new ShareListDTO();
			CalendarDTO result = calendarService.readCalendar(CalendarDTO);
			System.out.println(result.getCid());
			dto.setId(result.getId());
			dto.setCid(result.getCid());
			shareListService.addUserCal(dto);

			map.put("message", "success");
		} catch (RuntimeException e) {
			System.out.println(e);
			map.put("message", "fail");
		}
		return map;
	}

	// 공유달력 삭제
	@ResponseBody
	@RequestMapping(value = "/deleteCal", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public Map<String, Object> deleteCalendar(@RequestBody CalendarDTO CalendarDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			CalendarDTO.setId(jwtService.getUserID());
			calendarService.deleteCalendar(CalendarDTO);
			map.put("message", "success");
		} catch (RuntimeException e) {
			System.out.println(e);
			map.put("message", "fail");
		}
		return map;
	}

	// 공유달력 수정
	@ResponseBody
	@RequestMapping(value = "/updateCal", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public Map<String, Object> updateCalendar(@RequestBody CalendarDTO CalendarDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			CalendarDTO.setId(jwtService.getUserID());
			calendarService.updateCalendar(CalendarDTO);
			map.put("message", "success");
		} catch (RuntimeException e) {
			System.out.println(e);
			map.put("message", "fail");
		}
		return map;
	}

	
	// 공유달력 조회(사이드바 달력들 보여주기)
	// 해당 사용자가 가지고 있는 달력들 조회 (cid,calName,img_url,사용자들)
	@ResponseBody
	@RequestMapping(value = "/readAllCal", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public Map<String, Object> readAllCalendar(@RequestBody CalendarDTO CalendarDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			ShareListDTO dto = new ShareListDTO();
			dto.setId(jwtService.getUserID());
			List<ShareListDTO> resultCid = shareListService.readUserAllCal(dto); 
			List<CalendarDTO> result = new ArrayList<CalendarDTO>();
			CalendarDTO temp = new CalendarDTO();
			int i=0;
			for(ShareListDTO object:resultCid) {
				System.out.println(i++);
				temp.setCid(object.getCid());
				result.add(calendarService.readAllCalendar(temp));
//				System.out.println(result);
			}
			if(!result.isEmpty()) {
				map.put("data", result);
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
}
