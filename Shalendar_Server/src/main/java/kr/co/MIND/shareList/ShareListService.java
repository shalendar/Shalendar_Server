package kr.co.MIND.shareList;

import java.util.List;

import kr.co.MIND.shareList.ShareListDTO;

public interface ShareListService {
	// Ķ���� ����� ��ȸ
		public List<ShareListDTO> readUserCal(ShareListDTO dto);

		// Ķ���� ����� �߰�
		public void addUserCal(ShareListDTO dto);
}
