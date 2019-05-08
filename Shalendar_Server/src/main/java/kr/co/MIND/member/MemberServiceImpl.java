package kr.co.MIND.member;


import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import kr.co.MIND.member.MemberDAOImpl;
import kr.co.MIND.member.MemberDTO;

@Service("MemberService")
public class MemberServiceImpl implements MemberService {
	
	@Inject
	MemberDAO memberDao;
	
	//01. 회원 로그인 체크 
	public boolean loginCheck(MemberDTO dto,HttpSession session) {
		boolean result = memberDao.loginCheck(dto, session);
		if(result) {
			MemberDTO dto2 = viewMember(dto);
			session.setAttribute("id", dto2.getId());
			session.setAttribute("userName", dto2.getUserName());
		}
		return result;
	}
	
	@Override
	public MemberDTO viewMember(MemberDTO dto) {
		return memberDao.viewMember(dto);
	}
	
	@Override
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
	@Override
	public void joinMember(MemberDTO dto) {
		memberDao.joinMember(dto);
	}
	@Override
	public boolean joinCheck(MemberDTO dto) {
		return memberDao.joinCheck(dto);
		
	}
	
}
