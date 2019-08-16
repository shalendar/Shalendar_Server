package kr.co.MIND.Invite;

import java.util.List;

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

	@Override
	public List<InviteDTO> showInvitation(String id) {
		// TODO Auto-generated method stub
		
		return mybatis.selectList("invite.showInvitation",id);
		
	}

	@Override
	public void deleteInvitation(InviteDTO dto) {
		// TODO Auto-generated method stub
		mybatis.selectOne("invite.deleteInvitation", dto);
		return;
	}

}
