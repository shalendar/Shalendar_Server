package kr.co.MIND.shareList;

import java.util.List;

import kr.co.MIND.shareList.ShareListDTO;

public interface ShareListService {
	// Ķ���� ����� ��ȸ
		public List<ShareListDTO> readUserCal(ShareListDTO dto);

		// Ķ���� ����� �߰�
		public void addUserCal(ShareListDTO dto);
		
		// �ϳ��� Ķ���� ����ڵ� ��ȸ(id�� Ȯ��)
		public List<ShareListDTO> readUserAllCal(ShareListDTO dto);
		
		
		
		public ShareListDTO userCheck(ShareListDTO dto);
		
		public int numOfUser(ShareListDTO dto);
		
		
}
