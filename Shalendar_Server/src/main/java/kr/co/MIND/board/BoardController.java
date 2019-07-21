package kr.co.MIND.board;

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

@Controller
@RequestMapping
public class BoardController {

	@Inject
	JwtService jwtService;

	@Inject
	BoardService boardService;

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
}
