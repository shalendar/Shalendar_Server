package kr.co.MIND.calendar;

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
		mybatis.delete("calendar.deleteCal", dto.getCalName());
	}

	//���� �޷� ����
	@Override
	public void updateCalendar(CalendarDTO dto) {
		mybatis.update("calendar.updateCal", dto);
		
	}

	@Override
	public CalendarDTO searchCalendar(CalendarDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

}
