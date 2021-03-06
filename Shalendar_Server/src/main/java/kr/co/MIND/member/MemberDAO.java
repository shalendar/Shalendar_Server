package kr.co.MIND.member;



import javax.servlet.http.HttpServletResponse;


import kr.co.MIND.member.MemberDTO;

public interface MemberDAO {
	public boolean loginCheck(MemberDTO dto, HttpServletResponse response);
	public MemberDTO viewMember(MemberDTO dto);
	public void logout(HttpServletResponse response);
	public void joinMember(MemberDTO dto);
	public boolean joinCheck(MemberDTO dto);
	public void imgChange(MemberDTO dto);
	public MemberDTO select(MemberDTO dto);
	public MemberDTO profile(MemberDTO dto);
	public void setDeviceToken(MemberDTO dto);
	
	public String getUserName(String id);
	
	//사용자 초대 전 푸쉬 알림 
	public String invite(String id);
	
}
