package com.hcm.grw.ctrl.sm;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
	public ReplyDto insertReply(ReplyDto dto, HttpSession session){
		log.info("ReplyController insertReply 댓글등록 : {} ", dto);
		EmployeeDto empldto = (EmployeeDto)session.getAttribute("userInfoVo");
		 dto.setRebo_writer(empldto.getEmpl_name());
		 dto.setRebo_writer_id(empldto.getEmpl_id());
		System.out.println(dto);
		int n = service.insertReply(dto);
		return dto;
	}
	
	@GetMapping("updateReply.do")
	@ResponseBody
	public Boolean updateReply(ReplyDto dto,HttpSession session) {
		log.info("ReplyController insertReplyTwo 댓글 수정 :  {} ", dto);
		EmployeeDto empldto = (EmployeeDto)session.getAttribute("userInfoVo");
		 dto.setRebo_writer(empldto.getEmpl_name());
		 dto.setRebo_modify_id(empldto.getEmpl_id());
		 System.out.println(dto.getRebo_content());
		int n = service.updateReply(dto);
		return (n>0)?true:false;
	}
	
	
	@GetMapping("updateReplyDelFlag.do")
	@ResponseBody
	public Boolean updateReplyDelFlag(String rebo_no) {
		log.info("ReplyController insertReplyTwo 댓글 삭제 :  {} ",rebo_no);
		int n = service.updateReplyDelFlag(rebo_no);
		return (n>0)?true:false;
	}
	
	
	
	
	
	
	
	
	
	
}
