package kr.co.MIND.shareList;

import java.util.List;

public interface ShareListDAO {
	// Ķ���� ����� ��ȸ
	public List<ShareListDTO> readUserCal(ShareListDTO dto);

	// Ķ���� ����� �߰�
	public void addUserCal(ShareListDTO dto);

}
