package kr.co.MIND.calendar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@Override
	public void createCalendarImage(Map<String, Object> param) {
		mybatis.update("calendar.createCalImage",param);
		
	}

	//공유 달력 삭제
	@Override
	public void deleteCalendar(CalendarDTO dto) {
		mybatis.delete("calendar.deleteCal", dto);
	}

	//공유 달력 수정
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

	



	
	
	

}
