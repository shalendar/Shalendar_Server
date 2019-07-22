package kr.co.MIND.board;

import java.util.List;
import java.util.Map;

import kr.co.MIND.board.BoardDTO;

public interface BoardService {
	// ��� ����
	public void createComments(BoardDTO dto);

	// ��� ����
	public void deleteComments(BoardDTO dto);

	// ��� ����
	public void updateComments(BoardDTO dto);

	// ��� ��ȸ (cid,sid)
	public List<BoardDTO> readComments(BoardDTO dto);
	
	public BoardDTO commentCheck(BoardDTO dto);
	
	public Map<String,Integer> numOfComments(BoardDTO dto);
}
