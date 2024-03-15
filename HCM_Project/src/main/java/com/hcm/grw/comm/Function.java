package com.hcm.grw.comm;

import org.springframework.util.Base64Utils;

import lombok.extern.slf4j.Slf4j;

/**
* 공통함수 클래스
* @author : SDJ
* @since : 2024.01.13
* @version : 1.0
*/
@Slf4j
public class Function {

	/**
	* alert 메시지 발생 후 url이동
	* @param msg : 메시지(String)
	* @param location : 이동경로(String) - 미필수(빈값 처리 시 메시지창만 띄움)
	* @param className : 버튼 클래스명(String) - 미필수
	* @param btnText : 버튼 텍스트(String) - 미필수
	* @param focus : 포커스이동(String) - 미필수
	* @return : String(메시지 발생 스크립트 호출)
	* @author : SDJ
	* @since : 2024.03.06
	*/
	public static String alertLocation(String msg, String location, String className, String btnText, String focus) {
		if(msg == "" || msg == null) msg = "";
		if(location == "" || location == null) location = "";
		if(focus == "" || focus == null) focus = "";

		StringBuffer sb = new StringBuffer();
		sb.append("<script src='//cdn.jsdelivr.net/npm/sweetalert2@11'></script>");
		sb.append("<script src='/js/common.js' defer></script>");
		sb.append("<script>");
		sb.append("window.onload = function(){");
		sb.append("swalAlert('"+ msg +"','"+ location +"','"+ className +"','"+ btnText +"','"+ focus +"');");
		sb.append("}");
		sb.append("</script>");
		return sb.toString();
	}

	/**
	* alert 메시지 발생 후 history.back()
	* @param msg : 메시지(String)
	* @param className : 버튼 클래스명(String) - 미필수
	* @param btnText : 버튼 텍스트(String) - 미필수
	* @return : String(메시지 발생 스크립트 호출)
	* @author : SDJ
	* @since : 2024.03.06
	*/
	public static String alertHistoryBack(String msg, String className, String btnText) {
		if(msg == "" || msg == null) msg = "";

		StringBuffer sb = new StringBuffer();
		sb.append("<script src='//cdn.jsdelivr.net/npm/sweetalert2@11'></script>");
		sb.append("<script src='/js/common.js' defer></script>");
		sb.append("<script>");
		sb.append("window.onload = function(){");
		sb.append("swalAlert('"+ msg +"','"+ className +"','"+ btnText +"');");
		sb.append("}");
		sb.append("</script>");
		return sb.toString();
	}
	
	/**
	* Blob데이터 String처리
	* @param b : Blob데이터(byte[])
	* @return : String(이미지 String)
	* <br>b Parameter가 없는 경우 default 값(/images/blank.png) 처리
	* <br>ex) &lt;img src="return String값"&gt;
	* @author : SDJ
	* @since : 2024.03.06
	*/	
	public static String blobImageToString(byte[] b) {
		 String base64ToString = "";
		 try {
			 if(b != null) {
				 base64ToString = "data:image/png;base64,"+Base64Utils.encodeToString(b);
			 }else {
				 base64ToString = "/images/blank.png";
			 }
		 }catch(Exception e) {
			 e.printStackTrace();
			 base64ToString = "/images/blank.png";
		 }

		 return base64ToString;
	}
	
}