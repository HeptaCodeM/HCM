package com.hcm.grw.ctrl.doc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hcm.grw.dto.doc.SignJsonDto;
import com.hcm.grw.dto.doc.TempTreeDto;
import com.hcm.grw.dto.doc.TemplateDto;
import com.hcm.grw.model.service.doc.ITempTreeService;
import com.hcm.grw.model.service.doc.ITemplateService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/doc/")
public class TempTreeController {

	@Autowired
	private ITempTreeService service;
	@Autowired
	private ITemplateService tService;
	
	@GetMapping("getTempTree.do")
	public ResponseEntity<?> tempTree(){
		log.info("TempTreeController getTempTree.do GET 템플릿트리 비동기 요청");
		List<TempTreeDto> lists = service.getTempTree();
		Gson gson = new GsonBuilder().create();
		
		return ResponseEntity.ok(gson.toJson(lists));
	}
	
	@GetMapping(value = "getDoc.do", produces = "text/html; charset=UTF-8")
	public ResponseEntity<?> getDoc(@RequestParam String sidt_temp_cd) {
		log.info("TempTreeController getDoc.do GET 템플릿 불러오기 전달값 : {}",sidt_temp_cd );
		TemplateDto dto = tService.getDetailTemp(sidt_temp_cd);
		return ResponseEntity.ok(dto.getSidt_temp_content());
	}
	
	
	@PostMapping(value = "/insertTempDoc.do")
	@RequestBody
	public void insertTempDoc(@ModelAttribute SignJsonDto jsonDto) {
		log.info("TempTreeController insertTempDoc.do POST 기안문 작성");
		
	}
	
	
	
}
