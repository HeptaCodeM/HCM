package com.hcm.grw.ctrl.sm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcm.grw.dto.sm.ReplyDto;
import com.hcm.grw.model.service.sm.IReplyService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("sm/")
public class ReplyController {

	@Autowired
	private IReplyService service;
	
	
	@GetMapping("insertReply.do")
	public Boolean insertReply(@RequestParam ReplyDto dto){
		System.out.println(dto);
		int n = service.insertReply(dto);
		
		
		
		return (n>0)?true:false;
	}
}
