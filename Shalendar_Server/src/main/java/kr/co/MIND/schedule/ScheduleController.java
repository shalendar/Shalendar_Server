package kr.co.MIND.schedule;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;


import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import kr.co.MIND.member.JwtService;
import kr.co.MIND.shareList.ShareListDTO;
import kr.co.MIND.shareList.ShareListService;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping
public class ScheduleController {
	@Inject
	JwtService jwtservice;
	
	@Inject
	ScheduleService scheduleService;
	
	@Inject
	ShareListService shareListService;

	@ResponseBody
	@RequestMapping(value="/showAllSche",produces="application/json;charset=UTF-8", method=RequestMethod.POST)
	public JSONObject showAllSche(@RequestBody ScheduleDTO dto) {
		JSONObject json = new JSONObject();
		List result = scheduleService.showAllSchedule(dto);
		if(!result.isEmpty()) {
			json.put("data", result);
			json.put("message","success");
			
		}else {
			json.put("message", "no schedule");
		}
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value="/showSche",produces="application/json;charset=UTF-8", method=RequestMethod.POST)
	public JSONObject showSche(@RequestBody ScheduleDTO dto) {
		JSONObject json = new JSONObject();
		ScheduleDTO result = scheduleService.showSchedule(dto);
		if(result!=null) {
			json.put("data", result);
			json.put("message","success");
			
		}else {
			json.put("message", "no schedule");
		}
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value="/createSche",produces="application/json;charset=UTF-8", method=RequestMethod.POST)
	public JSONObject createSche(@RequestBody ScheduleDTO dto) {
		JSONObject json = new JSONObject();
		try {
			dto.setId(jwtservice.getUserID());
			scheduleService.createSchedule(dto);
			json.put("message", "success");
		}catch(RuntimeException e) {
			System.out.println(e);
			json.put("message", "fail");
		}
		
		return json;
	}
	@ResponseBody
	@RequestMapping(value="/deleteSche",produces="application/json;charset=UTF-8", method=RequestMethod.POST)
	public JSONObject deleteSche(@RequestBody ScheduleDTO dto) {
		JSONObject json = new JSONObject();
		try {
			scheduleService.deleteSchedule(dto);
			json.put("message", "success");
		}catch(RuntimeException e) {
			System.out.println(e);
			json.put("message", "fail");
		}
		
		return json;
	}
	@ResponseBody
	@RequestMapping(value="/updateSche",produces="application/json;charset=UTF-8", method=RequestMethod.POST)
	public JSONObject updateSche(@RequestBody ScheduleDTO dto) {
		JSONObject json = new JSONObject();
		try {
			scheduleService.updateSchedule(dto);
			json.put("message", "success");
		}catch(RuntimeException e) {
			System.out.println(e);
			json.put("message", "fail");
		}
		
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value="/searchSche",produces="application/json;charset=UTF-8", method=RequestMethod.POST)
	public JSONObject searchSche(@RequestBody ScheduleDTO dto) {
		
		JSONObject json = new JSONObject();
		dto.setId(jwtservice.getUserID());
		List result = scheduleService.searchSchedule(dto);
		if(!result.isEmpty()) {
			json.put("data", result);
			json.put("message","success");
			
		}else {
			json.put("message", "no schedule");
		}
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value="/recommendSche",produces="application/json;charset=UTF-8", method=RequestMethod.POST)
	public JSONObject recommendSche(@RequestBody ScheduleDTO dto) throws NumberFormatException, ParseException {
		JSONObject json = new JSONObject();
		List result = scheduleService.recommendSche(dto);
		if(!result.isEmpty()) {
			json.put("data", result);
			json.put("message","success");
			
		}else {
			json.put("message", "null");
		}
		
		return json;
	}
}
