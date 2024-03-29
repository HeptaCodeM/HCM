package com.hcm.grw.comm;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
/**
* 쿠키관리 클래스
* @author : SDJ
* @since : 2024.03.06
* @version : 1.0
*/
public class CookiesMgr extends HttpServlet {
	
	private static final long serialVersionUID = 4861339738533986334L;

	/**
	* 쿠키생성
	* @param rep : HttpServletResponse
	* @param cName : 쿠키명(String)
	* @param cValue : 쿠키값(String)
	* @param expireTime : 쿠키유지시간[분](int)
	* @return : void
	* @author : SDJ
	* @since : 2024.03.06
	*/
	/* setCookies */
	public static void setCookies(String cName, String cValue, int expireTime) {
		HttpServletResponse resp = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
		resp.setContentType("text/html; charset=UTF-8;");
		
		try {
			if(expireTime == 0) {
				expireTime = 20;
			}
		}catch(Exception e) {
			expireTime = 20;
		}
		
		// 쿠키생성
		String encValue="";
		try {
			encValue = Cryption.aes256Encrypt(cValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Cookie hcmCookies = new Cookie(cName, encValue);
		//log.info("cookies 암호화 값 : {}", encValue);

		// setDomain : 쿠키 도메인 설정(도메인 주소 http, https)
		// setPath : 쿠키 경로 지정
		// setMaxAge : 쿠키 유효 시간(초*분)
		// setSecure : 쿠키 연결시 보안인증된 도메인 접속만 // true => https
		//hcmCookies.setDomain("localhost");
		hcmCookies.setPath("/");
		hcmCookies.setMaxAge(60*expireTime);
		hcmCookies.setSecure(false);
		resp.addCookie(hcmCookies);
	}

	/**
	* 쿠키값 출력
	* @param req : HttpServletRequest
	* @param cName : 쿠키명(String)
	* @return : String
	* @author : SDJ
	* @since : 2024.03.06
	*/
	/* getCookies */
	public static String getCookies(String cName) {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

		Cookie[] loginCookie = req.getCookies();
		String rtnStr = "";
		if(loginCookie!= null) {
			for(Cookie c : loginCookie) {
				if(c.getName().equalsIgnoreCase(cName)) {
					try {
						rtnStr = Cryption.aes256Decrypt(c.getValue());
						//rtnStr = c.getValue();	// 직접 사용하는 곳에서만 복호화 처리 한다.
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return rtnStr;
	}

	/**
	* 쿠키값 삭제
	* @param req : HttpServletRequest
	* @param res : HttpServletResponse
	* @param cName : 쿠키명, 쿠키명 입력(단일삭제), 쿠키명에 ALL입력 시 모두 삭제
	* @return : void
	* @author : SDJ
	* @since : 2024.03.06
	*/
	/* delCookies */
	public static void delCookies(String cName) {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpServletResponse resp = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
		resp.setContentType("text/html; charset=UTF-8;");
		
		Cookie[] loginCookie = req.getCookies();
		if(loginCookie != null) {
			for(Cookie c : loginCookie) {
				if(c.getName().equalsIgnoreCase(cName) || cName.equals("ALL")) {
					c.setPath("/");
					c.setMaxAge(0);
					System.out.println(c.getName());
					System.out.println(c.getValue());
					System.out.println(c.getMaxAge());
					resp.addCookie(c);
				}
			}
		}
	}

}
