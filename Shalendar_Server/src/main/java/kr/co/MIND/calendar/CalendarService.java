package kr.co.MIND.calendar;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kr.co.MIND.calendar.CalendarDTO;

public interface CalendarService {
	// Ä¶ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
	public void createCalendar(CalendarDTO dto);
	public void createCalendarImage(byte[] image,CalendarDTO dto);
	
	// Ä¶¸°´õ »èÁ¦

	// Ä¶ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
	public void deleteCalendar(CalendarDTO dto);

	// Ä¶ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
	public void updateCalendar(CalendarDTO dto);

	// Ä¶ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½È¸ (cid)
	public CalendarDTO readCalendar(CalendarDTO dto);

	// Ä¶ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½Ã¼ ï¿½ï¿½È¸
	public CalendarDTO readAllCalendar(CalendarDTO dto);
	public CalendarDTO getCalInfo(CalendarDTO dto);
	
	//ÆÄÀÏ ¾÷·Îµå ¼­ºñ½º
	public String upload(MultipartFile file) throws IOException;
}
