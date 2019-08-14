package kr.co.MIND.calendar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.MIND.image.ImageService;
import kr.co.MIND.member.JwtService;
import kr.co.MIND.member.MemberDTO;
import kr.co.MIND.member.MemberService;
import kr.co.MIND.shareList.*;

@Controller
@RequestMapping
public class CalendarController {

	@Inject
	ShareListService shareListService;

	@Inject
	CalendarService calendarService;

	@Inject
	MemberService memberService;

	@Inject
	JwtService jwtService;

	@Inject
	ImageService imageService;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	// 공유달력 생성
	@ResponseBody
	@RequestMapping(value = "/createCal", produces = "application/json;charset=UTF-8", method = RequestMethod.POST,consumes = {"multipart/form-data"})
	public Map<String, Object> createCalendar(MultipartFile file,String calName, String calContent) {
		Map<String, Object> map = new HashMap<String, Object>();
		CalendarDTO CalendarDTO = new CalendarDTO();
		
		try {
			ResponseEntity<String> img_path = new ResponseEntity<>(
					imageService.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.CREATED);
			String user_imgPath = (String) img_path.getBody();
			
			CalendarDTO.setId(jwtService.getUserID());
//			String url = calendarService.upload(file);
			CalendarDTO.setImg_url(user_imgPath);
			CalendarDTO.setCalName(calName);
			CalendarDTO.setCalContent(calContent);
			calendarService.createCalendar(CalendarDTO);
//			byte[ ] fileData = file.getBytes();	
//			System.out.println(file.getContentType());
			

			// ShareList에도 공유달력 생성자가 자동으로 추가되어야 한다.
			ShareListDTO dto = new ShareListDTO();
			CalendarDTO result = calendarService.readCalendar(CalendarDTO);
			
			System.out.println(result.getCid());
			dto.setId(result.getId());
			dto.setCid(result.getCid());
			shareListService.addUserCal(dto);
			
//			calendarService.createCalendarImage(fileData,result);
			map.put("message", "success");
		} catch (RuntimeException e) {
			System.out.println(e);
			map.put("message", "fail");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	// 공유달력 삭제
	@ResponseBody
	@RequestMapping(value = "/deleteCal", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public Map<String, Object> deleteCalendar(@RequestBody CalendarDTO CalendarDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			CalendarDTO.setId(jwtService.getUserID());
			calendarService.deleteCalendar(CalendarDTO);
			map.put("message", "success");
		} catch (RuntimeException e) {
			System.out.println(e);
			map.put("message", "fail");
		}
		return map;
	}

	// 공유달력 수정
	@ResponseBody
	@RequestMapping(value = "/updateCal", produces = "application/json;charset=UTF-8", method = RequestMethod.POST,consumes = {"multipart/form-data"})
	public Map<String, Object> updateCalendar(MultipartFile file,String cid,String calName, String calContent) {
		Map<String, Object> map = new HashMap<String, Object>();
		CalendarDTO CalendarDTO = new CalendarDTO();
		try {
			ResponseEntity<String> img_path = new ResponseEntity<>(
					imageService.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.CREATED);
			String user_imgPath = (String) img_path.getBody();
			
			CalendarDTO.setId(jwtService.getUserID());
			CalendarDTO.setCid(cid);
			CalendarDTO.setCalName(calName);
			CalendarDTO.setCalContent(calContent);
			CalendarDTO.setImg_url(user_imgPath);
			
			calendarService.updateCalendar(CalendarDTO);
			map.put("message", "success");
		} catch (RuntimeException e) {
			System.out.println(e);
			map.put("message", "fail");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	// 공유달력 조회(사이드바 달력들 보여주기)
	// 해당 사용자가 가지고 있는 달력들 조회 (cid,calName,img_url,사용자들)
	// 사용자들 정보도 보내줄 것
	@ResponseBody
	@RequestMapping(value = "/readAllCal", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public Map<String, Object> readAllCalendar(@RequestBody CalendarDTO CalendarDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			ShareListDTO dto = new ShareListDTO();
			dto.setId(jwtService.getUserID());
			List<ShareListDTO> resultCid = shareListService.readUserAllCal(dto);
			List<List<MemberDTO>> profile = new ArrayList<List<MemberDTO>>();
			List<CalendarDTO> result = new ArrayList<CalendarDTO>();
//			List<MemberDTO> profile = new ArrayList<MemberDTO>();
			CalendarDTO temp = new CalendarDTO();
			MemberDTO mTemp = new MemberDTO();
			int i = 0;
			for (ShareListDTO object : resultCid) {
				temp.setCid(object.getCid());
				mTemp.setId(object.getId());
				result.add(calendarService.readAllCalendar(temp));
				profile.add(memberService.readMemCal(object));
			}
//			profile.add(memberService.profile(dto))
			if (!result.isEmpty()) {
				map.put("data", result);
				map.put("data2", profile);
				map.put("message", "success");
			} else {
				map.put("message", "fail");
			}

		} catch (RuntimeException e) {
			System.out.println(e);
			map.put("message", "fail");
		}
		return map;
	}
}
