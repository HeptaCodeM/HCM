package com.hcm.grw.ctrl.doc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hcm.grw.dto.doc.TempTreeDto;
import com.hcm.grw.model.service.doc.ITempTreeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/doc/")
public class TempTreeController {

	@Autowired
	private ITempTreeService service;
	
	@GetMapping("getTempTree.do")
	public ResponseEntity<?> tempTree(){
		log.info("TempTreeController getTempTree.do GET 템플릿트리 비동기 요청");
		List<TempTreeDto> lists = service.getTempTree();
		Gson gson = new GsonBuilder().create();
		
		return ResponseEntity.ok(gson.toJson(lists));
	}
	
	
}
