package com.hcm.grw.ctrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcm.grw.comm.CookiesMgr;
import com.hcm.grw.comm.EmailService;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.model.service.hr.HolidayService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private HolidayService holidayService;
	
	
	@GetMapping({"/index.do", "/"})
	public String index(HttpServletRequest request, Model model, Authentication authentication, HttpSession session) {
		
		String getId = "";
		if(authentication != null) {
			getId = authentication.getName();
		}
		log.info("getId : {}", getId);
		
		model.addAttribute("getId", getId);

		//공통함수를 이용한 사원정보 조회
		EmployeeDto employeeDto = (EmployeeDto)session.getAttribute("userInfoVo");
		String empl_id = "";
		if(employeeDto != null) {
			empl_id = employeeDto.getEmpl_id();
		}
		log.info("session empl_id : {}", empl_id);
		model.addAttribute("empl_info", employeeDto);
		
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

	
	//메일발송 테스트
	@GetMapping("/sendMailTest.do")
	public String sendMailTest() {
		log.info("메일발송");
		
		String subject = "테스트 메일 입니다.";
		String content = "테스트 입니다.<br/>테스트 입니다.";
		String toEmail = "hcm_0415@naver.com";
		String fromEmail = null;

		//boolean sendFlag = fn.sendMail(subject, content, toEmail, fromEmail, htmlFlag);
		boolean sendFlag = emailService.sendMail(subject, content, toEmail, fromEmail, true);
		log.info("메일발송 : {}", sendFlag);
		
		return "redirect:/";
	}


	//휴가관련 조회
	@GetMapping("/holidayTest.do")
	public String holidayInfo() {
		/*
		* 사원별 휴가정보 조회
		*/
		Map<String, Object> holidayTotalMap = holidayService.selectEmpTotalHoliDayInfo("20220101");
		for(String s : holidayTotalMap.keySet()) {
			log.info("holidayTotalMap key: {}, value : {}", s, holidayTotalMap.get(s));
		}
		log.info("소문자 테스트 : {}",holidayTotalMap.get("TOTAL_HOLIDAY"));
		
		/* 
		* 휴가일자 조회 
		*/
		Map<String, String> holidayMap = new HashMap<String, String>(){{
			put("sidb_doc_be","2024-02-29");
			put("sidb_doc_end","2024-03-04");
		}};
		
		int holiCnt = holidayService.selectHoliDayInfo(holidayMap);
		log.info("전자결재에서 선택한 일자로 휴가일 검색 : {}", holiCnt);
		
		return "redirect:/";
	}
	
	@GetMapping("notification.do")
	public String notification() {
		return "notification";
	}
	
	@GetMapping("fileTest.do")
	public String fileTest() {
		return "fileTest";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
