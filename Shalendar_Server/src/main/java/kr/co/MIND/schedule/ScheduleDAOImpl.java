package kr.co.MIND.schedule;
import javax.inject.Inject;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ScheduleDAOImpl implements ScheduleDAO {
	
	@Inject
	SqlSessionTemplate	mybatis;
	
	@Override
	public List showAllSchedule(ScheduleDTO dto) {
		// TODO Auto-generated method stub
		
		return mybatis.selectList("schedule.showAllSche",dto.getCid());
		
		
		
	}
	@Override
	public ScheduleDTO showSchedule(ScheduleDTO dto) {
		// TODO Auto-generated method stub
		return mybatis.selectOne("schedule.showSche",dto.getSid());
	}
	@Override
	public void createSchedule(ScheduleDTO dto) {
		// TODO Auto-generated method stub
		
		mybatis.selectOne("schedule.createSche", dto);
		
	}

	@Override
	public void updateSchedule(ScheduleDTO dto) {
		// TODO Auto-generated method stub
		mybatis.selectOne("schedule.updateSche",dto);
		
	}

	@Override
	public void deleteSchedule(ScheduleDTO dto) {
		// TODO Auto-generated method stub
		mybatis.selectOne("schedule.deleteSche",dto);
	}
	@Override
	public List searchSchedule(ScheduleDTO dto) {
		// TODO Auto-generated method stub
		return mybatis.selectList("schedule.searchSche",dto);
	}
	@Override
	public void updateNumOfComments(ScheduleDTO dto) {
		// TODO Auto-generated method stub
		mybatis.selectOne("schedule.updateNumOfComments",dto);
		
	}
	



}
