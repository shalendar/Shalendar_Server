package kr.co.MIND.calendar;


import java.io.File;
import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.MIND.calendar.CalendarDAOImpl;
import kr.co.MIND.calendar.CalendarDTO;
import java.util.UUID;

@Service("CalendarService")
public class CalendarServiceImpl implements CalendarService {
	
	@Inject
	CalendarDAO CalendarDao;

	private static final String UPLOAD_PATH = "C:\\Graduation\\uploadFile\\";
	//���� �޷� ����
	@Override
	public void createCalendar(CalendarDTO dto) {
		CalendarDao.createCalendar(dto);
		
	}
	@Override
	public void createCalendarImage(byte[] image,CalendarDTO dto){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("img_url",image);
		param.put("id", dto.getId());
		param.put("cid", dto.getCid());
		System.out.println(param);
		CalendarDao.createCalendarImage(param);
		
	}

	//���� �޷� ����
	@Override
	public void deleteCalendar(CalendarDTO dto) {
		CalendarDao.deleteCalendar(dto);
	}

	//���� �޷� ����
	@Override
	public void updateCalendar(CalendarDTO dto) {
		CalendarDao.updateCalendar(dto);
		
	}

	@Override
	public CalendarDTO readCalendar(CalendarDTO dto) {
		return CalendarDao.readCalendar(dto);
	}

	@Override
	public CalendarDTO readAllCalendar(CalendarDTO dto) {
		return CalendarDao.readAllCalendar(dto);
	}

	@Override
	public CalendarDTO getCalInfo(CalendarDTO dto) {
		// TODO Auto-generated method stub
		return CalendarDao.getCalInfo(dto);
	}
	@Override
	public String upload(MultipartFile file) throws IOException{
		String orgName = file.getOriginalFilename();
		String url;
		try {
			String ext = orgName.substring(orgName.lastIndexOf('.'));
			
			String saveFileName = getUuid() + ext;
			
			File uploadFile = new File(UPLOAD_PATH,saveFileName);
			file.transferTo(uploadFile);
			url = UPLOAD_PATH+saveFileName;
			
		} catch (StringIndexOutOfBoundsException e) {
			url = null;
		}
		return url;
	}
	
	public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
	
	
}
