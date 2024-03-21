package com.hcm.grw.interceptor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DuplicateRequestHandler implements HandlerInterceptor {

	private Set<String> requestSet = Collections.synchronizedSet(new HashSet<>());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
		if (requestSet.contains(requestURI)) {
			log.info("[DuplicateRequestHandler]\t" + requestURI + " : 중복된 요청 차단");
			requestSet.remove(requestURI);
            return false;
            
        }
//		requestSet.add(requestURI);
		return true;
	}
	
}
