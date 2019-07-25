package kr.co.MIND.calendar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.MIND.calendar.CalendarDTO;

public interface CalendarDAO {
	// Ä¶ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
	public void createCalendar(CalendarDTO dto);
<<<<<<< HEAD
	public void createCalendarImage(Map<String, Object> param);
	
	// Ä¶¸°´õ »èÁ¦
=======

	// Ä¶ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
>>>>>>> d8a685ab49ef6019722fbb944c0f0232721a42c6
	public void deleteCalendar(CalendarDTO dto);

	// Ä¶ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
	public void updateCalendar(CalendarDTO dto);

	// Ä¶ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½È¸
	public CalendarDTO readCalendar(CalendarDTO dto);

	// Ä¶ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½Ã¼ ï¿½ï¿½È¸
	public CalendarDTO readAllCalendar(CalendarDTO dto);
	
	public CalendarDTO getCalInfo(CalendarDTO dto);
}
