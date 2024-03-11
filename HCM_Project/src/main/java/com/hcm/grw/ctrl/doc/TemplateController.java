package com.hcm.grw.ctrl.doc;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@GetMapping("/selectCategory.do")
	@ResponseBody
	public ResponseEntity<?> selectCategory() {
		log.info("TemplateController 템플릿 카테고리 조회하는 selectCategory");
		List<TemplateDto> list = service.getCategory();
		return ResponseEntity.ok(list);
	}
	
	@PostMapping("/insertTemplate.do")
	public String insertTemplate(@ModelAttribute TemplateDto dto) {
		log.info("TemplateController 템플릿 등록하는 insertTemplate");
		int n = service.insertTemp(dto);
		return (n==0)? "" : "redirect:template.do";
	}
	
	@GetMapping("/modifyTemplate.do")
	public String modifyTemplate(String sidt_temp_cd, Model model) {
		log.info("TemplateController 템플릿 수정화면으로 이동하는 modifyTemplate");
		TemplateDto temDto = service.getDetailTemp(sidt_temp_cd);
		model.addAttribute("temDto",temDto);
		return "doc/modifyTemplate";
	}	
	
	@PostMapping(value = "updateTemplate.do", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String updateTemplate(@RequestBody Map<String, Object> map) {
		log.info("TemplateController 템플릿 수정하는 updateTemplate");
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("sica_cd", sicaCd);
//        paramMap.put("sidt_temp_name", tempName);
//        paramMap.put("sidt_temp_content", tempContent);
        
        service.updateTemp(map);
		
		return "redirect:/detailTemplate.do?sidt_temp_cd=" + map.get("sidt_temp_cd");
	}
	
	@GetMapping("/deleteTemplate.do")
	public String deleteTemplate(@RequestParam(name = "sidt_temp_cd") String sidt_temp_cd) {
		log.info("TemplateController 템플릿 삭제하는 deleteTemplate");
		int n = service.deleteTemp(sidt_temp_cd);
		return "redirect:template.do";		
	}
	
	@PostMapping(value = "getTemplate.do", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String getTemplate(String sidt_temp_cd) {
		log.info("TemplateController 선택한 템플릿 에디터로 가져오는 getTemplate");
		String getTemp = service.getTemplate(sidt_temp_cd);		
		return getTemp;
		
	}
	
	
	
	
	
}