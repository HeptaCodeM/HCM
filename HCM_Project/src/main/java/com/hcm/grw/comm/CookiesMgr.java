package com.hcm.grw.comm;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	* @param : HttpServletResponse
	* @param : 쿠키명
	* @param : 쿠키값
	* @return : void
	* @author : SDJ
	* @since : 2024.03.06
	*/
	/* setCookies */
	public static void setCookies(HttpServletResponse rep, String cName, String cValue) {
		rep.setContentType("text/html;charset=UTF-8");
		
		// 쿠키생성
		String encValue="";
		try {
			encValue = Cryption.aes256Encrypt(cValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Cookie hcmCookies = new Cookie(cName, encValue);
		log.info("cookies 암호화 값 : {}", encValue);

		// setDomain : 쿠키 도메인 설정(도메인 주소 http, https)
		// setPath : 쿠키 경로 지정
		// setMaxAge : 쿠키 유효 시간(초*분)
		// setSecure : 쿠키 연결시 보안인증된 도메인 접속만 // true => https
		hcmCookies.setDomain("localhost");
		hcmCookies.setPath("/");
		hcmCookies.setMaxAge(60*20);
		hcmCookies.setSecure(false);
		rep.addCookie(hcmCookies);
	}

	/**
	* 쿠키값 출력
	* @param : HttpServletRequest
	* @param : 쿠키명
	* @return : String
	* @author : SDJ
	* @since : 2024.03.06
	*/
	/* getCookies */
	public static String getCookies(HttpServletRequest req, String cName) {
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
	* @param : HttpServletRequest
	* @param : HttpServletResponse
	* @return : void
	* @author : SDJ
	* @since : 2024.03.06
	*/
	/* delCookies */
	public static void delCookies(HttpServletRequest req, HttpServletResponse res) {
		Cookie[] loginCookie = req.getCookies();
		if(loginCookie != null) {
			for(Cookie c : loginCookie) {
				c.setPath("/");
				c.setMaxAge(0);
				System.out.println(c.getName());
				System.out.println(c.getValue());
				System.out.println(c.getMaxAge());
				res.addCookie(c);
			}
		}
	}

}
