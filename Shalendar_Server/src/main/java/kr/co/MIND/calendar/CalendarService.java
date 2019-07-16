package kr.co.MIND.calendar;

import kr.co.MIND.calendar.CalendarDTO;

public interface CalendarService {
	//캘린더 생성
	public void createCalendar(CalendarDTO dto);

	//캘린더 삭제
	public void deleteCalendar(CalendarDTO dto);

	//캘린더 수정
	public void updateCalendar(CalendarDTO dto);

	//캘린더 조회 (cid)
	public CalendarDTO readCalendar(CalendarDTO dto);
}
