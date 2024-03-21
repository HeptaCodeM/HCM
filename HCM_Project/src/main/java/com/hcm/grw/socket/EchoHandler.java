package com.hcm.grw.socket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.hcm.grw.dto.ChatDto;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.model.service.hr.EmployeeService;

import lombok.extern.slf4j.Slf4j;



@Component(value = "hcmWs.do")
@Slf4j
public class EchoHandler extends TextWebSocketHandler {
	
	@Autowired
	private EmployeeService service;
	
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	private Map<String, WebSocketSession> userSessionsMap = new HashMap<String, WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		sessionList.add(session);
		String empl_id = userInfo(session);
		userSessionsMap.put(empl_id, session);
		log.info("접속한 사원 : {}", userInfo(session));
		log.info("접속중인 사원 수 : {}", sessionList.size());
		
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		String fullMsg = message.getPayload();
		String msgTostring = message.toString();
		log.info("전달된 TextMessage getPayload : {}", fullMsg);
		log.info("전달된 TextMessage toString : {}", msgTostring);
		
		for (WebSocketSession clientSession : sessionList) {
			if (clientSession.isOpen() && !clientSession.equals(session)) {
				try {
					// 본인 제외 전체 푸쉬
//					clientSession.sendMessage(new TextMessage("노티피케이션!"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		String[] msgArr = fullMsg.split(",");
		String sender = msgArr[0];
		String target = msgArr[1];
		String msg = msgArr[2];
		
		EmployeeDto user = service.getUserInfo(sender);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		
		ChatDto chatDto = new ChatDto();
		chatDto.setCh_date(time);
		chatDto.setSender_name(user.getEmpl_name());
		chatDto.setCh_message(msg);
		chatDto.setCh_sender(user.getEmpl_id());
		if(user.getEmpl_picture() != null) {
			chatDto.setSender_pic(Base64Utils.encodeToString(user.getEmpl_picture()));
		}
		String json = new Gson().toJson(chatDto);
		
		if(userSessionsMap.get(target) != null) {
			// 대상이 접속중인 경우
			userSessionsMap.get(target).sendMessage(new TextMessage(user.getEmpl_name() + "님으로 부터 메세지 도착\n" + msg));
			userSessionsMap.get(target).sendMessage(new TextMessage(json));
		} else {	
			// 비접속중인경우
		}
		
		log.info(session.getId());
		
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		for(WebSocketSession logoutUser : sessionList) {
			if(logoutUser != session) {
				logoutUser.sendMessage(new TextMessage(new Gson().toJson("접속 해제")));
			}
		}
		
		sessionList.remove(session);
		
	}
	
	private String userInfo(WebSocketSession session) {
		Map<String, Object> httpSession = session.getAttributes();
		EmployeeDto loginEmp = (EmployeeDto)httpSession.get("userInfoVo");
		String emp = "";
		if(loginEmp == null) {
			emp = session.getId();
			return emp;
		}
		emp = loginEmp.getEmpl_id();
		return emp;
		
	}

}
