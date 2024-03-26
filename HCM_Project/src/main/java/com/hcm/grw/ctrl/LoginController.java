package com.hcm.grw.ctrl;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcm.grw.comm.CookiesMgr;
import com.hcm.grw.comm.EmailService;
import com.hcm.grw.dto.hr.CompanyDto;
import com.hcm.grw.model.service.hr.CompanyService;
import com.hcm.grw.model.service.hr.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private CompanyService companyService;
	
	@GetMapping("/login/login.do")
	public String login(String error, String logout, Model model, HttpServletRequest request) {
		log.info("error : {}", error);
		log.info("logout : {}", logout);

		if (error != null) {
			model.addAttribute("error", "로그인 오류! 계정을 확인하세요.");
		}

		if (logout != null) {
			model.addAttribute("logout", "로그아웃!!");
		}

		try {
			String ipAddr=request.getRemoteAddr();
			if(ipAddr.equalsIgnoreCase("0:0:0:0:0:0:0:1")){
			    InetAddress inetAddress=InetAddress.getLocalHost();
			    ipAddr=inetAddress.getHostAddress();
			}
			
			Map<String, String> loginMap = new HashMap<String, String>();
			
			//ip주소 셋팅
			String empl_id = "";
			if(ipAddr.equals("192.168.8.145")) {		//신동준
				empl_id = "20220101";
			}else if(ipAddr.equals("192.168.8.28")) {	//오지수
				empl_id = "20230107";
			}else if(ipAddr.equals("192.168.8.3")) {	//류종윤
				empl_id = "20230106";
			}else if(ipAddr.equals("192.168.8.18")) {	//김지아
				empl_id = "20230105";
			}else if(ipAddr.equals("192.168.8.12")) {	//윤영훈
				empl_id = "20230108";
			}else if(ipAddr.equals("192.168.8.29")) {	//김재원
				empl_id = "20230102";
			}
			
			if(empl_id!="") {
				loginMap.put("id", empl_id);
				loginMap.put("pw", empl_id);
			}
			model.addAttribute("loginMap", loginMap);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		//모바일 처리
		Device device = DeviceUtils.getCurrentDevice(request);
		if(device.isMobile()) {
			model.addAttribute("mobile", "Y");
		}else {
			model.addAttribute("mobile", "N");
		}
		
		return "login/login";
	}
	
	@GetMapping("/login/restPwd.do")
	public String restPwd(Model model) {
		log.info("LoginController restPwd");
		
		return "/login/restPwd";
	}

	@PostMapping("/login/initPwdAuthNumSend.do")
	public @ResponseBody void initPwdAuthNumSend(@RequestParam Map<String, Object> empMap, HttpServletResponse resp) throws IOException {
		log.info("LoginController initPwdAuthNumSend empMap : {}", empMap);
		resp.setContentType("text/html; charset=UTF-8;");

		int cnt = employeeService.getInitPwdcheck(empMap);
		if(cnt>0) {
			CompanyDto companyDto = companyService.showCompanyInfo(new HashMap<String, Object>(){{
				put("comp_id", "ITCOM0A1");
			}});

	        // Random 객체 생성
	        Random random = new Random();
	        // 4자리 숫자 생성
	        int randomNumber = random.nextInt(9000) + 1000;
			
			String subject = "[HCM]비밀번호 찾기 인증번호";
			String content = "";
			content += "비밀번호 찾기의 인증번호를 안내드립니다.<br>";
			content += "요청하신 인증번호는 ";
			content += String.valueOf(randomNumber);
			content += "입니다.<br>";
			content += "인증번호 입력 화면으로 돌아가 인증번호를 입력하세요.<br>";
			String toEmail = String.valueOf(empMap.get("empl_email"));
			String fromEmail = companyDto.getComp_email();

			//boolean sendFlag = fn.sendMail(subject, content, toEmail, fromEmail, htmlFlag);
			boolean sendFlag = emailService.sendMail(subject, content, toEmail, fromEmail, true);
			
			if(sendFlag) {
				resp.getWriter().print("true");
				CookiesMgr.setCookies(resp, "cInitPwdAuthNum", String.valueOf(randomNumber), 3);
			}else {
				resp.getWriter().print("false");
			}
		}else {
			resp.getWriter().print("false");
		}
	}

	
	@PostMapping("/login/initPwdAuthNumCheck.do")
	public @ResponseBody void initPwdAuthNumCheck(@RequestParam Map<String, Object> authNumMap, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.info("LoginController initPwdAuthNumCheck empMap : {}", authNumMap);
		resp.setContentType("text/html; charset=UTF-8;");
		
		String authNum = authNumMap.get("authnum").toString();
		String cAuthNum = CookiesMgr.getCookies(req, "cInitPwdAuthNum");

		log.info("authNum : {}, cAuthNum : {}", authNum, cAuthNum);
		
		//인증번호 비교
		if(authNum.equals(cAuthNum)) {
			// Random 객체 생성
	        Random random = new Random();
	        // 8자리 숫자 생성
	        int randomNumber = random.nextInt(90000000) + 10000000;
	        
	        authNumMap.put("empl_pwd", String.valueOf(randomNumber));
	        
	        // 초기화 비밀번호 업데이트
	        int cnt = employeeService.setInitPwd(authNumMap);
	        if(cnt>0) {
				CompanyDto companyDto = companyService.showCompanyInfo(new HashMap<String, Object>(){{
					put("comp_id", "ITCOM0A1");
				}});
		        
		        String subject = "[HCM]비밀번호 초기화 안내";
				String content = "";
				content += "비밀번호 초기화 정보를 안내드립니다.<br>";
				content += "초기화된 비밀번호는 ";
				content += String.valueOf(randomNumber);
				content += "입니다.<br>";
				content += "로그인 화면에서 로그인 후 비밀번호 변경을 진행하여 주세요.<br>";
				String toEmail = String.valueOf(authNumMap.get("empl_email"));
				String fromEmail = companyDto.getComp_email();
	
				//메일발송
				boolean sendFlag = emailService.sendMail(subject, content, toEmail, fromEmail, true);
				if(sendFlag) {
					resp.getWriter().print("true");
					CookiesMgr.delCookies(req, resp, "cInitPwdAuthNum");
				}else {
					resp.getWriter().print("false");
				}
	        }else {
				resp.getWriter().print("false");
	        }
		}else {
			resp.getWriter().print("false");
		}
	}
	
}

