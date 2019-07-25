package kr.co.MIND.calendar;

import java.util.List;
import java.util.Map;

import kr.co.MIND.calendar.CalendarDTO;

public interface CalendarService {
	// Ä¶¸°´õ »ý¼º
	public void createCalendar(CalendarDTO dto);
	public void createCalendarImage(byte[] image,CalendarDTO dto);
	
	// Ä¶¸°´õ »èÁ¦
	public void deleteCalendar(CalendarDTO dto);

	// Ä¶¸°´õ ¼öÁ¤
	public void updateCalendar(CalendarDTO dto);

	// Ä¶¸°´õ Á¶È¸ (cid)
	public CalendarDTO readCalendar(CalendarDTO dto);

	// Ä¶¸°´õ ÀüÃ¼ Á¶È¸
	public CalendarDTO readAllCalendar(CalendarDTO dto);
}
