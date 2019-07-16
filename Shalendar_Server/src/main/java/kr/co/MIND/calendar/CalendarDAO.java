package kr.co.MIND.calendar;

import kr.co.MIND.calendar.CalendarDTO;

public interface CalendarDAO {
	//Ķ���� ����
	public void createCalendar(CalendarDTO dto);

	//Ķ���� ����
	public void deleteCalendar(CalendarDTO dto);

	//Ķ���� ����
	public void updateCalendar(CalendarDTO dto);
	
	//Ķ���� ��ȸ
	public CalendarDTO readCalendar(CalendarDTO dto);
}
