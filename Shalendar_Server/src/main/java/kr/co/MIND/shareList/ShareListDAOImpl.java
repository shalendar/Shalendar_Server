package kr.co.MIND.shareList;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.MIND.schedule.ScheduleDTO;

@Repository
public class ShareListDAOImpl implements ShareListDAO{
	
	@Inject
	SqlSessionTemplate	mybatis;

	@Override
	public List<ShareListDTO> readUserCal(ShareListDTO dto) {
		List<ShareListDTO> result;
		result = mybatis.selectList("shareList.readUserCal", dto);
		return result;
	}

	@Override
	public void addUserCal(ShareListDTO dto) {
		mybatis.insert("shareList.addUserCal", dto);
	}

	@Override
	public List<ShareListDTO> readUserAllCal(ShareListDTO dto) {
		List<ShareListDTO> result;
		result = mybatis.selectList("shareList.readUserAllCal", dto);
		return result;
	}

	@Override
	public ShareListDTO userCheck(ShareListDTO dto) {
		return mybatis.selectOne("shareList.userCheck", dto);
	}

	@Override
	public int numOfUser(ShareListDTO dto) {
		// TODO Auto-generated method stub
		return mybatis.selectOne("shareList.numOfUser",dto);
	}

	@Override
	public void deleteShareUser(ShareListDTO dto) {
		// TODO Auto-generated method stub
		mybatis.selectOne("shareList.deleteUser",dto);
	}
	
	@Override
	public List<String> recommendSche01(ScheduleDTO dto) {
		List<String> result = mybatis.selectList("shareList.recommendSche01", dto); 
		return result;
	}
	
	@Override
	public List<Integer> recommendSche02(String id) {
		List<Integer> result = mybatis.selectList("shareList.recommendSche02", id); 
		return result;
	}


	
	

}
