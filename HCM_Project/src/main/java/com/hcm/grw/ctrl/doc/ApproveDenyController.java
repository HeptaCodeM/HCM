package com.hcm.grw.ctrl.doc;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcm.grw.dto.doc.SignBoxDto;
import com.hcm.grw.dto.hr.EmployeeDto;
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
	
	@PostMapping(value = "/doc/docBox/approve.do", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<?> approve(@RequestBody SignBoxDto dto, Model model, HttpSession session ) {
		log.info("approve 승인 진입", dto);
		
		//승인할 문서번호와 첨언내용 
		
		// 서명 이미지 임의 지정 (추후 선택방식으로 변경예정)
		
		//세션에서 가져온 결재자  사원 아이디 set
		EmployeeDto Edto = (EmployeeDto)session.getAttribute("userInfoVo");
		dto.setEmpl_id(Edto.getEmpl_id());
		
		//내 appr depth와 max depth 비교해서 최종 여부 판단 
		SignBoxDto depth = docService.getMyDepth(dto);
		System.out.println("나의 결재순서는???"+depth.getAppr_depth()+ "이문서의 결재자 수는?" +depth.getMax_depth());
		
		
		//getMyDepth 메소드로 가져온 나의 결재 순서  appr_depth
			String turn = depth.getAppr_depth();
			int turnInt = Integer.parseInt(turn)-1;
			String string_index = Integer.toString(turnInt);
			dto.setString_index(string_index);
			
		
	    // 내 depth와 max depth 비교해서 어떤 쿼리 실행시킬지 선택
			String apprDepth = depth.getAppr_depth();
			String maxDepth = depth.getMax_depth();	
			
			if (apprDepth.equals(maxDepth)) {
			    apprService.finalApprove(dto);
			} else {
			    apprService.approve(dto);
			}
			List<SignBoxDto> docDto= docService.getDetailDocsList(dto);
			String num = docDto.get(0).getSidb_doc_num();
		return ResponseEntity.ok(num);
	}
	
	@PostMapping(value = "/doc/docBox/deny.do", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<?> deny(@RequestBody SignBoxDto dto, Model model, HttpSession session) {
		log.info("deny 반려 진입", dto);
	
		//세션에서 가져온 결재자  사원 아이디 set
		EmployeeDto Edto = (EmployeeDto)session.getAttribute("userInfoVo");
		dto.setEmpl_id(Edto.getEmpl_id());
		
		//내 appr depth와 max depth 비교해서 최종 여부 판단 
		SignBoxDto depth = docService.getMyDepth(dto);
		System.out.println("나의 결재순서는???"+depth.getAppr_depth()+ "이문서의 결재자 수는?" +depth.getMax_depth());
		
		
		//getMyDepth 메소드로 가져온 나의 결재 순서  appr_depth
			String turn = depth.getAppr_depth();
			int turnInt = Integer.parseInt(turn)-1;
			String string_index = Integer.toString(turnInt);
			dto.setString_index(string_index);
		
			apprService.deny(dto);
		
	    List<SignBoxDto> docDto= docService.getDetailDocsList(dto);
	    String num = docDto.get(0).getSidb_doc_num();
	    
		return ResponseEntity.ok(num);
	}
	
	
	@PostMapping("/doc/docBox/gianCancel.do")
	public String gianCancel(Model model, String docNum, HttpSession session) {
		log.info("상신취소 메소드 진입");
		SignBoxDto dto = new SignBoxDto();
		dto.setSidb_doc_num(docNum);
	
		int n=apprService.gianCancel(dto);
		
		return (n==1)?"redirect:/doc/docBox.do":"redirect:/error403.do";
	}
	}
