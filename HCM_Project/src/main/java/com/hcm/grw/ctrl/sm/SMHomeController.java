package com.hcm.grw.ctrl.sm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hcm.grw.dto.sm.GoboDto;
import com.hcm.grw.model.service.sm.IGoboService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("sm/")
public class SMHomeController {

	
	@Autowired
	private IGoboService GoboService;
	
	
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
		model.addAttribute("dto",dto);
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
		return "redirect:/sm/GongiBoard/getAllGobo.do";
	}
	
	@GetMapping("insertGobo.do")
	public String insertGoboWrite() {
		log.info("SMHomeController insertGobo.do 공지사항 글등록 화면 이동");
		return "sm/GongiBoard/insertGobo";
	}
	
	
	@PostMapping("insertGobo.do")
	public String insertGobo(GoboDto dto) {
		log.info("SMHomeController insertGobo.do 공지사항 글등록");
		dto.setGobo_modify_id("1");
		dto.setGobo_writer("오종우");
		dto.setGobo_writer_id("dhwhddn1");
		int n = GoboService.insertGobo(dto);
		if(n>0) {
			return "sm/GongiBoard/insertGobo";
		}else {
			return "alert('글 작성 실패')";
		}
	}
	
	
}
