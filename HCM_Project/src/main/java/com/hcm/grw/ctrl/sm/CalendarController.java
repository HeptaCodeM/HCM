package com.hcm.grw.ctrl.sm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hcm.grw.model.service.sm.IScbService;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequestMapping("sm/")
public class CalendarController {

	
	
	@Autowired IScbService service;
	
	@GetMapping("calendar.do")
	public String calendar(){
		log.info("CalendarController 캘린더 화면 이동");
		return "sm/schedule/calendar";
	}
	
}
