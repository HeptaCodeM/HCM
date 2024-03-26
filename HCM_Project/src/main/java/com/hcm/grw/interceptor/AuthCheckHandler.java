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
			log.info("@@@@@ AuthCheck Interceptor Start - requestURI : {}", requestURI);
			
			/* 사용자 로그인 정보 가져오기 시작 */
			// Authentication Check
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(authentication == null) {
				log.info("인증정보가 없습니다.");
				response.sendRedirect("/login/login.do");
				return false;
			}
	
			
			// Authentication Role Info Get
			String authRole = "";
			for (GrantedAuthority authority : authentication.getAuthorities()) {
				authRole += authority.getAuthority();
	            // 여기에 권한에 따른 추가적인 처리를 수행할 수 있습니다.
	        }
	        log.info("Auth_Role : {}", authRole);
	
	        
	        //DB Role Info Get
	        EmployeeDto empDto = employeeService.getUserInfo(authentication.getName());
			// DB Check
	        if(empDto == null) {
				log.info("DB 사용자 정보 오류입니다.");
				response.sendRedirect("/login/login.do");
				return false;
	        }
	        String dbRole = empDto.getEmpl_auth();
	        log.info("DB_Role : {}", dbRole);
			/* 사용자 로그인 정보 가져오기 종료 */
			
	        if(!authRole.equals(dbRole)) {
				//Role정보 Update
				//Security Role정보 Update
				SecurityContextHolder.getContext().setAuthentication(authService.createNewAuthentication(authentication,authentication.getName()));
				//Session Role정보 Update
				EmployeeDto employeeDto = employeeService.getUserInfo(authentication.getName());
				HttpSession session = request.getSession();
				//이미지 스트링 정보로 처리
				employeeDto.setEmpl_picture_str(Function.blobImageToString(employeeDto.getEmpl_picture()));
				//2진정보 초기화
				employeeDto.setEmpl_picture(null);
				session.setAttribute("userInfoVo", employeeDto);
	        }
	        
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			Function.alertLocation(response, "오류가 발생 하였습니다.", "/login/login.do", "", "", "");
			return false;
		}
	}

}
