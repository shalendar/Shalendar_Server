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

	// 댓글 생성
	// cid, sid, token(id), 댓글내용을 같이 보내줘야 한다.
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

	// 댓글 수정
	// cid, sid, token(id)을 같이 보내줘야 한다.
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

	// 댓글 삭제
	// cid, sid, token(id)을 같이 보내줘야 한다.

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

	// 댓글 전체 조회
	// cid, sid, token(id)을 같이 보내줘야 한다.
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
