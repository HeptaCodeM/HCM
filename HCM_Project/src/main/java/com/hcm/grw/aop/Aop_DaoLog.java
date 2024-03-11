package com.hcm.grw.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class Aop_DaoLog {
	
	private long start = 0;
	private long end = 0;
	
	@Pointcut("execution(public * com.hcm.grw.model.mapper.**.*.*(..))")
	public void daoPoint() {};
	
	@Before("daoPoint()")
	public void beforePoint(JoinPoint j) {
		start = System.currentTimeMillis();
		Logger log = LoggerFactory.getLogger(j.getTarget() + "");
		String method = j.getSignature().getName();
		Object[] args = j.getArgs();
		for (int i = 0; i < args.length; i++) {
			log.debug("[AOP Logger]\t {} {}번째 Argument : {}", method, i + 1, args[i]);
		}
	}
	
	@After("daoPoint()")
	public void afterPoint(JoinPoint j) {
		Logger log = LoggerFactory.getLogger(j.getTarget() + "");
		end = System.currentTimeMillis();
		long result = end - start;
		double sec = result/100.0;
		String method = j.getSignature().getName();
		log.debug("[AOP Logger]\t {} 소요시간 : {}초", method, sec);
	}
	
	@AfterReturning(pointcut = "daoPoint()", returning = "returnValue")
	public void afterReturning(JoinPoint j, Object returnValue) {
		Logger log = LoggerFactory.getLogger(j.getTarget() + "");
		String method = j.getSignature().getName();
		log.debug("[AOP Logger]\t {} 반환 값 : {}", method, returnValue);
	}
	
	@AfterThrowing(pointcut = "daoPoint()", throwing = "error")
	public void afterThrowing(JoinPoint j, Exception error) {
		Logger log = LoggerFactory.getLogger(j.getTarget() + "");
		String method = j.getSignature().getName();
		log.error("[AOP Logger]\t {} 오류 : {}", method, error.getMessage());
	}

}
