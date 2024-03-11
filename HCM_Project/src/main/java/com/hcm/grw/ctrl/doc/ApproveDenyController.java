package com.hcm.grw.ctrl.doc;

import java.util.List;

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
	
	@PostMapping("/doc/approve.do")
	public String approve(@RequestParam("reply") String reply, Model model) {
		log.info("approve 승인 진입", reply);
		SignBoxDto dto = new SignBoxDto();
		dto.setSidb_doc_num("24000003");
		dto.setAppr_reply(reply);
		
		String seq = "6";
		dto.setEmsi_seq(seq);
		
		//로그인세션에서 뜯어온 appr_depth
			String turn = "1";
			int turnInt = Integer.parseInt(turn)-1;
			String string_index = Integer.toString(turnInt);
			dto.setString_index(string_index);
			
		
		
	    boolean isc= apprService.approve(dto);
	    List<SignBoxDto> docDto= docService.getDetailDocsList(dto);
		model.addAttribute("docDto",docDto);
	    
		return "/doc/boardDetail";
	}
	
	@PostMapping("/doc/deny.do")
	public String deny(@RequestParam("reply") String reply, Model model) {
		log.info("deny 반려 진입", reply);
		SignBoxDto dto = new SignBoxDto();
		dto.setSidb_doc_num("24000003");
		dto.setAppr_reply(reply);
		
		//로그인세션에서 뜯어온 appr_depth
	//	String turn = dto.getAppr_depth();
		
		String turn = "2";
		
		//그걸 int로 바꿈
		int turnInt = Integer.parseInt(turn)-1;
		
		//그걸 다시 String으로 바꿈
		String string_index = Integer.toString(turnInt);
		
		//담음
		dto.setString_index(string_index);
		
	    boolean isc= apprService.deny(dto);
	    
		
	    List<SignBoxDto> docDto= docService.getDetailDocsList(dto);
		model.addAttribute("docDto",docDto);
	    
		return "/doc/boardDetail";
	}
}
