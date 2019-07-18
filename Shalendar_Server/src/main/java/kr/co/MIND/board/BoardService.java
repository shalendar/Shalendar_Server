package kr.co.MIND.board;

import java.util.List;

import kr.co.MIND.board.BoardDTO;

public interface BoardService {
	// ´ñ±Û »ý¼º
	public void createComments(BoardDTO dto);

	// ´ñ±Û »èÁ¦
	public void deleteComments(BoardDTO dto);

	// ´ñ±Û ¼öÁ¤
	public void updateComments(BoardDTO dto);

	// ´ñ±Û Á¶È¸ (cid,sid)
	public List<BoardDTO> readComments(BoardDTO dto);
	
	public BoardDTO commentCheck(BoardDTO dto);

}
