package com.hcm.grw.ctrl.doc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hcm.grw.dto.hr.EmpSignDto;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.model.service.doc.ISignFavoService;
import com.hcm.grw.model.service.hr.EmpSignService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("doc/")
public class SignPageController {
	
	@Autowired
	private ISignFavoService service;
	
	@Autowired
	private EmpSignService signService;
	
	@GetMapping("write.do")
	public String write() {
		log.info("SignTreeController write.do GET 문서작성 페이지");
		return "doc/write";
	}
	
	@GetMapping("signFavo.do")
	public String signFavo() {
		log.info("SignTreeController signFavo.do GET 결재선 관리 페이지");
		return "doc/approverSet/signFavo";
	}
	
	@GetMapping("writeDoc/signRefer.do")
	public String signRefer() {
		log.info("SignTreeController signRefer.do GET 참조 등록 페이지");
		return "doc/writeDoc/signRefer/signRefer";
	}
	
	@GetMapping("writeDoc.do")
	public String writeDoc(Authentication auth, Model model) {
		String id = auth.getName();
		List<String> list = List.of(id);
		List<EmployeeDto> dto = service.getFav(list);
		model.addAttribute("loginInfo", dto.get(0));
		return "doc/writeDoc";
	}
	
	@GetMapping("fileTest.do")
	public String fileTest() {
		return "doc/fileTest";
	}
	
	@GetMapping("signManagement.do")
	public String signManagement(Model model, HttpSession session) {
		log.info("SignTreeController signManagement.do GET 서명 관리 페이지");
		EmployeeDto dto = (EmployeeDto)session.getAttribute("userInfoVo");
		if(dto == null) {
			return "doc/signManagement/signListForm";
		}
		Map<String, Object> signMap = new HashMap<String, Object>();
		signMap.put("empl_id", dto.getEmpl_id());
		List<EmpSignDto> signList = signService.selectAllSign(signMap);
		model.addAttribute("signList",signList);
		return "doc/signManagement/signListForm";
	}
	
}