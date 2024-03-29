package com.hcm.grw.comm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/error/**")
public class ErrorController {


	@GetMapping("{errCode}.do")
	public String error404(Model model, @PathVariable("errCode") String errCode) {
		errCode = errCode.replace("error", "");
		log.info("errCode : {}", errCode);
		model.addAttribute("errCode", errCode);
		
		return "error/error";
	}
	/*
	@GetMapping("error404.do")
	public String error404(Model model) {

		model.addAttribute("errCode", "404");
		
		return "error/404";
	}

	@GetMapping("error403.do")
	public String error403(Model model) {

		model.addAttribute("errCode", "403");
		
		return "error/403";
	}

	@GetMapping("error500.do")
	public String error500(Model model) {

		model.addAttribute("errCode", "500");
		
		return "error/500";
	}
	*/

	
}
