package kr.co.MIND.shareList;

import java.util.List;

import kr.co.MIND.shareList.ShareListDTO;

public interface ShareListService {
	// 캘린더 사용자 조회
		public List<ShareListDTO> readUserCal(ShareListDTO dto);

		// 캘린더 사용자 추가
		public void addUserCal(ShareListDTO dto);
		
		// 하나의 캘린더 사용자들 조회(id값 확인)
		public List<ShareListDTO> readUserAllCal(ShareListDTO dto);
		
		
}
