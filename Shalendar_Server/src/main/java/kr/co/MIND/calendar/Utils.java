package kr.co.MIND.calendar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import org.springframework.web.multipart.MultipartFile;

public class Utils {
	public static byte[] imageToByteArray(File file) throws Exception{
		byte[] returnValue = null;
		
		ByteArrayOutputStream baos = null;
		FileInputStream fis = null;
		
		try {
			baos = new ByteArrayOutputStream();
			fis = new FileInputStream(file);
			
			byte[] buf = new byte[1024];
			int read = 0;
			while((read=fis.read(buf,0,buf.length))!= -1) {
				baos.write(buf,0,read);
			}
			returnValue = baos.toByteArray();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(baos != null) {
				baos.close();
			}
			if(fis!=null) {
				fis.close();
			}
		}
		return returnValue;
	}
}
