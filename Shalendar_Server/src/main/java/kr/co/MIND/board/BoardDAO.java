package kr.co.MIND.board;

import java.util.List;

public interface BoardDAO {
	// ��� ����
	public void createComments(BoardDTO dto);

	// ��� ����
	public void deleteComments(BoardDTO dto);

	// ��� ����
	public void updateComments(BoardDTO dto);

	// ��� ��ȸ (cid,sid)
	public List<BoardDTO> readComments(BoardDTO dto);
	
	public BoardDTO commentCheck(BoardDTO dto);

}
