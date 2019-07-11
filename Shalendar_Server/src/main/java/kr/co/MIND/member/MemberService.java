package kr.co.MIND.member;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.MIND.member.MemberDTO;

public interface MemberService {
	public boolean loginCheck(MemberDTO dto, HttpServletResponse response);
	public MemberDTO viewMember(MemberDTO dto);
	public void logout(HttpSession session);
	public void joinMember(MemberDTO dto);
	public boolean joinCheck(MemberDTO dto);
}
