package com.hcm.grw.ctrl.sm;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.dto.sm.GoboDto;
import com.hcm.grw.dto.sm.ReplyDto;
import com.hcm.grw.model.service.sm.IGoboService;
import com.hcm.grw.model.service.sm.IReplyService;
import com.hcm.grw.socket.EchoHandler;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("sm/")
public class SMHomeController {

	
	@Autowired
	private IGoboService GoboService;
	@Autowired
	private IReplyService ReplyService;
	@Autowired
	private EchoHandler echoHandler;
	
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
		List<ReplyDto> list = ReplyService.getAllReply(gobo_no);
		for (ReplyDto rdto : list) {
			if(rdto.getEmpl_picture() != null) {
				rdto.setEmpl_picture_str(Base64Utils.encodeToString(rdto.getEmpl_picture()));
			}
		}
		
		model.addAttribute("dto",dto);
		model.addAttribute("list",list);
		return "sm/GongiBoard/GoboDetail";
	}
	
	@PostMapping("updateGoboAdmin.do")
	@ResponseBody
	public Boolean updateGobo(GoboDto dto) {
		log.info("SMHomeController updateGobo.do 공지사항 수정 ");
		int n = GoboService.updateGobo(dto);
		return (n>0)?true:false;
	}
	@GetMapping("updateGoboMoveAdmin.do")
	public String updateGoboMove(String gobo_no,Model model) {
		log.info("SMHomeController updateGobo.do 공지사항 수정화면 이동 ");
		GoboDto dto =  GoboService.getDetailGobo(gobo_no);
		model.addAttribute("dto",dto);
		return "sm/GongiBoard/updateAdminGobo";
	}
	
	
	@GetMapping("updateGoboDelFlagAdmin.do")
	public String updateGoboDelFlag(String gobo_no) {
		log.info("SMHomeController updateGoboDelFlag.do 공지사항 삭제 : {} ", gobo_no);
		int n = GoboService.updateGoboDelFlag(gobo_no);
		
		return "redirect:/sm/getAllGobo.do";
	}
	
	@GetMapping("insertGoboFormAdmin.do")
	public String insertGoboWrite() {
		log.info("SMHomeController insertGobo.do 공지사항 글등록 화면 이동");
		return "sm/GongiBoard/insertAdminGobo";
	}
	
	
	@PostMapping("insertGoboAdmin.do")
	@ResponseBody
	public Boolean insertGobo(GoboDto dto, HttpSession session) {
	    log.info("SMHomeController insertGobo.do 공지사항 글 등록: {}", dto);
	    
	    EmployeeDto empldto = (EmployeeDto)session.getAttribute("userInfoVo");
	    dto.setGobo_writer(empldto.getEmpl_name());
	    dto.setGobo_writer_id(empldto.getEmpl_id());
	    int n = GoboService.insertGobo(dto);
	    
	    // 공지등록 전체푸쉬알림 - OJS
	    echoHandler.sendMessageToClients("새로운 공지사항이 등록되었습니다");
	    
	    return (n>0)?true:false;
	}
	
	
	
	
	
}
