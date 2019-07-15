package kr.co.MIND.schedule;
import javax.inject.Inject;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ScheduleDAOImpl implements ScheduleDAO {
	
	@Inject
	SqlSessionTemplate	mybatis;
	
	@Override
	public void createSchedule(ScheduleDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSchedule(ScheduleDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSchedule(ScheduleDTO dto) {
		// TODO Auto-generated method stub
		
	}

}
