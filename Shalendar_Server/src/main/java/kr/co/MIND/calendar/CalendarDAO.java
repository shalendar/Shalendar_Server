package kr.co.MIND.calendar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.MIND.calendar.CalendarDTO;

public interface CalendarDAO {
	// Ä¶ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
	public void createCalendar(CalendarDTO dto);
	public void createCalendarImage(Map<String, Object> param);
	
	// Ä¶¸°´õ »èÁ¦

	public void deleteCalendar(CalendarDTO dto);

	// Ä¶ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
	public void updateCalendar(CalendarDTO dto);

	// Ä¶ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½È¸
	public CalendarDTO readCalendar(CalendarDTO dto);

	// Ä¶ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½Ã¼ ï¿½ï¿½È¸
	public CalendarDTO readAllCalendar(CalendarDTO dto);
	
	public CalendarDTO getCalInfo(CalendarDTO dto);
}
