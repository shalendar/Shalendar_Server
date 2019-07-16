package kr.co.MIND.calendar;

import kr.co.MIND.calendar.CalendarDTO;

public interface CalendarService {
	//Ķ���� ����
	public void createCalendar(CalendarDTO dto);

	//Ķ���� ����
	public void deleteCalendar(CalendarDTO dto);

	//Ķ���� ����
	public void updateCalendar(CalendarDTO dto);

	//Ķ���� ��ȸ (cid)
	public CalendarDTO readCalendar(CalendarDTO dto);
}
