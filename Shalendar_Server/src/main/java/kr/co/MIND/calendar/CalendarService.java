package kr.co.MIND.calendar;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kr.co.MIND.calendar.CalendarDTO;

public interface CalendarService {
	// 캘占쏙옙占쏙옙 占쏙옙占쏙옙
	public void createCalendar(CalendarDTO dto);

	public void createCalendarImage(byte[] image,CalendarDTO dto);
	

	public void deleteCalendar(CalendarDTO dto);

	// 캘占쏙옙占쏙옙 占쏙옙占쏙옙
	public void updateCalendar(CalendarDTO dto);

	// 캘占쏙옙占쏙옙 占쏙옙회 (cid)
	public CalendarDTO readCalendar(CalendarDTO dto);

	// 캘占쏙옙占쏙옙 占쏙옙체 占쏙옙회
	public CalendarDTO readAllCalendar(CalendarDTO dto);
	public CalendarDTO getCalInfo(CalendarDTO dto);
	
	//占쏙옙占쏙옙 占쏙옙琯占� 占쏙옙占쏙옙
	public String upload(MultipartFile file) throws IOException;
}
