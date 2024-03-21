package com.hcm.grw.ctrl.sm;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.dto.sm.GoboDto;
import com.hcm.grw.dto.sm.ReplyDto;
import com.hcm.grw.model.service.sm.IGoboService;
import com.hcm.grw.model.service.sm.IReplyService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("sm/")
public class SMHomeController {

	
	@Autowired
	private IGoboService GoboService;
	@Autowired
	private IReplyService ReplyService;
	
	
	
	@GetMapping("getAllGobo.do")
	public String AllGobo(Model model) {
		log.info("SMHomeController getAllGobo.do 공지사항 전체조회 화면 이동");
		
		List<GoboDto> lists = GoboService.getAllGobo();
		model.addAttribute("lists",lists);
		
		return "sm/GongiBoard/Gobo";
	}
	
	@GetMapping("getDetailGobo.do")
	public String getDetailGobo(String gobo_no,Model model) {
		log.info("SMHomeController getDetailGobo.do 공지사항 상세조회 화면 이동");
		GoboDto dto =  GoboService.getDetailGobo(gobo_no);
		List<ReplyDto> Rlist = ReplyService.getAllReply(gobo_no);
		model.addAttribute("dto",dto);
		model.addAttribute("Rlist",Rlist);
		return "sm/GongiBoard/GoboDetail";
	}
	
	@PostMapping("updateGobo.do")
	public String updateGobo(GoboDto dto) {
		log.info("SMHomeController updateGobo.do 공지사항 수정 ");
		GoboService.updateGobo(dto);
		return "sm/GongiBoard/Gobo";
	}
	
	
	@GetMapping("updateGoboDelFlag.do")
	public String updateGoboDelFlag(String gobo_no) {
		log.info("SMHomeController updateGoboDelFlag.do 공지사항 삭제 : {} ", gobo_no);
		int n = GoboService.updateGoboDelFlag(gobo_no);
		return "redirect:/sm/getAllGobo.do";
	}
	
	@GetMapping("insertGoboForm.do")
	public String insertGoboWrite() {
		log.info("SMHomeController insertGobo.do 공지사항 글등록 화면 이동");
		return "sm/GongiBoard/insertGobo";
	}
	
	
	@PostMapping("insertGobo.do")
	@ResponseBody
	public Boolean insertGobo(GoboDto dto, HttpSession session) {
	    log.info("SMHomeController insertGobo.do 공지사항 글 등록: {}", dto);
	    
	    EmployeeDto empldto = (EmployeeDto)session.getAttribute("userInfoVo");
	    dto.setGobo_writer(empldto.getEmpl_name());
	    dto.setGobo_writer_id(empldto.getEmpl_id());
	    int n = GoboService.insertGobo(dto);
	    return (n>0)?true:false;
	}
	
	
	
	
	
}
