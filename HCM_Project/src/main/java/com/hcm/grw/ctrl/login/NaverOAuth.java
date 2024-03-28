package com.hcm.grw.ctrl.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcm.grw.comm.Function;
import com.hcm.grw.config.CreateNewAuthService;
import com.hcm.grw.model.service.hr.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequestMapping("/api/**")
public class NaverOAuth {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	private CreateNewAuthService authService;

	
	@Value("#{dataSpcProperties['naver.auth']}")
	private String authUrl;
	@Value("#{dataSpcProperties['naver.token']}")
	private String tokenUrl;
	@Value("#{dataSpcProperties['naver.me']}")
	private String meUrl;
	@Value("#{dataSpcProperties['naver.redirect']}")
	private String redirectUrl;
	@Value("#{dataSpcProperties['naver.clientid']}")
	private String clientId;
	@Value("#{dataSpcProperties['naver.clientsecret']}")
	private String clientSecret;

	
	// CSRF 방지를 위한 상태 토큰 생성 코드
	// 상태 토큰은 추후 검증을 위해 세션에 저장되어야 한다.
	public String generateState()
	{
	    SecureRandom random = new SecureRandom();
	    return new BigInteger(130, random).toString(32);
	}
	
	
	@GetMapping("naverCallback.do")
	public @ResponseBody String NaverCallback(String code, 
											  String state) {
		log.info("{} - CallBack code : {}, state : {}", Function.getMethodName(), code, state);
		
		String strToken = this.getToken(code, state);
		if(strToken.isEmpty()) {
			Function.alertClose("토큰정보 오류입니다.", "", "", "");
			return null;
		}
		log.info("strToken : {}", strToken);
		
		Map<String, String> userInfoMap = getUserInfo(strToken);
		if(userInfoMap != null) {
			System.out.println(userInfoMap);
			
			String responseCode = userInfoMap.get("responseCode");
			String resultCode = userInfoMap.get("resultCode");
			String message = userInfoMap.get("message");
			String id = userInfoMap.get("id");
			String email = userInfoMap.get("email");
			
			if(responseCode.equals("200") && resultCode.equals("00")) {
				log.info("{}, {}", id, email);
				procSnsLogin(id, email);	//SNS로그인 처리
			}else {
				Function.alertClose("["+resultCode+"]"+message, "", "", "");
			}
		}else {
			Function.alertClose("사용자정보 오류입니다[1].", "", "", "");
		}
		
		return null;
	}

	
	//Token발급요청
	public String getToken(String code, String state) {
		log.info("{} - Naver 토큰발급 요청", Function.getMethodName());
		
		String rtnToken = "";
	    String redirectURI;
		//Response 한 값을 JSON으로 바꾸기 위함
		ObjectMapper objmapper = new ObjectMapper();

	    try {
			redirectURI = URLEncoder.encode(redirectUrl, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
	    	return "";
		}
	    String tokenURL = tokenUrl
	        + "&client_id=" + clientId
	        + "&client_secret=" + clientSecret
	        + "&redirect_uri=" + redirectURI
	        + "&code=" + code
	        + "&state=" + state;
	    try {
	      URL url = new URL(tokenURL);
	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
	      con.setRequestMethod("GET");
	      int responseCode = con.getResponseCode();
	      BufferedReader br;
	      if (responseCode == 200) { // 정상 호출
	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      } else {  // 에러 발생
	        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	      }
	      String inputLine;
	      StringBuilder res = new StringBuilder();
	      while ((inputLine = br.readLine()) != null) {
	        res.append(inputLine);
	      }
	      br.close();
	      if (responseCode == 200) {
	    	  JsonNode jnode = objmapper.readTree(res.toString());
	    	  rtnToken = jnode.get("access_token").toString();
	      }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return "";
	    }		
		return rtnToken;
	}
	
	
	//사용자 정보 요청
	public Map<String, String> getUserInfo(String strToken) {
		log.info("{} - Naver 사용자 정보 요청", Function.getMethodName());

		Map<String, String> userInfoMap = new HashMap<String, String>();
		try {
			//Response 한 값을 JSON으로 바꾸기 위함
			ObjectMapper objmapper = new ObjectMapper();

			URL url = new URL(meUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "Bearer " + strToken);
			
            Integer resCode = conn.getResponseCode();
            userInfoMap.put("responseCode", String.valueOf(resCode));
            if (resCode == HttpURLConnection.HTTP_OK) {
            	// API 응답 읽기
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder res = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                	res.append(inputLine);
                }
                in.close();

                // JSON 응답 파싱
                JsonNode jnode = objmapper.readTree(res.toString());
                /*
                System.out.println(resCode);
                System.out.println(jnode);
                System.out.println(jnode.get("resultcode").asText());
                System.out.println(jnode.get("message").asText());
                System.out.println(jnode.get("response").get("id").asText());
                System.out.println(jnode.get("response").get("email").asText());
				*/
                userInfoMap.put("resultCode", jnode.get("resultcode").asText());
    	    	userInfoMap.put("message", jnode.get("message").asText());
    	    	if(resCode.equals(200) && jnode.get("resultcode").asText().equals("00")) {
        	    	userInfoMap.put("id", jnode.get("response").get("id").asText());
      				userInfoMap.put("email", jnode.get("response").get("email").asText());
    	    	}
                
                return userInfoMap;

            } else {
                userInfoMap.put("responseCode", String.valueOf(resCode));
                return userInfoMap;
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//SNS로그인 처리
	public void procSnsLogin(@RequestParam(name="id", required = true) String id, 
							 @RequestParam(name="email", required = true) String email) {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		log.info("{} - Naver 사용자 로그인 처리", Function.getMethodName());
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Map<String, String> snsMap = new HashMap<String, String>(){{
			put("emsn_id", id);
			put("emsn_email", email);
		}};
		
		// 간편로그인 정보 확인
		String empl_id = employeeService.getSnsLoginInfo(snsMap);
		if(empl_id != null && empl_id != "") {
			boolean flag = authService.createNewAuthentication(authentication, empl_id, req);
			if(!flag) {
				log.info("{} - 인증등록 오류발생.", Function.getMethodName());
				Function.alertClose("인증 재등록에 실패하였습니다.<br>다시 로그인하여 주세요.", "", "", "");
				return;
			}else {
				Function.alertClose("간편로그인에 성공하였습니다.", "/", "", "");
				return;
			}
		}else if(authentication != null && !authentication.getName().equals("anonymousUser")) {	// Authentication정보가 있다면 등록처리 한다.
			Map<String, Object> registSnsMap = new HashMap<String, Object>(){{
				put("empl_id", authentication.getName());
				put("emsn_id", id);
				put("emsn_email", email);
			}};
			log.info("{}", registSnsMap);
			//간편로그인 등록처리
			int cnt = employeeService.registSnsLoginInfo(registSnsMap);
			if(cnt > 0) {
				// Authentication 등록처리(Session포함)
				boolean flag = authService.createNewAuthentication(authentication, authentication.getName(), req);
				if(!flag) {
					log.info("{} - 인증등록 오류발생(로그인 사용자 자동 등록).", Function.getMethodName());
					Function.alertClose("인증 재등록에 실패하였습니다.<br>다시 로그인하여 주세요.", "", "", "");
					return;
				}else {
					Function.alertClose("네이버 간편로그인에 등록었습니다.", "/hr/employee/naverSns.do", "", "");
					return;
				}

			}else {
				Function.alertClose("네이버 간편로그인 등록에 실패하였습니다.<br>다시 시도하여 주세요.", "", "", "");
			}
		}else {
			Function.alertClose("네이버 간편로그인 정보가 없습니다.<br>로그인 후 간편로그인을 등록하여 주시기 바랍니다.", "", "", "");
		}
		
	}


	public String getAuthUrl() {
		return authUrl;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public String getClientId() {
		return clientId;
	}

}