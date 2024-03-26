package com.hcm.grw.ctrl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hcm.grw.comm.CookiesMgr;
import com.hcm.grw.comm.EmailService;
import com.hcm.grw.comm.Function;
import com.hcm.grw.dto.doc.SignBoxDto;
import com.hcm.grw.dto.hr.CommuteDto;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.dto.sm.GoboDto;
import com.hcm.grw.model.service.doc.IDocBoxService;
import com.hcm.grw.model.service.hr.CommuteService;
import com.hcm.grw.model.service.hr.HolidayService;
import com.hcm.grw.model.service.sm.IGoboService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private HolidayService holidayService;
	
	@Autowired
	private IDocBoxService docService;
	
	@Autowired
	private CommuteService commuteService;
	
	@Autowired
	private IGoboService GoboService;
	
	
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
		CookiesMgr.setCookies(resp, "testCk", "1111", 20);
		
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
		
		//CookiesMgr.delCookies(req, resp, "All");	//모두삭제
		CookiesMgr.delCookies(req, resp, "testCk");	//단일삭제
		
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
	
	
	
	@GetMapping("/mainTmp.do")
	public String mainTmp(Authentication authentication, Model model, HttpServletResponse resp) throws JsonProcessingException {
		
		String empl_id = authentication.getName();
		
		List<GoboDto> gbLists = GoboService.getAllGobo();
		model.addAttribute("gbLists",gbLists);
		model.addAttribute("gbListsSize",gbLists.size());

		Map<String, Object> holidayTotalMap = holidayService.selectEmpTotalHoliDayInfo(empl_id);
		if(holidayTotalMap != null) {
			model.addAttribute("rest_holiday",holidayTotalMap.get("REST_HOLIDAY"));
		}else {
			model.addAttribute("rest_holiday","0");
		}
		
		SignBoxDto dto = new SignBoxDto();
		dto.setEmpl_id(empl_id);
		List<SignBoxDto> table = docService.getIngDocs(dto);
		List<SignBoxDto> allDoc = docService.getAllDocs(dto);
		model.addAttribute("ingDoc",table.size());
		
		model.addAttribute("allDoc",allDoc);
		model.addAttribute("docSize",allDoc.size()-1);
		
		Date commuteInTime = null;
		Date commuteOutTime = null;
		CommuteDto cgDto = commuteService.selectCommuteDayInfo(empl_id);
		if(cgDto != null) {
			if(cgDto.getEmco_in_dt() != null) {
				commuteInTime = cgDto.getEmco_in_dt();
			}
			if(cgDto.getEmco_out_dt() != null) {
				commuteOutTime = cgDto.getEmco_out_dt();
			}
		}

		model.addAttribute("commuteInTime", commuteInTime);
		model.addAttribute("commuteOutTime", commuteOutTime);
		
		
		return "mainTmp";
	}
	
	
	
	@GetMapping("/hr/home/registCommuteOkMain.do")
	public @ResponseBody void registCommuteOk(Authentication authentication, 
												Model model, 
												HttpServletResponse resp) throws IOException {
		log.info("CommuteController registCommute 출/퇴근 처리 페이지");
		resp.setContentType("text/html; charset=UTF-8");
		
		if(authentication == null) {
			Function.alertLocation(resp, "로그인 후 이용 가능합니다.", "/login/login.do", "", "", "");
		}
		
		String empl_id = authentication.getName();
		String commuteInTime = "";
		CommuteDto cgDto = commuteService.selectCommuteDayInfo(empl_id);
		if(cgDto != null) {
			commuteInTime = cgDto.getEmco_in_dt().toString();
		}
		
		int cnt = 0;
		String commuteMsg = "";
		if(StringUtils.isEmpty(commuteInTime)) {
			CommuteDto cDto = new CommuteDto() {{
				setEmpl_id(empl_id);
			}};
			
			cnt += commuteService.registCommute(cDto);
			commuteMsg = "출근";
		}else {
			cnt += commuteService.updateCommute(empl_id);
			commuteMsg = "퇴근";
		}
		
		if(cnt > 0) {
			Function.alertLocation(resp, commuteMsg + "처리가 완료 되었습니다.", "/hr/commute/registCommute.do", "", "", "");
		}else {
			Function.alertHistoryBack(resp, commuteMsg + "처리 중 오류가 발생하였습니다.<br>관리자에게 문의하세요.", "", "");
		}
		
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
