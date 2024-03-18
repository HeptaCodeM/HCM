package com.hcm.grw.ctrl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.GsonBuilder;
import com.hcm.grw.dto.ChatDto;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.model.service.ChatService;
import com.hcm.grw.model.service.hr.EmployeeListService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ChatController {
	
	@Autowired
	private ChatService service;
	@Autowired
	private EmployeeListService employeeListService;
		
	@PostMapping(value = "sendMessage.do", produces = "text/html; charset=UTF-8")
	public void sendMessage(@RequestBody Map<String, Object> map) {
		log.info("ChatController sendMessage POST 메시지 전송 : {}", map);
		try {
			ChatDto dto = new ChatDto();
			dto.setCh_sender((String)map.get("ch_sender"));
			dto.setCh_target((String)map.get("ch_target"));
			dto.setCh_message((String)map.get("ch_message"));
			service.sendMessage(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping(value = "loadMessage.do", produces = "text/html; charset=UTF-8")
	public ResponseEntity<?> loadMessage(ChatDto dto) {
		log.info("ChatController loadMessage 대화목록 불러오기 : {}", dto);
		List<ChatDto> chatList = service.selectAllMessage(dto);
		return ResponseEntity.ok(new GsonBuilder().create().toJson(chatList));
	}
	
	@GetMapping(value = "chatUserList.do", produces = "text/html; charset=UTF-8")
	public ResponseEntity<?> chatUserList() {
		log.info("ChatController chatUserList 채팅 유저목록 조회");
		List<EmployeeDto> employeeList = employeeListService.selectAllEmployee();
		return ResponseEntity.ok(new GsonBuilder().create().toJson(employeeList));
	}

}
