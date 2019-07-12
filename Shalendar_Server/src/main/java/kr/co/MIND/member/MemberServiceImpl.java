package kr.co.MIND.member;


import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import kr.co.MIND.member.MemberDAOImpl;
import kr.co.MIND.member.MemberDTO;

@Service("MemberService")
public class MemberServiceImpl implements MemberService {
	
	@Inject
	MemberDAO memberDao;
	
	//01. ȸ�� �α��� üũ 
	public boolean loginCheck(MemberDTO dto,HttpServletResponse response) {
		MemberDTO dto2 = memberDao.viewMember(dto);
		if(dto2!=null) {
			return true;
		}else {
			return false;
		}

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
