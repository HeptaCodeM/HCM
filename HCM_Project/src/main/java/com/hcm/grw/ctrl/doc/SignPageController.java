package com.hcm.grw.ctrl.doc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("doc/")
public class SignPageController {
	
	@GetMapping("write.do")
	public String write() {
		log.info("SignTreeController write.do GET 문서작성 페이지");
		return "doc/write";
	}
	
	@GetMapping("signFavo.do")
	public String signFavo() {
		log.info("SignTreeController signFavo.do GET 결재선 관리 페이지");
		return "doc/signFavo";
	}
	
	@GetMapping("signRefer.do")
	public String signRefer() {
		log.info("SignTreeController signRefer.do GET 참조 등록 페이지");
		return "doc/signRefer";
	}
}
