package com.hcm.grw.comm;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Base64Utils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
	* @param resp : HttpServletResponse
	* @param msg : 메시지(String)
	* @param location : 이동경로(String) - 미필수(빈값 처리 시 메시지창만 띄움)
	* @param className : 버튼 클래스명(String) - 미필수
	* @param btnText : 버튼 텍스트(String) - 미필수
	* @param focus : 포커스이동(String) - 미필수
	* @return : String(메시지 발생 스크립트 호출)
	* @author : SDJ
	 * @throws IOException 
	* @since : 2024.03.06
	*/
	public static void alertLocation(String msg, String location, String className, String btnText, String focus) {
		HttpServletResponse resp = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
		resp.setContentType("text/html; charset=UTF-8;");

		if(msg == "" || msg == null) msg = "";
		if(location == "" || location == null) location = "";
		if(focus == "" || focus == null) focus = "";

		StringBuffer sb = new StringBuffer();
		sb.append("<!DOCTYPE html><html><head>");
		//sb.append("<link href='/assets/plugins/global/plugins.bundle.css' rel='stylesheet' type='text/css' defer/>");
		//sb.append("<link href='/assets/css/style.bundle.css' rel='stylesheet' type='text/css' />");
		//sb.append("<script src='/assets/plugins/global/plugins.bundle.js' defer></script>");
		sb.append("<script src='//cdn.jsdelivr.net/npm/sweetalert2@11'></script>");
		sb.append("<script src='/js/common/common.js' defer></script>");
		sb.append("<script>");
		sb.append("window.onload = function(){");
		sb.append("swalAlert('"+ msg +"','"+ location +"','"+ className +"','"+ btnText +"','"+ focus +"');");
		sb.append("}");
		sb.append("</script>");
		sb.append("</head><body></body></html>");
		try {
			resp.getWriter().print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	* alert 메시지 발생 후 history.back()
	* @param resp : HttpServletResponse
	* @param msg : 메시지(String)
	* @param className : 버튼 클래스명(String) - 미필수
	* @param btnText : 버튼 텍스트(String) - 미필수
	* @return : String(메시지 발생 스크립트 호출)
	* @author : SDJ
	 * @throws IOException 
	* @since : 2024.03.06
	*/
	public static void alertHistoryBack(String msg, String className, String btnText) {
		HttpServletResponse resp = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
		resp.setContentType("text/html; charset=UTF-8;");

		if(msg == "" || msg == null) msg = "";

		StringBuffer sb = new StringBuffer();
		//sb.append("<link href='/assets/plugins/global/plugins.bundle.css' rel='stylesheet' type='text/css'/>");
		//sb.append("<link href='/assets/css/style.bundle.css' rel='stylesheet' type='text/css' />");
		//sb.append("<script src='/assets/plugins/global/plugins.bundle.js' defer></script>");
		sb.append("<script src='//cdn.jsdelivr.net/npm/sweetalert2@11'></script>");
		sb.append("<script src='/js/common/common.js' defer></script>");
		sb.append("<script>");
		sb.append("window.onload = function(){");
		sb.append("swalHistoryBack('"+ msg +"','"+ className +"','"+ btnText +"');");
		sb.append("}");
		sb.append("</script>");
		try {
			resp.getWriter().print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	* alert 메시지 발생 후 self.close() 처리
	* @param resp : HttpServletResponse
	* @param msg : 메시지(String)
	* @param location : 부모창 이동경로(String) - 미필수(빈값 처리 시 메시지창만 띄움)
	* @param className : 버튼 클래스명(String) - 미필수
	* @param btnText : 버튼 텍스트(String) - 미필수
	* @return : String(메시지 발생 스크립트 호출)
	* @author : SDJ
	 * @throws IOException
	* @since : 2024.03.27
	*/
	public static void alertClose(String msg, String location, String className, String btnText) {
		HttpServletResponse resp = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
		resp.setContentType("text/html; charset=UTF-8;");

		if(msg == "" || msg == null) msg = "";

		StringBuffer sb = new StringBuffer();
		sb.append("<script src='//cdn.jsdelivr.net/npm/sweetalert2@11'></script>");
		sb.append("<script src='/js/common/common.js' defer></script>");
		sb.append("<script>");
		sb.append("window.onload = function(){");
		sb.append("swalClose('"+ msg +"','"+ location +"','"+ className +"','"+ btnText +"');");
		sb.append("}");
		sb.append("</script>");
		try {
			resp.getWriter().print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
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
				 base64ToString = "/image/blank.png";
			 }
		 }catch(Exception e) {
			 e.printStackTrace();
			 base64ToString = "/image/blank.png";
		 }

		 return base64ToString;
	}

	
	/**
	* html 템플릿 처리
	* @param templateName : 템플릿명(String)
	* @return : String(이미지 String)
	* #{content} 메일발송내용으로 replace처리
	* @author : SDJ
	* @since : 2024.03.25
	*/	
	public static String getHtmlTemplate(String templateName) {
		
		String content = "";

        // 현재 클래스의 클래스 로더를 통해 리소스 폴더의 절대 경로를 가져옵니다.
        String resourcesPath = Function.class.getClassLoader().getResource("").getPath();
        // 파일의 상대 경로를 지정합니다.
        String relativePath = "template/"+templateName+".html";
        // 리소스 폴더 경로와 상대 경로를 합쳐서 파일 객체를 생성합니다.
        File file = new File(resourcesPath + relativePath);

        try (FileReader reader = new FileReader(file)) {
            char[] buffer = new char[(int) file.length()];
            reader.read(buffer);
            content = new String(buffer);
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
	}

	
	/**
	* 클래스명,메소드명 확인
	* @return : 클래스명.메소드명(String)
	* @author : SDJ
	* @since : 2024.03.27
	*/	
	public static String getMethodName() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		 StackTraceElement callingMethod = stackTrace[2];
		 String className = callingMethod.getClassName();
		 String packageName = className.substring(0, className.lastIndexOf("."));
	     String methodName = callingMethod.getMethodName();
	     
	     return className.replace(packageName.concat("."), "") + "." + methodName;
	}
	
	
	/**
	* IP주소 확인
	* @param req : HttpServletRequest
	* @return : IP주소IPv4(String)
	* @author : SDJ
	* @since : 2024.03.27
	*/	
	public static String getIpAddress() throws Exception {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

		String ipAddr=req.getRemoteAddr();
		if(ipAddr.equalsIgnoreCase("0:0:0:0:0:0:0:1")){
		    InetAddress inetAddress=InetAddress.getLocalHost();
		    ipAddr=inetAddress.getHostAddress();
		}
		
		return ipAddr;
	}
	
	
}