package kr.co.MIND.calendar;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CalendarDAOImpl implements CalendarDAO{
	
	@Inject
	SqlSessionTemplate	mybatis;

	//���� �޷� ����
	@Override
	public void createCalendar(CalendarDTO dto) {
		mybatis.insert("calendar.createCal",dto);
	}

	//���� �޷� ����
	@Override
	public void deleteCalendar(CalendarDTO dto) {
		mybatis.delete("calendar.deleteCal", dto);
	}

	//���� �޷� ����
	@Override
	public void updateCalendar(CalendarDTO dto) {
		mybatis.update("calendar.updateCal", dto);
		
	}

	@Override
	public CalendarDTO readCalendar(CalendarDTO dto) {
		return mybatis.selectOne("calendar.readCal",dto);
	}

	@Override
	public CalendarDTO readAllCalendar(CalendarDTO dto) {
		return mybatis.selectOne("calendar.readAllCal",dto);
	}

	@Override
	public CalendarDTO getCalInfo(CalendarDTO dto) {
		// TODO Auto-generated method stub
		return mybatis.selectOne("calendar.getCalInfo",dto);
	}



	
	
	

}
