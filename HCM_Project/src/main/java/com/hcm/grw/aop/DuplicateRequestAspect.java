package com.hcm.grw.aop;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class DuplicateRequestAspect {

	private Set<String> requestSet = Collections.synchronizedSet(new HashSet<>());
	
	@Pointcut(value = "within(*..*Controller)")
	public void requestPoint() {};
	
//	@Around("requestPoint()")
	public Object duplicateRequestCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("[DuplicateRequestAspect]\t HTTP 요청 중복 체크..");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	    String httpMethod = request.getMethod();
	    String requestId = joinPoint.getSignature().toLongString();
	    
	    if(httpMethod.equalsIgnoreCase("GET")) {
	    	joinPoint.proceed();
	    }
	    
        if (requestSet.contains(requestId)) {
            // 중복 요청인 경우
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("중복된 요청입니다");
        }
        requestSet.add(requestId);
        
        try {
        	return joinPoint.proceed();
        } finally {
        	requestSet.remove(requestId);
        }
	}
	
}
