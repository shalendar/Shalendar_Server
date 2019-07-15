package kr.co.MIND.member;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.MIND.member.MemberDTO;

public interface MemberDAO {
	public boolean loginCheck(MemberDTO dto, HttpServletResponse response);
	public MemberDTO viewMember(MemberDTO dto);
	public void logout(HttpServletResponse response);
	public void joinMember(MemberDTO dto);
	public boolean joinCheck(MemberDTO dto);
	
}
