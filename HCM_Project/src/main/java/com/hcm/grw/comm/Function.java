package com.hcm.grw.comm;

/**
* 공통함수 클래스
* @author : SDJ
* @since : 2024.01.13
* @version : 1.0
*/
public class Function {

	
	/**
	* alert 메시지 발생 후 url이동
	* @param : 메시지
	* @param : 이동경로(미필수)
	* @param : 포커스이동(미필수)
	* @return : String(메시지 발생 스크립트 호출)
	* @author : SDJ
	* @since : 2024.03.06
	*/
	public static String alertLocation(String msg, String location, String focus) {
		if(msg == "" || msg == null) msg = "";
		if(location == "" || location == null) location = "";
		if(focus == "" || focus == null) focus = "";

		StringBuffer sb = new StringBuffer();
		sb.append("<script src='//cdn.jsdelivr.net/npm/sweetalert2@11'></script>");
		sb.append("<script src='/js/common.js' defer></script>");
		sb.append("<script>");
		sb.append("window.onload = function(){");
		sb.append("swalAlert('"+ msg +"','"+ location +"','"+ focus +"');");
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
	public static String alertHistoryBack(String msg) {
		if(msg == "" || msg == null) msg = "";

		StringBuffer sb = new StringBuffer();
		sb.append("<script src='//cdn.jsdelivr.net/npm/sweetalert2@11'></script>");
		sb.append("<script src='/js/common.js' defer></script>");
		sb.append("<script>");
		sb.append("window.onload = function(){");
		sb.append("swalAlert('"+ msg +"');");
		sb.append("}");
		sb.append("</script>");
		return sb.toString();
	}

}