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

	// Ķ���� ����� �߰�
	// cid�� �ִ� shareList.id�� �߿���
	// Ķ������ �ʴ�� ����鸸 �� �߰� ���� (token.id == shareList.id)
	@ResponseBody
	@RequestMapping(value = "/addUserCal", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public Map<String, Object> addUserCal(@RequestBody ShareListDTO ShareListDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		shareListService.addUserCal(ShareListDTO);
		map.put("message", "success");
		return map;
	}

	// Ķ������ ����ϰ� �ִ� ����ڵ��� ��ȸ
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
