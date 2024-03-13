package com.hcm.grw.ctrl.doc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.hcm.grw.dto.doc.SignBoxDto;
import com.hcm.grw.model.service.doc.IDocBoxService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DocController {

	
	@Autowired
	private IDocBoxService docService;
	
	
	@GetMapping("/doc/docBox/getDetail.do")
	public String getDetailBoard(Model model, SignBoxDto dto, String docNum ) {
		
		dto.setSidb_doc_num(docNum);
		List<SignBoxDto> docDto= docService.getDetailDocsList(dto);
		model.addAttribute("docDto",docDto);
		log.info("상세조회  데이터 리스트 결과{}", docDto);
		return "/doc/boardDetail";
	}
	
	
	@GetMapping(value="/doc/docBox.do")
	public String jobs(Model model) {
	log.info("결재함 진입");
	SignBoxDto dto = new SignBoxDto();
	dto.setEmpl_id("20220101");
	List<SignBoxDto> lists = docService.getAllDocs(dto);
	model.addAttribute("lists", lists);
	return "/doc/docBox";
	}
	
	
	
}
