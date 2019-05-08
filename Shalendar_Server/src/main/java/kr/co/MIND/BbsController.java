package kr.co.MIND;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BbsController {

	@Autowired
	BbsDAO dao;
	
	
	@RequestMapping("selectAll.do")
	public void selectAll(Model model) {
		List<BbsDTO> list = dao.selectAll();
		model.addAttribute("list", list);
	}
	
	@RequestMapping("select2.do")
	public void select(BbsDTO bbsDTO, Model model) {
		BbsDTO dto = dao.select(bbsDTO);
		model.addAttribute("dto", dto);
	}
	
	@RequestMapping("insert.do")
	public void insert(BbsDTO bbsDTO) {
		dao.insert(bbsDTO);
	}
}
