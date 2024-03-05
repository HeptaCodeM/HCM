package com.hcm.grw.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcm.grw.dto.doc.TemplateDto;
import com.hcm.grw.model.service.doc.ITemplateService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value = "doc")
public class TemplateController {
	
	@Autowired
	private ITemplateService service;

	@GetMapping("/template.do")
	public String template(Model model) {
		log.info("TemplateController 템플릿 전체 값을 저장하여 이동하는 template");
		List<TemplateDto> lst = service.getTempList();
		model.addAttribute("lst",lst);
		return "doc/template";
	}
	
	@GetMapping("/detailTemplate.do")
	public String detailTempate(String sidt_temp_cd, Model model) {
		log.info("TemplateController 템플릿 상세 내용 조회하는 detailTemplate");
		TemplateDto temDto = service.getDetailTemp(sidt_temp_cd);
		model.addAttribute("temDto",temDto);
		return "doc/detailTemplate";
	}
	
	@GetMapping("/writeTemplate.do")
	public String writeTemplate() {
		log.info("TemplateController 템플릿 등록화면으로 이동하는 writeTemplate");		
		return "doc/writeTemplate";
	}
	
	
	@GetMapping("/modifyTemplate.do")
	public String modifyTemplate() {
		log.info("TemplateController 템플릿 수정화면으로 이동하는 modifyTemplate");
		return "doc/modifyTemplate";
	}
	
	@GetMapping("/deleteTemplate.do")
	public String deleteTemplate(@RequestParam(name = "sidt_temp_cd") String sidt_temp_cd) {
		log.info("TemplateController 템플릿 삭제하는 deleteTemplate");
		int n = service.deleteTemp(sidt_temp_cd);
		return "redirect:template.do";
		
	}
	
}
