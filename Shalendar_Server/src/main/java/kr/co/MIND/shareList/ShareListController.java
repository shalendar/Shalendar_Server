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

@Controller
@RequestMapping
public class ShareListController {

	@Inject
	ShareListService shareListService;

	// 캘린더 사용자 추가
	// cid에 있는 shareList.id들 중에서
	// 캘린더에 초대된 사람들만 만 추가 가능 (token.id == shareList.id)
	@ResponseBody
	@RequestMapping(value = "/addUserCal", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public Map<String, Object> addUserCal(@RequestBody ShareListDTO ShareListDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		shareListService.addUserCal(ShareListDTO);
		map.put("message", "success");
		return map;
	}

	// 캘린더를 사용하고 있는 사용자들을 조회
	@ResponseBody
	@RequestMapping(value = "/readUserCal", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public Map<String, Object> readUserCal(@RequestBody ShareListDTO ShareListDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ShareListDTO> result = shareListService.readUserCal(ShareListDTO);
		if(!result.isEmpty()) {
			map.put("message","success");
			map.put("data", result);
		}else {
			map.put("message", "fail");
		}
		return map;
	}
	
}
