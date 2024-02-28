package com.hcm.grw.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public void index() {
		
	}
	
	@GetMapping("/main.do")
	public String main() {
		return "main";
	}
	
	@GetMapping("/Error404.do")
	public String Error404() {
		return "Error404";
	}
	
	@GetMapping("/Error500.do")
	public String Error500() {
		return "Error500";
	}
	
	@GetMapping("/login.do")
	public String login() {
		return "login";
	}
	
}
