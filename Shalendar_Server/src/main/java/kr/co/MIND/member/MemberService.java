package kr.co.MIND.member;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.MIND.member.MemberDTO;
import kr.co.MIND.shareList.ShareListDTO;

public interface MemberService {
	public MemberDTO loginCheck(MemberDTO dto, HttpServletResponse response);
	public MemberDTO viewMember(MemberDTO dto);
	public void logout(HttpSession session);
	public void joinMember(MemberDTO dto);
	public boolean joinCheck(MemberDTO dto);
	public void imageChange(MemberDTO dto);
	public MemberDTO profile(MemberDTO dto);
	
	public MemberDTO emailCheck(MemberDTO dto);
	
	public String invite(String id);
	
	public List<MemberDTO> readMemCal(ShareListDTO dto);
	public void setDeviceToken(MemberDTO dto);
	public String getUserName(String id);
}
