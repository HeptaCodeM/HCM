package com.hcm.grw.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.hcm.grw.comm.Function;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.model.service.hr.EmployeeService;

@Service
public class CreateNewAuthService {

	@Autowired
	private LoginService loginService;

	@Autowired
	private EmployeeService employeeService;

	
	/**
	  * @description 정보 수정 시 새로운 인증 생성
	  * @param auth 현재 auth 정보
	  * @param username	현재 사용자 Id
	  * @return Authentication
	  * @author SDJ
	  * @since 2024.03.21
	*/
	public boolean createNewAuthentication(Authentication auth, 
												  String username, 
												  HttpServletRequest req) {

		try {
			UserDetails newPrincipal = loginService.loadUserByUsername(username);
		    UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newPrincipal, auth.getCredentials(), newPrincipal.getAuthorities());
		    newAuth.setDetails(auth.getDetails());
			SecurityContextHolder.getContext().setAuthentication(newAuth);
			
			
			//Session Role정보 Update
			EmployeeDto employeeDto = employeeService.getUserInfo(newAuth.getName());
			//이미지 스트링 정보로 처리
			employeeDto.setEmpl_picture_str(Function.blobImageToString(employeeDto.getEmpl_picture()));
			//2진정보 초기화
			employeeDto.setEmpl_picture(null);
			HttpSession session = req.getSession();
			session.setAttribute("userInfoVo", employeeDto);
			
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
