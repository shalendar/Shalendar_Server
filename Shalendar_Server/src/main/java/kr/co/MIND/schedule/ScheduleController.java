package kr.co.MIND.schedule;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;


import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping
public class ScheduleController {
	
	@Inject
	ScheduleService scheduleService;


	@ResponseBody
	@RequestMapping(value="/showAllSche",produces="application/json;charset=UTF-8", method=RequestMethod.POST)
	public JSONObject showAllSche(@RequestBody ScheduleDTO dto) {
		JSONObject json = new JSONObject();
		List result = scheduleService.showAllSchedule(dto);
		if(!result.isEmpty()) {
			json.put("message","success");
			json.put("data", result);
		}else {
			json.put("message", "no schedule");
		}
		return json;
	}

}
