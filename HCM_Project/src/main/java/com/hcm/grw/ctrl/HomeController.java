package com.hcm.grw.ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcm.grw.comm.CookiesMgr;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping({"/index.do", "/"})
	public String index(Model model, Authentication authentication) {
		
		String getId = authentication.getName();
		log.info("getId : {}", getId);
		model.addAttribute("getId", getId);
		
		return "index";
	}

	@GetMapping("/main.do")
	public String main() {
		return "main";
	}

	@GetMapping("/error404.do")
	@ResponseBody
	public void Error404(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.getRequestDispatcher("/WEB-INF/error/error404.jsp").forward(request, response);
	}

	@GetMapping("/error500.do")
	@ResponseBody
	public void Error500(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.getRequestDispatcher("/WEB-INF/error/error500.jsp").forward(request, response);
	}

	@GetMapping("/login.do")
	public String login(String error, String logout, Model model) {
		log.info("error : {}", error);
		log.info("logout : {}", logout);

		if (error != null) {
			model.addAttribute("error", "로그인 오류! 계정을 확인하세요.");
		}

		if (logout != null) {
			model.addAttribute("logout", "로그아웃!!");
		}

		return "login";
	}

	@GetMapping("/mainTmp.do")
	public String mainTmp() {
		return "mainTmp";
	}

	@GetMapping("/hr/hrMainTmp.do")
	public String hrMainTmp() {
		return "hr/hrMainTmp";
	}

	@GetMapping("/sm/smMainTmp.do")
	public String smMainTmp() {
		return "sm/smMainTmp";
	}

	@GetMapping("/doc/docMainTmp.do")
	public String docMainTmp() {
		return "doc/docMainTmp";
	}

	//쿠키생성
	@GetMapping("/setCookies.do")
	public String setCookiesTest(HttpServletResponse resp) {
		log.info("testCk 쿠키생성");
		CookiesMgr.setCookies(resp, "testCk", "1111");
		
		return "redirect:/";
	}

	//쿠키값 확인
	@GetMapping("/getCookies.do")
	public String getCookiesTest(HttpServletRequest req) {
		
		log.info(CookiesMgr.getCookies(req, "testCk"));
		log.info("testCk 쿠키확인 : {}", CookiesMgr.getCookies(req, "testCk"));
		
		return "redirect:/";
	}
	
	//쿠키삭제
	@GetMapping("/delCookies.do")
	public String delCookiesTest(HttpServletRequest req, HttpServletResponse resp) {
		log.info("testCk 쿠키삭제");
		
		CookiesMgr.delCookies(req, resp);
		
		return "redirect:/";
	}
	
	
}
