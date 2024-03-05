package com.hcm.grw.ctrl.sm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SMController {

	
	@GetMapping(value = "/sm/getAllGobo.do")
	public String Gobo() {
		log.info("SM 메인 화면 이동");
		
		
		return "sm/Gobo";
	}
	
	
}
