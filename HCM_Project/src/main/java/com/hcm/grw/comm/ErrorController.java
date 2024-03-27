package com.hcm.grw.comm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/error/**")
public class ErrorController {

	@GetMapping("error.do")
	public String Error(HttpServletRequest request, 
						HttpServletResponse response,
						String errCode,
						Model model) throws IOException, ServletException {
		
		model.addAttribute("errCode", errCode);

		return "error/error";
	}

	@GetMapping("error403.do")
	public String Error403(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		return "error/error403";
	}

	@GetMapping("error404.do")
	public String Error404(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		return "error/error404";
	}

	@GetMapping("error500.do")
	public String Error500(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		return "error/error500";
	}

}
