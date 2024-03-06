package com.hcm.grw.ctrl.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.hcm.grw.dto.doc.SignBoxDto;
import com.hcm.grw.dto.doc.SignJsonDto;
import com.hcm.grw.model.service.doc.IApprDenyService;
import com.hcm.grw.model.service.doc.IDocBoxService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ApproveDenyController {

	
	@Autowired
	private IApprDenyService apprService;
	@Autowired
	private IDocBoxService docService;
	
	@PostMapping("/doc/getDetail.do")
	public String approve(@RequestParam("reply") String reply) {
		log.info("approve 승인 진입", reply);
		SignBoxDto dto = new SignBoxDto();
		dto.setSidb_doc_num("24000003");
		dto.setAppr_reply(reply);
		
	    boolean isc= apprService.approve(dto);
	    
		return "/doc/boardDetail";
	}
	
	@PostMapping("/doc/deny.do")
	public String deny(@RequestParam("reply") String reply, Model model) {
		log.info("deny 반려 진입", reply);
		SignBoxDto dto = new SignBoxDto();
		dto.setSidb_doc_num("24000003");
		dto.setAppr_reply(reply);
		
	    boolean isc= apprService.deny(dto);
	    
		
		SignBoxDto docDto= docService.getDetailDocs(dto);
		model.addAttribute("docDto",docDto);
	    
		return "/doc/boardDetail";
	}
}
