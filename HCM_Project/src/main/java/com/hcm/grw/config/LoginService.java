package com.hcm.grw.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.model.mapper.hr.EmployeeDao;

import lombok.extern.slf4j.Slf4j;

/*
UserDetailsService를 구현한 LoginService 클래스. 
사용자가 입력한 사용자 아이디를 기반으로 사용자 정보를 데이터베이스에서 검색하고, 해당 사용자의 권한 정보를 로드하여 UserDetails 객체로 반환처리.
*/
//@Service("com.hcm.grw.config")
@Slf4j
public class LoginService implements UserDetailsService {
	
	@Autowired
	private EmployeeDao dao;

//	@Autowired
//	private HttpServletRequest req;
	
    /*
	loadUserByUsername(String userId)은 UserDetailsService 인터페이스의 추상메서드로 사용자의 아이디를 입력받아 사용자의 상세정보를 로드
	*/
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		log.info("LoginService loadUserByUsername : {}", userId);
		log.info("LoginService repository : {}", dao);

		String privateKey = new String("D:/raon_secure/webapp/WEB-INF/raon_config/nxkey/Private2048.key.der");	

//		HttpSession session = req.getSession();
		
//		log.info("req : {}", req.getParameter("username"));
//		log.info("session : {}", session.toString());
//		log.info("privateKey : {}", privateKey);

		
		//E2ECrypto Tk = new E2ECrypto(req, session, privateKey);
		/*
		int LastError = Tk.getLastError();
		if (LastError != 0) {
			System.out.println("[raonsecure] TouchEnKey ERROR_CODE :[ " + LastError+ " ] , ERROR_Message [" + Tk.getLastErrorMessage()	+ " ]");
		}
		*/
		
		//log.info("Tk.getDecryptedAttribute : {}", Tk.getDecryptedAttribute(userId));
		
		EmployeeDto employeeDto = dao.getLogin(userId);	//userId로 상세정보 조회
		log.info("LoginService userInfoVo : {}", employeeDto);
		log.info("employeeDto.getEmpl_pwd() : {}", employeeDto.getEmpl_pwd());
		
		if(employeeDto != null) {	//정보가 있다면...
			/*
			ROLE 정보가 여러개 있다면 Collection 객제로 전달한다.
			Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
			roles.add(new SimpleGrantedAuthority(userInfoVo.getAuth()));
			return new User(userId, userInfoVo.getPassword(), roles);
			*/
			return new User(userId, employeeDto.getEmpl_pwd(), AuthorityUtils.createAuthorityList(employeeDto.getEmpl_auth()));
		}else {
			return null;
		}

	}
}
