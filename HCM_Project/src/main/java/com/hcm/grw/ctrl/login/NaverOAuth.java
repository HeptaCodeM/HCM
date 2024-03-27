package com.hcm.grw.ctrl.login;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcm.grw.comm.Function;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequestMapping("/api/**")
public class NaverOAuth {

	@Value("#{dataSpcProperties['naver.auth']}")
	private String authUrl;
	@Value("#{dataSpcProperties['naver.token']}")
	private String tokenUrl;
	@Value("#{dataSpcProperties['naver.me']}")
	private String meUrl;
	@Value("#{dataSpcProperties['naver.clientid']}")
	private String clientId;
	@Value("#{dataSpcProperties['naver.clientsecret']}")
	private String clientSecret;
	
	
	// CSRF 방지를 위한 상태 토큰 생성 코드
	// 상태 토큰은 추후 검증을 위해 세션에 저장되어야 한다.
	public String generateState()
	{
	    SecureRandom random = new SecureRandom();
	    return new BigInteger(130, random).toString(32);
	}
	
	//Token발급요청
	public String getToken() {
		log.info("{} - 토큰발급 요청", Function.getMethodName());
		
		return null;
	}

	
	@GetMapping("naverCallback.do")
	public @ResponseBody String NaverCallback(String code, String state) {
		log.info("{} - CallBack code : {}, state : {}", Function.getMethodName(), code, state);
		
		
		
		return null;
	}
	
	
}
