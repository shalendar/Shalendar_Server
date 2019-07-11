package kr.co.MIND.calendar;

import kr.co.MIND.calendar.CalendarDTO;

public interface CalendarService {
	//Ķ���� ����
	public void createCalendar(CalendarDTO dto);

	//Ķ���� ����
	public void deleteCalendar(CalendarDTO dto);

	//Ķ���� ����
	public void updateCalendar(CalendarDTO dto);

	//Ķ���� �̸����� ��ȸ
	public CalendarDTO searchCalendar(CalendarDTO dto);

	//Ķ���� ��� ��ȸ
	public CalendarDTO viewCalendar(CalendarDTO dto);

	//Ķ���� ����� �߰�
	public void inviteMember(CalendarDTO dto);
}
