package com.hcm.grw.ctrl.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hcm.grw.dto.doc.SignBoxDto;
import com.hcm.grw.model.service.doc.IApprDenyService;
import com.hcm.grw.model.service.doc.IDocBoxService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DocController {

	@Autowired
	private IApprDenyService apprService;
	
	@Autowired
	private IDocBoxService docService;
	
	
	@GetMapping("/doc/getDetail.do")
	public String getDetailBoard(Model model) {
		
		SignBoxDto dto = new SignBoxDto();
		dto.setSidb_doc_num("24000003");
		
		SignBoxDto boardDto= docService.getDetailDocs(dto);
		model.addAttribute("boardDto",boardDto);
		log.info("상세조회  데이터값{}", boardDto);
		return "/doc/boardDetail";
	}
}