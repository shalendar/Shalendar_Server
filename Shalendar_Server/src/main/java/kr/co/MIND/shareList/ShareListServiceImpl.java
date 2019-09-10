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
		List<ShareListDTO> list = shareListDao.readUserCal(dto);
		for(ShareListDTO object:list) {
			MemberDTO temp = new MemberDTO();
			temp.setId(object.getId());
			String img = memberDao.select(temp).getImg_url();
			object.setImg_url(img);
		}
		return list;
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

	@Override
	public int numOfUser(ShareListDTO dto) {
		// TODO Auto-generated method stub
		return shareListDao.numOfUser(dto);
	}

	@Override
	public void deleteShareUser(ShareListDTO dto) {
		// TODO Auto-generated method stub
		shareListDao.deleteShareUser(dto);
		return;
		
	}

	
	
	
	
	
}
