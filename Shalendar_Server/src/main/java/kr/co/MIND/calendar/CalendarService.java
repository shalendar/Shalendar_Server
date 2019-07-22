package kr.co.MIND.calendar;

import java.util.List;

import kr.co.MIND.calendar.CalendarDTO;

public interface CalendarService {
	// Ķ���� ����
	public void createCalendar(CalendarDTO dto);

	// Ķ���� ����
	public void deleteCalendar(CalendarDTO dto);

	// Ķ���� ����
	public void updateCalendar(CalendarDTO dto);

	// Ķ���� ��ȸ (cid)
	public CalendarDTO readCalendar(CalendarDTO dto);

	// Ķ���� ��ü ��ȸ
	public CalendarDTO readAllCalendar(CalendarDTO dto);
	public CalendarDTO getCalInfo(CalendarDTO dto);
}
