package com.hcm.grw.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
	public void Error404(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("/WEB-INF/error/Error404.jsp").forward(request, response);
	}
	
	@GetMapping("/Error500.do")
	@ResponseBody
	public void Error500(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("/WEB-INF/error/Error500.jsp").forward(request, response);
	}
	
	@GetMapping("/login.do")
	public String login() {
		return "login";
	}
	
	@GetMapping("/mainTmp.do")
	public String mainTmp() {
		return "mainTmp";
	}
	
	@GetMapping("/hrMainTmp.do")
	public String hrMainTmp() {
		return "hr/hrMainTmp";
	}
	
	@GetMapping("/smMainTmp.do")
	public String smMainTmp() {
		return "sm/smMainTmp";
	}
	
	@GetMapping("/docMainTmp.do")
	public String docMainTmp() {
		return "doc/docMainTmp";
	}
	
	
}
