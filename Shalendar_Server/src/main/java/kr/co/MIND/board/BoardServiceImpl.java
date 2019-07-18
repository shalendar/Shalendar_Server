package kr.co.MIND.board;


import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Service;

import kr.co.MIND.calendar.CalendarDAOImpl;
import kr.co.MIND.calendar.CalendarDTO;

@Service("BoardService")
public class BoardServiceImpl implements BoardService {
	
	@Inject
	BoardDAO boardDao;

	@Override
	public void createComments(BoardDTO dto) {
		boardDao.createComments(dto);
	}

	@Override
	public void deleteComments(BoardDTO dto) {
		boardDao.deleteComments(dto);
	}

	@Override
	public void updateComments(BoardDTO dto) {
		boardDao.updateComments(dto);
	}

	@Override
	public List<BoardDTO> readComments(BoardDTO dto) {
		return boardDao.readComments(dto);
	}

	@Override
	public BoardDTO commentCheck(BoardDTO dto) {
		return boardDao.commentCheck(dto);
	}
}
