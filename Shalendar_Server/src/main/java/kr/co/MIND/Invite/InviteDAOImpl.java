package kr.co.MIND.Invite;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InviteDAOImpl implements InviteDAO {

	@Inject
	SqlSessionTemplate	mybatis;
	
	@Override
	public void storeInvitation(InviteDTO dto) {
		// TODO Auto-generated method stub
		mybatis.selectOne("invite.storeInvitation",dto);
		return;
		
	}

}
