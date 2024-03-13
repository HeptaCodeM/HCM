package com.hcm.grw.ctrl.doc;

import java.util.List;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.model.service.doc.ISignFavoService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("doc/")
public class SignPageController {
	
	@Autowired
	private ISignFavoService service;
	
	@GetMapping("write.do")
	public String write() {
		log.info("SignTreeController write.do GET 문서작성 페이지");
		return "doc/write";
	}
	
	@GetMapping("signFavo.do")
	public String signFavo(Authentication auth, Model model) {
		log.info("SignTreeController signFavo.do GET 결재선 관리 페이지");
		if(auth != null) {
			List<String> list = List.of(auth.getName());
			List<EmployeeDto> loginInfo = service.getFav(list);
			model.addAttribute("loginInfo", loginInfo.get(0));
		}
		return "doc/signFavo";
	}
	
	@GetMapping("signRefer.do")
	public String signRefer() {
		log.info("SignTreeController signRefer.do GET 참조 등록 페이지");
		return "doc/signRefer";
	}
	
	@GetMapping("writeDoc.do")
	public String writeDoc(Authentication auth, Model model) {
		if(auth != null) {
			String id = auth.getName();
			List<String> list = List.of(id);
			List<EmployeeDto> dto = service.getFav(list);
			model.addAttribute("loginInfo", dto.get(0));
		}
		return "doc/writeDoc";
	}
}
