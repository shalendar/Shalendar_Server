package kr.co.MIND.Invite;

import javax.inject.Inject;

import org.springframework.stereotype.Service;


@Service("InviteService")
public class InviteServiceImpl implements InviteService{

	@Inject
	InviteDAO inviteDAO;
	
	@Override
	public void storeInvitation(InviteDTO dto) {
		// TODO Auto-generated method stub
		inviteDAO.storeInvitation(dto);
		return;
		
	}

}
