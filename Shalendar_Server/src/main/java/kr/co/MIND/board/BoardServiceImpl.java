package kr.co.MIND.board;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import org.springframework.stereotype.Service;

import kr.co.MIND.calendar.CalendarDAOImpl;
import kr.co.MIND.member.MemberDAO;
import kr.co.MIND.member.MemberDTO;
import kr.co.MIND.schedule.ScheduleDTO;

@Service("BoardService")
public class BoardServiceImpl implements BoardService {
	
	@Inject
	BoardDAO boardDao;
	
	@Inject
	MemberDAO memberDao;
	

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
		List<BoardDTO> resultL = new ArrayList<BoardDTO>();
		resultL = boardDao.readComments(dto);
		for(BoardDTO user: resultL) {
			MemberDTO temp = new MemberDTO();
			temp.setId(user.getId());
			
			String img = memberDao.select(temp).getImg_url();
			user.setImg_url(img);
			String name = memberDao.getUserName(user.getId());
			user.setUserName(name);
		}
		return resultL;
	}

	@Override
	public BoardDTO commentCheck(BoardDTO dto) {
		return boardDao.commentCheck(dto);
	}

	@Override
	public List numOfComments(BoardDTO dto) {
		// TODO Auto-generated method stub
			
		return boardDao.numOfComments(dto);
	}
}
