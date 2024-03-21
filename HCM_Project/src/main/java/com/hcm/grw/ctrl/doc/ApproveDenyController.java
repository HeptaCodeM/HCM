package com.hcm.grw.ctrl.doc;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.hcm.grw.dto.doc.SignBoxDto;
import com.hcm.grw.dto.doc.SignJsonDto;
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
	
	@PostMapping("/doc/docBox/approve.do")
	public String approve(String reply, Model model, String docNum,HttpSession session ) {
		log.info("approve 승인 진입", reply);
		
		//승인할 문서번호와 첨언내용 
		SignBoxDto dto = new SignBoxDto();
		dto.setSidb_doc_num(docNum);
		dto.setAppr_reply(reply);
		
		// 서명 이미지 임의 지정 (추후 선택방식으로 변경예정)
		String seq = "6";
		dto.setEmsi_seq(seq);
		
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
		model.addAttribute("docDto",docDto);
	    
		return "/doc/docBox/boardDetail/boardDetail";
	}
	
	@PostMapping("/doc/docBox/deny.do")
	public String deny(@RequestParam("reply") String reply, Model model, String docNum, HttpSession session) {
		log.info("deny 반려 진입", reply);
		SignBoxDto dto = new SignBoxDto();
		dto.setSidb_doc_num(docNum);
		dto.setAppr_reply(reply);
	
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
		model.addAttribute("docDto",docDto);
	    
		return "/doc/docBox/boardDetail/boardDetail";
	}
	
	
	@PostMapping("/doc/docBox/gianCancel.do")
	public String gianCancel(Model model, String docNum, HttpSession session, HttpServletResponse response) {
		log.info("상신취소 메소드 진입");
		SignBoxDto dto = new SignBoxDto();
		dto.setSidb_doc_num(docNum);
	
		//세션에서 가져온 결재자  사원 아이디 set
		EmployeeDto Edto = (EmployeeDto)session.getAttribute("userInfoVo");
		dto.setEmpl_id(Edto.getEmpl_id());
		
		int n=apprService.gianCancel(dto);
		
		
		//return (n==1)?"/doc/docBox/docBox":"/doc/docBox/boardDetail/boardDetail";
		return (n==1)?"redirect:/doc/docBox.do":"redirect:/doc/docBox.do";
	}
	}
