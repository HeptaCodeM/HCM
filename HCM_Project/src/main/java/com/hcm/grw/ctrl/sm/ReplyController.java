package com.hcm.grw.ctrl.sm;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.dto.sm.ReplyDto;
import com.hcm.grw.model.service.sm.IGoboService;
import com.hcm.grw.model.service.sm.IReplyService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("sm/")
public class ReplyController {

	@Autowired
	private IReplyService service;
	@Autowired
	private IGoboService GoboService;
	
	@GetMapping("insertReply.do")
	@ResponseBody
	public boolean insertReply(ReplyDto dto, HttpSession session){
		log.info("ReplyController insertReply 댓글등록 : {} ", dto);
		EmployeeDto empldto = (EmployeeDto)session.getAttribute("userInfoVo");
		 dto.setRebo_writer(empldto.getEmpl_name());
		 dto.setRebo_writer_id(empldto.getEmpl_id());
		System.out.println(dto);
		int n = service.insertReply(dto);
		return (n>1)?true:false;
	}
	
	@GetMapping("insertReplyTwo.do")
	@ResponseBody
	public boolean insertReplyTwo(ReplyDto dto, HttpSession session) {
		log.info("ReplyController insertReplyTwo 대댓글등록 :  {} ", dto);
		 EmployeeDto empldto = (EmployeeDto)session.getAttribute("userInfoVo");
		 dto.setRebo_writer(empldto.getEmpl_name());
		 dto.setRebo_writer_id(empldto.getEmpl_id());
		System.out.println(dto);
		int n = service.insertReplyTwo(dto);
		return (n>1)?true:false;
	}
	
	@GetMapping("updateReply.do")
	public int updateReply(ReplyDto dto) {
		log.info("ReplyController insertReplyTwo 댓글 수정 :  {} ", dto);
		int n = service.updateReply(dto);
		return n;
	}
	
	
	@GetMapping("updateReplyDelFlag.do")
	public int updateReplyDelFlag(String rebo_no) {
		log.info("ReplyController insertReplyTwo 댓글 삭제 :  {} ",rebo_no);
		int n = service.updateReplyDelFlag(rebo_no);
		return n;
	}
	
	
	
	@GetMapping("")
	public List<List<ReplyDto>> ReplyList(String gobo_no) {
		log.info("ReplyController insertReplyTwo  :  {} ",gobo_no);
		List<List<ReplyDto>> list = new ArrayList<List<ReplyDto>>();
		List<ReplyDto> Rlist = service.getAllReply(gobo_no);
		List<ReplyDto> Dlist = service.getAllReplyTwo(gobo_no);
		list.add(Rlist);
		list.add(Dlist);
		return list;
	}
	
	
	
	
	
	
}
