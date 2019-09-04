package kr.co.MIND.shareList;

import java.util.List;

import kr.co.MIND.schedule.ScheduleDTO;

public interface ShareListDAO {
	// 캘占쏙옙占쏙옙 占쏙옙占쏙옙占� 占쏙옙회(cid占쏙옙 확占쏙옙)
	public List<ShareListDTO> readUserCal(ShareListDTO dto);

	// 캘占쏙옙占쏙옙 占쏙옙占쏙옙占� 占쌩곤옙
	public void addUserCal(ShareListDTO dto);
	
	// 占싹놂옙占쏙옙 캘占쏙옙占쏙옙 占쏙옙占쏙옙湄占� 占쏙옙회(id占쏙옙 확占쏙옙)
	public List<ShareListDTO> readUserAllCal(ShareListDTO dto);
	
	public ShareListDTO userCheck(ShareListDTO dto);
	
	public int numOfUser(ShareListDTO dto);
	
	public void deleteShareUser(ShareListDTO dto);

	// 추천알고리즘 1번째. 해당 달력에 대한 사용자 리스트를 받아온다.
	public List<String> recommendSche01(ScheduleDTO dto);
	
	public List<Integer> recommendSche02(String id);

	
	
}
