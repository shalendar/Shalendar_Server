package kr.co.MIND.Invite;

import java.util.List;

public interface InviteDAO {
	public void storeInvitation(InviteDTO dto);
	public List<InviteDTO> showInvitation(String id);
	public void deleteInvitation(InviteDTO dto);
	
}
