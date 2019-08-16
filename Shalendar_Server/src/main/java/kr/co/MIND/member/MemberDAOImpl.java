package kr.co.MIND.member;



import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	@Inject
	
	SqlSessionTemplate	mybatis;
	
	@Override
	public boolean loginCheck(MemberDTO dto, HttpServletResponse response) {
		String name = mybatis.selectOne("member.loginCheck",dto);	
		return (name==null)?false:true;
	}
	
	@Override
	public MemberDTO viewMember(MemberDTO dto) {
		return mybatis.selectOne("member.viewMember", dto);
	}
	
	@Override
	public void logout(HttpServletResponse response) {
		
	}
	@Override
	public void joinMember(MemberDTO dto) {
		mybatis.insert("member.joinMember",dto);
	}
	
	@Override
	public boolean joinCheck(MemberDTO dto) {
		String name = mybatis.selectOne("member.joinCheck",dto);	
		return (name==null)?true:false;
	}
	
	@Override
	public MemberDTO select(MemberDTO dto) {
		return mybatis.selectOne("member.select", dto);
		
	}

	@Override
	public void imgChange(MemberDTO dto) {
		// TODO Auto-generated method stub
		mybatis.selectOne("member.updateImg_url",dto);
		
	}

	@Override
	public MemberDTO profile(MemberDTO dto) {
		return mybatis.selectOne("member.select",dto);
	}

	@Override
	public String invite(String id) {
		// TODO Auto-generated method stub
		return mybatis.selectOne("member.invite",id);
		
	}
}
