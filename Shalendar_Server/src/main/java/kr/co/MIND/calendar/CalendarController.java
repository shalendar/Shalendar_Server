package kr.co.MIND.calendar;

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
	
	
	//회원가입 로직
	@ResponseBody
	@RequestMapping(value="/createCal",produces="application/json;charset=UTF-8", method=RequestMethod.POST)
	public JSONObject createCalendar(@RequestBody CalendarDTO CalendarDTO) {
		JSONObject json = new JSONObject();
		calendarService.createCalendar(CalendarDTO);
		json.put("message", "success");
		
		return json;
	}

}






