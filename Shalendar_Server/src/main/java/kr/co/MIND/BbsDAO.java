package kr.co.MIND;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BbsDAO {

	@Autowired
	SqlSessionTemplate myBatis;
	
	public List<BbsDTO> selectAll() {
		return myBatis.selectList("bbs.selectAll");
				
	}
	
	public BbsDTO select(BbsDTO dto) {
		return myBatis.selectOne("bbs.select", dto);
	}
	
	
	public void insert(BbsDTO dto) {
		myBatis.insert("bbs.insert", dto);
	}
	
	
	
	
	
	
	
	
}
