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

@Controller
@RequestMapping
public class CalendarController {

	@Inject
	CalendarService calendarService;

	// 공유달력 생성
	@ResponseBody
	@RequestMapping(value = "/createCal", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public JSONObject createCalendar(@RequestBody CalendarDTO CalendarDTO) {
		JSONObject json = new JSONObject();
		calendarService.createCalendar(CalendarDTO);
		json.put("message", "success");

		return json;
	}

	// 공유달력 삭제
	@ResponseBody
	@RequestMapping(value = "/deleteCal", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public Map<String, Object> deleteCalendar(@RequestBody CalendarDTO CalendarDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		calendarService.deleteCalendar(CalendarDTO);
		map.put("message", "success");
		return map;
	}
	
	// 공유달력 수정
		@ResponseBody
		@RequestMapping(value = "/updateCal", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
		public Map<String, Object> updateCalendar(@RequestBody CalendarDTO CalendarDTO) {
			Map<String, Object> map = new HashMap<String, Object>();
			calendarService.updateCalendar(CalendarDTO);
			map.put("message", "success");
			return map;
		}
}
