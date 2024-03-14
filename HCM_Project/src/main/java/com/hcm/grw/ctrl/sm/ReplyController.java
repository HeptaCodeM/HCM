package com.hcm.grw.ctrl.sm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcm.grw.dto.sm.ReplyDto;
import com.hcm.grw.model.service.sm.IReplyService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("sm/")
public class ReplyController {

	@Autowired
	private IReplyService service;
	
	
	@GetMapping("insertReply.do")
	public ResponseEntity<?> insertReply(@RequestParam ReplyDto dto){
		
		
		
		return ResponseEntity.ok(null);
	}
}
