package kr.co.MIND.schedule;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import java.util.List;


@Service("ScheduleService")
public class ScheduleServiceImpl implements ScheduleService{
	@Inject
	ScheduleDAO scheduleDao;
	
	@Override
	public List showAllSchedule(ScheduleDTO dto) {
		// TODO Auto-generated method stub
		
		return scheduleDao.showAllSchedule(dto);
	}

	@Override
	public void createSchedule(ScheduleDTO dto) {
		// TODO Auto-generated method stub
		scheduleDao.createSchedule(dto);
		
	}

	@Override
	public void updateSchedule(ScheduleDTO dto) {
		// TODO Auto-generated method stub
		scheduleDao.updateSchedule(dto);
		
	}

	@Override
	public void deleteSchedule(ScheduleDTO dto) {
		// TODO Auto-generated method stub
		scheduleDao.deleteSchedule(dto);
	}

	@Override
	public ScheduleDTO showSchedule(ScheduleDTO dto) {
		// TODO Auto-generated method stub
		return scheduleDao.showSchedule(dto);
	}

	@Override
	public List searchSchedule(ScheduleDTO dto) {
		// TODO Auto-generated method stub
		return scheduleDao.searchSchedule(dto);
	}

}
