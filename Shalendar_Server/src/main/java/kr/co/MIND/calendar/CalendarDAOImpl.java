package kr.co.MIND.calendar;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CalendarDAOImpl implements CalendarDAO{
	
	@Inject
	SqlSessionTemplate	mybatis;

	//공유 달력 생성
	@Override
	public void createCalendar(CalendarDTO dto) {
		mybatis.insert("calendar.createCal",dto);
	}

	//공유 달력 삭제
	@Override
	public void deleteCalendar(CalendarDTO dto) {
		mybatis.delete("calendar.deleteCal", dto.getCalName());
	}

	//공유 달력 수정
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
