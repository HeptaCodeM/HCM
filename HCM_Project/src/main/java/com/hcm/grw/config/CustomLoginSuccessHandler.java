package com.hcm.grw.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.hcm.grw.comm.CookiesMgr;
import com.hcm.grw.comm.Function;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.model.service.hr.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private EmployeeService employeeService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, 
										HttpServletResponse response,
										Authentication authentication) throws IOException, ServletException {
		log.info("{} - login Success", Function.getMethodName());
		
		List<String> roleNames = new ArrayList<String>();
	
		/*
		Spring Security의 Authentication 객체에서 사용자의 권한(authority) 정보를 추출하여 roleNames 리스트에 추가
		authentication.getAuthorities()는 현재 사용자의 권한을 나타내는 컬렉션을 반환
		ex) // ["ROLE_USER"],["ROLE_ADMIN"]
		*/
		authentication.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
		});
		
		
		EmployeeDto employeeDto = employeeService.getUserInfo(authentication.getName());
		
		HttpSession session = request.getSession();
		//이미지 스트링 정보로 처리
		employeeDto.setEmpl_picture_str(Function.blobImageToString(employeeDto.getEmpl_picture()));
		//2진정보 초기화
		employeeDto.setEmpl_picture(null);
		session.setAttribute("userInfoVo", employeeDto);
		
		log.info("ROLE NAME : {}", roleNames);

		// 로그인 성공 시 메인화면 이동
		if(authentication.isAuthenticated()) {
			String ipAddr;
			try {
				ipAddr = Function.getIpAddress(request);
			} catch (Exception e) {
				e.printStackTrace();
				ipAddr = "";
			}
			Map<String, String> loginHistoryMap = new HashMap<String, String>();
			loginHistoryMap.put("empl_id", authentication.getName());
			loginHistoryMap.put("emlh_create_ip", ipAddr);
			
			int historyCnt = employeeService.insertLoginHistory(loginHistoryMap);
			log.info("{} - historyCnt : {}", Function.getMethodName(), historyCnt);
			
			//log.info("@@@@@@@@@@@@@@@@ ckMobile : {}",CookiesMgr.getCookies(request, "ckMobile"));
			if(CookiesMgr.getCookies(request, "ckMobile").equals("Y")) {
				response.sendRedirect("/hr/commute/registCommute.do");
			}else {
				response.sendRedirect("/mainTmp.do");
			}
			return;
		}

		// 롤 권한이 없다면 로그아웃 처리
		if(!roleNames.contains("ADMIN") && !roleNames.contains("USER")) {
			response.sendRedirect("/logout");
		}

	}
	

}
