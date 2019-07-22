package kr.co.MIND.board;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.MIND.schedule.ScheduleDTO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	
	@Inject
	SqlSessionTemplate	mybatis;

	@Override
	public void createComments(BoardDTO dto) {
		mybatis.insert("board.createComments",dto);
		
	}

	@Override
	public void deleteComments(BoardDTO dto) {
		mybatis.delete("board.deleteComments",dto);		
	}

	@Override
	public void updateComments(BoardDTO dto) {
		mybatis.update("board.updateComments",dto);		
	}

	@Override
	public List<BoardDTO> readComments(BoardDTO dto) {
		return mybatis.selectList("board.readAllComments", dto);
	}

	@Override
	public BoardDTO commentCheck(BoardDTO dto) {
		return mybatis.selectOne("board.commentCheck",dto);
	}

	@Override
	public List numOfComments(BoardDTO dto) {
		// TODO Auto-generated method stub
		
		return mybatis.selectList("board.numOfComments",dto);
	}
}
