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
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.model.service.hr.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthCheckHandler implements HandlerInterceptor {

	@Autowired
	private EmployeeService employeeService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestURI = request.getRequestURI();
		log.info("@@@@@ AuthCheck Interceptor Start - requestURI : {}", requestURI);
		
		/* 사용자 로그인 정보 가져오기 시작 */
		// Authentication Check
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null) {
			Function.alertLocation(response, "인증정보가 없습니다.", "/login/login.do", "", "", "");
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
        EmployeeDto empDto = employeeService.getLogin(authentication.getName());
		// DB Check
        if(empDto == null) {
			Function.alertLocation(response, "DB 사용자 정보 오류입니다.", "/login/login.do", "", "", "");
			return false;
        }
        String dbRole = empDto.getEmpl_auth();
        log.info("DB_Role : {}", dbRole);

        
        //Session Check
        /*
        EmployeeDto sessionDto = (EmployeeDto)request.getSession().getAttribute("loginInfoVo");
        log.info("sessionDto : {}", sessionDto);
        // Session Check
        if(sessionDto == null) {
			Function.alertLocation(response, "Session 연결이 종료 되었습니다.", "/login/login.do", "", "", "");
			return false;
        }
        String sessionRole = sessionDto.getEmpl_auth();
        log.info("Session_Role : {}", sessionRole);
		*/
		/* 사용자 로그인 정보 가져오기 종료 */
		
		return true;
	}
	
}
