package kr.co.MIND.calendar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.MIND.shareList.ShareListDTO;
import kr.co.MIND.member.JwtService;
import kr.co.MIND.member.MemberDTO;
import kr.co.MIND.member.MemberService;
import kr.co.MIND.shareList.*;
import kr.co.MIND.util.S3Util;
import kr.co.MIND.util.UploadFileUtils;

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

	
	S3Util s3 = new S3Util();
	private String bucketName = "shalendarmind";
	
	// �����޷� ����
	@ResponseBody
	@RequestMapping(value = "/createCal", produces = "application/json;charset=UTF-8", method = RequestMethod.POST, consumes = {
			"multipart/form-data" })
	public Map<String, Object> createCalendar(MultipartFile file, String calName, String calContent) {
		Map<String, Object> map = new HashMap<String, Object>();
		CalendarDTO CalendarDTO = new CalendarDTO();

		try {
			
			String uploadpath = "calendarImage";
			ResponseEntity<String> img_path = new ResponseEntity<>(
					UploadFileUtils.uploadFile(uploadpath, file.getOriginalFilename(), file.getBytes()),
					HttpStatus.CREATED);
			String img_url = (String) img_path.getBody();
			System.out.println(img_url);
			String url = s3.getFileURL(bucketName, uploadpath+img_url);
			
			CalendarDTO.setId(jwtService.getUserID());
//			String url = calendarService.upload(file);
			CalendarDTO.setImg_url(url);
			CalendarDTO.setCalName(calName);
			CalendarDTO.setCalContent(calContent);
			calendarService.createCalendar(CalendarDTO);
//			byte[ ] fileData = file.getBytes();	
//			System.out.println(file.getContentType());

			// ShareList���� �����޷� �����ڰ� �ڵ����� �߰��Ǿ�� �Ѵ�.
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

	// �����޷� ����
	@ResponseBody
	@RequestMapping(value = "/deleteCal", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public Map<String, Object> deleteCalendar(@RequestBody CalendarDTO CalendarDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			CalendarDTO.setId(jwtService.getUserID());
			calendarService.deleteCalendar(CalendarDTO);
			ShareListDTO shDto = new ShareListDTO();
			shDto.setCid(CalendarDTO.getCid());
			shDto.setId(CalendarDTO.getId());
			shareListService.deleteShareUser(shDto);
			map.put("message", "success");
		} catch (RuntimeException e) {
			System.out.println(e);
			map.put("message", "fail");
		}
		return map;
	}

	// �����޷� ����
	@ResponseBody
	@RequestMapping(value = "/updateCal", produces = "application/json;charset=UTF-8", method = RequestMethod.POST, consumes = {
			"multipart/form-data" })
	public Map<String, Object> updateCalendar(MultipartFile file, String cid, String calName, String calContent) {
		Map<String, Object> map = new HashMap<String, Object>();
		CalendarDTO CalendarDTO = new CalendarDTO();
		try {
			String uploadpath = "calendarImage";
			ResponseEntity<String> img_path = new ResponseEntity<>(
					UploadFileUtils.uploadFile(uploadpath, file.getOriginalFilename(), file.getBytes()),
					HttpStatus.CREATED);
			String img_url = (String) img_path.getBody();
			
			String url = s3.getFileURL(bucketName, uploadpath+img_url);

			CalendarDTO.setId(jwtService.getUserID());
			CalendarDTO.setCid(cid);
			CalendarDTO.setCalName(calName);
			CalendarDTO.setCalContent(calContent);
			CalendarDTO.setImg_url(url);

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

	// �����޷� ��ȸ(���̵�� �޷µ� �����ֱ�)
	// �ش� ����ڰ� ������ �ִ� �޷µ� ��ȸ (cid,calName,img_url,����ڵ�)
	// ����ڵ� ������ ������ ��
	@ResponseBody
	@RequestMapping(value = "/readAllCal", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public Map<String, Object> readAllCalendar(@RequestBody CalendarDTO CalendarDTO) throws Exception {
		
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
