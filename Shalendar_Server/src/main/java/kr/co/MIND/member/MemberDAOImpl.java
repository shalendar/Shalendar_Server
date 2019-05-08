package kr.co.MIND.member;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	@Inject
	SqlSessionTemplate	mybatis;
	
	@Override
	public boolean loginCheck(MemberDTO dto, HttpSession session) {
		String name = mybatis.selectOne("member.loginCheck",dto);	
		return (name==null)?false:true;
	}
	
	@Override
	public MemberDTO viewMember(MemberDTO dto) {
		return mybatis.selectOne("member.viewMember", dto);
	}
	
	@Override
	public void logout(HttpSession session) {
		
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
//	public MemberDTO select(MemberDTO dto) {
//		return myBatis.selectOne("mem.select", dto);
//		
//	}
}
