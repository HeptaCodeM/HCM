package com.hcm.grw.ctrl.sm;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.GsonBuilder;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.dto.sm.ReplyDto;
import com.hcm.grw.model.service.sm.IReplyService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("sm/")
public class ReplyController {

	@Autowired
	private IReplyService service;
	
	
	@PostMapping("insertReply.do")
	public int insertReply(ReplyDto dto){
		log.info("ReplyController insertReply 댓글등록 : {} ", dto);
		System.out.println(dto);
		int n = service.insertReply(dto);
		return n;
	}
}
