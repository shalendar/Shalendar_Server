package kr.co.MIND.member;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import kr.co.MIND.member.MemberDAOImpl;
import kr.co.MIND.member.MemberDTO;
import kr.co.MIND.shareList.ShareListDAO;
import kr.co.MIND.shareList.ShareListDTO;

@Service("MemberService")
public class MemberServiceImpl implements MemberService {

	@Inject
	MemberDAO memberDao;

	@Inject
	ShareListDAO shareListDao;

	//01. ȸ�� �α��� üũ 
	public boolean loginCheck(MemberDTO dto,HttpServletResponse response) {
		MemberDTO dto2 = memberDao.viewMember(dto);
		if(dto2!=null) {
			return true;
		}else {
			return false;
		}

	}

	@Override
	public MemberDTO viewMember(MemberDTO dto) {
		return memberDao.viewMember(dto);
	}

	@Override
	public void logout(HttpSession session) {
		session.invalidate();
	}

	@Override
	public void joinMember(MemberDTO dto) {
		memberDao.joinMember(dto);
	}
	@Override
	public boolean joinCheck(MemberDTO dto) {
		return memberDao.joinCheck(dto);

	}

	@Override
	public void imageChange(MemberDTO dto) {
		// TODO Auto-generated method stub
		memberDao.imgChange(dto);
	}

	@Override
	public MemberDTO profile(MemberDTO dto) {
		return memberDao.profile(dto);
	}

	//�Ѱ��� cid�� ���� ����� id ��
	@Override
	public List<MemberDTO> readMemCal(ShareListDTO dto) {
		List<ShareListDTO> result = shareListDao.readUserCal(dto);
		List<MemberDTO> mResult = new ArrayList<MemberDTO>();
		MemberDTO mdto = new MemberDTO();
		for(ShareListDTO object:result) {
			mdto.setId(object.getId());
			mResult.add(memberDao.profile(mdto));
		}
		return mResult;
	}

	@Override
	public MemberDTO emailCheck(MemberDTO dto) {
		// TODO Auto-generated method stub
		return memberDao.select(dto);
	}

	@Override
	public String invite(String id) {
		// TODO Auto-generated method stub
		return memberDao.invite(id);
	}
}
