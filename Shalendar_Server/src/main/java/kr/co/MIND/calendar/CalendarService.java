package kr.co.MIND.calendar;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kr.co.MIND.calendar.CalendarDTO;

public interface CalendarService {
	// Ķ���� ����
	public void createCalendar(CalendarDTO dto);
	public void createCalendarImage(byte[] image,CalendarDTO dto);
	
	// Ķ���� ���

	// Ķ���� ����

	public void deleteCalendar(CalendarDTO dto);

	// Ķ���� ����
	public void updateCalendar(CalendarDTO dto);

	// Ķ���� ��ȸ (cid)
	public CalendarDTO readCalendar(CalendarDTO dto);

	// Ķ���� ��ü ��ȸ
	public CalendarDTO readAllCalendar(CalendarDTO dto);
	public CalendarDTO getCalInfo(CalendarDTO dto);
	
	//���� ��ε� ����
	public String upload(MultipartFile file) throws IOException;
}
