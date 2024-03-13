package com.hcm.grw.ctrl.sm;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("sm/")
public class ReplyController {

	
	
	
	@GetMapping("insertReply.do")
	public ResponseEntity<?> insertReply(@RequestParam String rebo_content){
		
		return ResponseEntity.ok(null);
	}
}
