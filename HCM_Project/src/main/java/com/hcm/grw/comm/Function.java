package com.hcm.grw.comm;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import lombok.extern.slf4j.Slf4j;

/**
* 공통함수 클래스
* @author : SDJ
* @since : 2024.01.13
* @version : 1.0
*/
@Slf4j
public class Function {

//	@Autowired
//	private JavaMailSender mailSender;
	
	/**
	* alert 메시지 발생 후 url이동
	* @param : 메시지
	* @param : 이동경로(미필수)
	* @param : 포커스이동(미필수)
	* @return : String(메시지 발생 스크립트 호출)
	* @author : SDJ
	* @since : 2024.03.06
	*/
	public static String alertLocation(String msg, String location, String className, String btnName, String focus) {
		if(msg == "" || msg == null) msg = "";
		if(location == "" || location == null) location = "";
		if(focus == "" || focus == null) focus = "";

		StringBuffer sb = new StringBuffer();
		sb.append("<script src='//cdn.jsdelivr.net/npm/sweetalert2@11'></script>");
		sb.append("<script src='/js/common.js' defer></script>");
		sb.append("<script>");
		sb.append("window.onload = function(){");
		sb.append("swalAlert('"+ msg +"','"+ location +"','"+ className +"','"+ btnName +"','"+ focus +"');");
		sb.append("}");
		sb.append("</script>");
		return sb.toString();
	}

	/**
	* alert 메시지 발생 후 history.back()
	* @param : 메시지
	* @return : String(메시지 발생 스크립트 호출)
	* @author : SDJ
	* @since : 2024.03.06
	*/
	public static String alertHistoryBack(String msg, String className, String btnName) {
		if(msg == "" || msg == null) msg = "";

		StringBuffer sb = new StringBuffer();
		sb.append("<script src='//cdn.jsdelivr.net/npm/sweetalert2@11'></script>");
		sb.append("<script src='/js/common.js' defer></script>");
		sb.append("<script>");
		sb.append("window.onload = function(){");
		sb.append("swalAlert('"+ msg +"','"+ className +"','"+ btnName +"');");
		sb.append("}");
		sb.append("</script>");
		return sb.toString();
	}


	
	/**
	* 메일발송 Method
	* @param : 제목
	* @param : 내용
	* @param : 수신자 메일주소
	* @param : 발신자 메일주소
	* @return : Boolean(메일발송 Flag값)
	* @author : SDJ
	* @since : 2024.03.12
	*/
//	public Boolean sendMail(String subject, String content, String toEmail, String fromEmail) {
//		try {
//
//			MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
//            messageHelper.setSubject(subject);	// 메일제목은 생략이 가능하다			
//            messageHelper.setText(content);
//            messageHelper.setTo(toEmail);
//            messageHelper.setFrom(fromEmail);
//			
//            mailSender.send(message);
//		}catch(Exception ex) {
//			log.info("EMail Send Error : {}", ex.getMessage());
//			return false;
//		}
//		
//		return true;
//	}

}