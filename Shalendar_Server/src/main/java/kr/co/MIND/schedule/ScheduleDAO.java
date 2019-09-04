package kr.co.MIND.schedule;
import kr.co.MIND.schedule.ScheduleDTO;
import java.util.List;
public interface ScheduleDAO {
	public List showAllSchedule(ScheduleDTO dto);
	public void createSchedule(ScheduleDTO dto);
	public void updateSchedule(ScheduleDTO dto);
	public void deleteSchedule(ScheduleDTO dto);
	public ScheduleDTO showSchedule(ScheduleDTO dto);
	public List searchSchedule(ScheduleDTO dto);
	
	public void updateNumOfComments(ScheduleDTO dto);
	public List<ScheduleDTO> recommendSche03(int dto);
	
}
