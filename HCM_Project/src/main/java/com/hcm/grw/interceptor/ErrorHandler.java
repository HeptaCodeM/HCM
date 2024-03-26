package com.hcm.grw.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hcm.grw.comm.Function;
import com.hcm.grw.config.CreateNewAuthService;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.model.service.hr.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErrorHandler implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 예외 발생 여부 확인
        Object status = request.getAttribute("javax.servlet.error.status_code");
        log.info("preHandle Error status : {}", status);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            // 에러 페이지로 리다이렉션
            switch (statusCode) {
	            case 403:
	                response.sendRedirect("/error/403.do");
	                return false;
                case 404:
                    response.sendRedirect("/error/404.do");
                    return false;
                case 500:
                    response.sendRedirect("/error/500.do");
                    return false;
                // 다른 에러 상태코드에 대한 처리 추가 가능
                default:
                    response.sendRedirect("/error.do?errCode="+statusCode);
                    return false;
            }
        }
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 예외 발생 여부 확인
        Object status = request.getAttribute("javax.servlet.error.status_code");
        log.info("postHandle Error status : {}", status);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            // 에러 페이지로 리다이렉션
            switch (statusCode) {
	            case 403:
	                response.sendRedirect("/error/403.do");
	                return;
                case 404:
                    response.sendRedirect("/error/404.do");
                    return;
                case 500:
                    response.sendRedirect("/error/500.do");
                    return;
                // 다른 에러 상태코드에 대한 처리 추가 가능
                default:
                    response.sendRedirect("/error.do?errCode="+statusCode);
                    return;
            }
        }
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 예외 발생 여부 확인
        Object status = request.getAttribute("javax.servlet.error.status_code");
        log.info("afterCompletion Error status : {}", status);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            // 에러 페이지로 리다이렉션
            switch (statusCode) {
	            case 403:
	                response.sendRedirect("/error/403.do");
	                return;
                case 404:
                    response.sendRedirect("/error/404.do");
                    return;
                case 500:
                    response.sendRedirect("/error/500.do");
                    return;
                // 다른 에러 상태코드에 대한 처리 추가 가능
                default:
                    response.sendRedirect("/error.do?errCode="+statusCode);
                    return;
            }
        }
	}
	
	

}
