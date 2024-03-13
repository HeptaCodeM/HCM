package com.hcm.grw.ctrl.doc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hcm.grw.dto.doc.SignTreeDto;
import com.hcm.grw.model.service.doc.ISignTreeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/doc/")
public class SignTreeRestController {
	
	@Autowired
	private ISignTreeService treeService;
	
	@GetMapping("signTree.do")
	public ResponseEntity<?> signTree() {
		log.info("SignTreeController signTree.do GET 결재트리 비동기 요청");
		List<SignTreeDto> list = treeService.getSignTree();
		Gson gson = new GsonBuilder().create();
		return ResponseEntity.ok(gson.toJson(list));
	}
	
}
