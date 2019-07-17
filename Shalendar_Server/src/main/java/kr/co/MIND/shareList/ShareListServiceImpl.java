package kr.co.MIND.shareList;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Service;

import kr.co.MIND.calendar.CalendarDAOImpl;
import kr.co.MIND.calendar.CalendarDTO;
import kr.co.MIND.member.MemberDAO;
import kr.co.MIND.member.MemberDTO;

@Service("ShareListService")
public class ShareListServiceImpl implements ShareListService {
	
	@Inject
	ShareListDAO shareListDao;
	
	@Inject
	MemberDAO memberDao;


	@Override
	public List<ShareListDTO> readUserCal(ShareListDTO dto) {
		return shareListDao.readUserCal(dto);
	}

	@Override
	public void addUserCal(ShareListDTO dto) {
		shareListDao.addUserCal(dto);
		
	}

	@Override
	public List<ShareListDTO> readUserAllCal(ShareListDTO dto) {
		return shareListDao.readUserAllCal(dto);
	}

	@Override
	public ShareListDTO userCheck(ShareListDTO dto) {
		return shareListDao.userCheck(dto);
	}

	
	
	
	
	
}
