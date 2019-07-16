package kr.co.MIND.calendar;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import kr.co.MIND.shareList.*;
@Controller
@RequestMapping
public class CalendarController {

	@Inject
	ShareListService shareListService;
	
	@Inject
	CalendarService calendarService;
	

	// �����޷� ����
	@ResponseBody
	@RequestMapping(value = "/createCal", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public JSONObject createCalendar(@RequestBody CalendarDTO CalendarDTO) {
		JSONObject json = new JSONObject();
		calendarService.createCalendar(CalendarDTO);
		
		// ShareList���� �����޷� �����ڰ� �ڵ����� �߰��Ǿ�� �Ѵ�.
		ShareListDTO dto = new ShareListDTO();
		CalendarDTO result = calendarService.readCalendar(CalendarDTO);
		System.out.println(result.getCid());
		dto.setId(result.getId());
		dto.setCid(result.getCid());
		shareListService.addUserCal(dto);
		json.put("message", "success");
		

		return json;
	}

	// �����޷� ����
	@ResponseBody
	@RequestMapping(value = "/deleteCal", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public Map<String, Object> deleteCalendar(@RequestBody CalendarDTO CalendarDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		calendarService.deleteCalendar(CalendarDTO);
		map.put("message", "success");
		return map;
	}

	// �����޷� ����
	@ResponseBody
	@RequestMapping(value = "/updateCal", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public Map<String, Object> updateCalendar(@RequestBody CalendarDTO CalendarDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		calendarService.updateCalendar(CalendarDTO);
		map.put("message", "success");
		return map;
	}
	
	// �����޷� ��ȸ(cidȮ��)
		@ResponseBody
		@RequestMapping(value = "/readCal", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
		public Map<String, Object> readCalendar(@RequestBody CalendarDTO CalendarDTO) {
			Map<String, Object> map = new HashMap<String, Object>();
			CalendarDTO result = calendarService.readCalendar(CalendarDTO);
			map.put("message", "success");
			map.put("data", result);
			return map;
		}
}
