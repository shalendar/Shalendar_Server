package kr.co.MIND.calendar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.MIND.calendar.CalendarDTO;

public interface CalendarDAO {
	// Ķ���� ����
	public void createCalendar(CalendarDTO dto);
<<<<<<< HEAD
	public void createCalendarImage(Map<String, Object> param);
	
	// Ķ���� ����
=======

	// Ķ���� ����
>>>>>>> d8a685ab49ef6019722fbb944c0f0232721a42c6
	public void deleteCalendar(CalendarDTO dto);

	// Ķ���� ����
	public void updateCalendar(CalendarDTO dto);

	// Ķ���� ��ȸ
	public CalendarDTO readCalendar(CalendarDTO dto);

	// Ķ���� ��ü ��ȸ
	public CalendarDTO readAllCalendar(CalendarDTO dto);
	
	public CalendarDTO getCalInfo(CalendarDTO dto);
}
