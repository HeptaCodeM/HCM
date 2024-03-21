package com.hcm.grw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CreateNewAuthService {

	@Autowired
	private LoginService loginService;

	/**
	  * @description 정보 수정 시 새로운 인증 생성
	  * @param auth 현재 auth 정보
	  * @param username	현재 사용자 Id
	  * @return Authentication
	  * @author SDJ
	  * @since 2024.03.21
	*/
	public Authentication createNewAuthentication(Authentication auth, String username) {
	    UserDetails newPrincipal = loginService.loadUserByUsername(username);
	    UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newPrincipal, auth.getCredentials(), newPrincipal.getAuthorities());
	    newAuth.setDetails(auth.getDetails());
	    return newAuth;
	}
}
