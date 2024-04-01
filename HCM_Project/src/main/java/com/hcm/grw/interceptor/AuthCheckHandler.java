package com.hcm.grw.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import com.hcm.grw.comm.Function;
import com.hcm.grw.config.CreateNewAuthService;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.model.service.hr.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthCheckHandler implements HandlerInterceptor {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private CreateNewAuthService authService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		try {
			String requestURI = request.getRequestURI();
			log.info("@@@@@@@@@@ AuthCheck Interceptor Start @@@@@@@@@@");
			log.info("requestURI : {}", requestURI);
			
			
			/* 사용자 로그인 정보 가져오기 시작 */
			// Authentication Check
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        log.info("Authentication Info : {}", authentication);
			if(authentication == null || authentication.getName().equals("anonymousUser")) {
				log.info("{} - AuthError : 인증정보가 없습니다.", Function.getMethodName());
				response.sendRedirect("/login/login.do");
				return false;
			}
			
			// Authentication Role Info Get
			String authRole = "";
			for (GrantedAuthority authority : authentication.getAuthorities()) {
				authRole += authority.getAuthority();
	            // 여기에 권한에 따른 추가적인 처리를 수행할 수 있습니다.
	        }
	        log.info("{} - Auth_Role : {}", Function.getMethodName(), authRole);

	        
	        //DB Role Info Get
	        EmployeeDto empDto = employeeService.getUserInfo(authentication.getName());
			// DB Check
	        if(empDto == null) {
				log.info("{} - DB 사용자 정보 오류입니다.", Function.getMethodName());
				response.sendRedirect("/login/login.do");
				return false;
	        }
	        String dbRole = empDto.getEmpl_auth();
	        log.info("{} - DB_Role : {}", Function.getMethodName(), dbRole);
			/* 사용자 로그인 정보 가져오기 종료 */

	        
			// 세션정보 확인
	        HttpSession session = request.getSession();
	        EmployeeDto sessionDto = (EmployeeDto)session.getAttribute("userInfoVo");
	        log.info("Session Info : {}", sessionDto);
			/*
			if(sessionDto == null) {
				log.info("{} - AuthError : Session정보가 없습니다.", Function.getMethodName());
				response.sendRedirect("/login/login.do");
				return false;
			}
	        log.info("{} - Session_Role : {}", Function.getMethodName(), sessionDto.getEmpl_auth());
	        */

	        
	        // 권한변경 체크 - 변경 시 Authentication, Session 정보 변경처리
	        //if(!authRole.equals(dbRole) || !authRole.equals(sessionDto.getEmpl_auth())) {
		    if(!authRole.equals(dbRole)) {
		        log.info("Roll정보 Update");
				//Role정보 Update
				//Security Role정보 Update(Session등록 포함)
				boolean flag = authService.createNewAuthentication(authentication, authentication.getName(), request);
				if(!flag) {
					log.info("{} - 인증등록 오류발생.", Function.getMethodName());
					response.sendRedirect("/login/login.do");
					return false;
				}
	        }else if(sessionDto == null){	// remember-me를 통한 로그인 시 세션 생성
	    		//이미지 스트링 정보로 처리
	        	empDto.setEmpl_picture_str(Function.blobImageToString(empDto.getEmpl_picture()));
	    		//2진정보 초기화
	        	empDto.setEmpl_picture(null);
	    		session.setAttribute("userInfoVo", empDto);	        	
	        }
	        

	        log.info("@@@@@@@@@@ AuthCheck Interceptor End @@@@@@@@@@");
	        
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			Function.alertLocation("오류가 발생 하였습니다.", "/login/login.do", "", "", "");
			return false;
		}
	}

}
