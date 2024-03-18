package com.hcm.grw.ctrl.sm;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	
	@GetMapping("insertReply.do")
	@ResponseBody
	public Boolean insertReply(ReplyDto dto, Authentication auth, HttpSession session){
		log.info("ReplyController insertReply 댓글등록 : {} ", dto);
		System.out.println(dto);
		int n = service.insertReply(dto);
		String id = "";
		if(auth != null) {
			id = auth.getName();
		}
		String name = "";
		EmployeeDto empldto = (EmployeeDto)session.getAttribute("userInfoVo");
		if(empldto != null) {
			name = empldto.getEmpl_name();
		}
		System.out.println(name);
		
		
		return (n>0)?true:false;
	}
}
