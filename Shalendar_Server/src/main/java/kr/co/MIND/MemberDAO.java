package kr.co.MIND;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberDAO {
	
	@Autowired
	SqlSessionTemplate myBatis;
	
	public void insert(MemberDTO dto) {
		myBatis.insert("mem.insert", dto);
	}
	
}
