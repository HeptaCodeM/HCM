package com.hcm.grw.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {

	@GetMapping("/login/login.do")
	public String login(String error, String logout, Model model) {
		log.info("error : {}", error);
		log.info("logout : {}", logout);

		if (error != null) {
			model.addAttribute("error", "로그인 오류! 계정을 확인하세요.");
		}

		if (logout != null) {
			model.addAttribute("logout", "로그아웃!!");
		}

		return "login/login";
	}
	
	@GetMapping("/login/restPwd.do")
	public String restPwd(Model model) {
		log.info("LoginController restPwd");

		return "/login/restPwd";
	}
	

}
