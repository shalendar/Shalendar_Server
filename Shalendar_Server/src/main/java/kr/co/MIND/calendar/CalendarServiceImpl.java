package kr.co.MIND.calendar;


import javax.inject.Inject;
import org.springframework.stereotype.Service;

import kr.co.MIND.calendar.CalendarDAOImpl;
import kr.co.MIND.calendar.CalendarDTO;

@Service("CalendarService")
public class CalendarServiceImpl implements CalendarService {
	
	@Inject
	CalendarDAO CalendarDao;

	@Override
	public void createCalendar(CalendarDTO dto) {
		CalendarDao.createCalendar(dto);
		
	}

	@Override
	public void deleteCalendar(CalendarDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCalendar(CalendarDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CalendarDTO searchCalendar(CalendarDTO dto) {
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public CalendarDTO viewCalendar(CalendarDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inviteMember(CalendarDTO dto) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
