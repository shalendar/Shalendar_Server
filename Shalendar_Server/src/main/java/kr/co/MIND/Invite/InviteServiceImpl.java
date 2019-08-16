package kr.co.MIND.Invite;

import java.util.List;

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

	@Override
	public List<InviteDTO> showInvitation(String id) {
		// TODO Auto-generated method stub
		
		return inviteDAO.showInvitation(id);
		
	}

	@Override
	public void deleteInvitation(InviteDTO dto) {
		// TODO Auto-generated method stub
		inviteDAO.deleteInvitation(dto);
		return;
	}

}
