package kr.co.MIND.shareList;

import java.util.List;

public interface ShareListDAO {
	// Ķ���� ����� ��ȸ(cid�� Ȯ��)
	public List<ShareListDTO> readUserCal(ShareListDTO dto);

	// Ķ���� ����� �߰�
	public void addUserCal(ShareListDTO dto);
	
	// �ϳ��� Ķ���� ����ڵ� ��ȸ(id�� Ȯ��)
	public List<ShareListDTO> readUserAllCal(ShareListDTO dto);
	
	public ShareListDTO userCheck(ShareListDTO dto);
	
	public int numOfUser(ShareListDTO dto);
	
}
