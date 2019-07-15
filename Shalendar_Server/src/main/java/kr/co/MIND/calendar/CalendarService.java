package kr.co.MIND.calendar;

import kr.co.MIND.calendar.CalendarDTO;

public interface CalendarService {
	//캘린더 생성
	public void createCalendar(CalendarDTO dto);

	//캘린더 삭제
	public void deleteCalendar(CalendarDTO dto);

	//캘린더 수정
	public void updateCalendar(CalendarDTO dto);

	//캘린더 이름으로 조회
	public CalendarDTO searchCalendar(CalendarDTO dto);

	//캘린더 목록 조회
	public CalendarDTO viewCalendar(CalendarDTO dto);

	//캘린더 사용자 추가
	public void inviteMember(CalendarDTO dto);
}
