package kr.co.MIND.calendar;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import org.springframework.stereotype.Service;

import kr.co.MIND.calendar.CalendarDAOImpl;
import kr.co.MIND.calendar.CalendarDTO;

@Service("CalendarService")
public class CalendarServiceImpl implements CalendarService {
	
	@Inject
	CalendarDAO CalendarDao;

	//공유 달력 생성
	@Override
	public void createCalendar(CalendarDTO dto) {
		CalendarDao.createCalendar(dto);
		
	}
	@Override
	public void createCalendarImage(byte[] image,CalendarDTO dto){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("img_url",image);
		param.put("id", dto.getId());
		param.put("cid", dto.getCid());
		System.out.println(param);
		CalendarDao.createCalendarImage(param);
		
	}

	//공유 달력 삭제
	@Override
	public void deleteCalendar(CalendarDTO dto) {
		CalendarDao.deleteCalendar(dto);
	}

	//공유 달력 수정
	@Override
	public void updateCalendar(CalendarDTO dto) {
		CalendarDao.updateCalendar(dto);
		
	}

	@Override
	public CalendarDTO readCalendar(CalendarDTO dto) {
		return CalendarDao.readCalendar(dto);
	}

	@Override
	public CalendarDTO readAllCalendar(CalendarDTO dto) {
		return CalendarDao.readAllCalendar(dto);
	}
	
	
	
	
}
