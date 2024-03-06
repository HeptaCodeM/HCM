package com.hcm.grw.comm;

public class Function {
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