package kr.co.MIND.image;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import kr.co.MIND.calendar.CalendarDTO;

public interface ImageService {
	
	public String uploadFile(String uploadePath,String originalName, byte[] fileData) throws Exception;
	public String makeIcon(String uploadPath, String path, String fileName) throws Exception;
	public String makeThumbnail(String uploadPath, String path, String fileName) throws Exception;
	public String calcPath(String uploadPath);
	public void makeDir(String uploadPath, String... paths);
	public MediaType getMediaType(String formatName);
	
}
