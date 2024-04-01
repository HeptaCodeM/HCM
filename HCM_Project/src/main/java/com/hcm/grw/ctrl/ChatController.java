package com.hcm.grw.ctrl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.GsonBuilder;
import com.hcm.grw.dto.ChatDto;
import com.hcm.grw.model.service.ChatService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ChatController {
	
	@Autowired
	private ChatService service;
		
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
		log.info("ChatController loadMessage 대화목록 불러오기 : {}", dto.getCh_target());
		List<ChatDto> chatList = service.selectAllMessage(dto);
		for (ChatDto chatDto : chatList) {
			if(chatDto.getSender_pic() != null) {
				chatDto.setSender_pic_str(Base64Utils.encodeToString(chatDto.getSender_pic()));
				chatDto.setSender_pic(null);
			}
		}
		return ResponseEntity.ok(new GsonBuilder().create().toJson(chatList));
	}
	
	@GetMapping(value = "chatUserList.do", produces = "text/html; charset=UTF-8")
	public ResponseEntity<?> chatUserList(@RequestParam String ch_target) {
		log.info("ChatController chatUserList 채팅 유저목록 조회");
		List<ChatDto> list = service.chatUserList(ch_target);
		for(ChatDto dto : list) {
			if(dto.getSender_pic() != null) {
				dto.setSender_pic_str(Base64Utils.encodeToString (dto.getSender_pic()));
				dto.setSender_pic(null);
			}
		}
		return ResponseEntity.ok(new GsonBuilder().create().toJson(list));
	}

	@GetMapping("chatCount.do")
	public ResponseEntity<?> chatCount(ChatDto dto) {
		log.info("ChatController chatCount 안읽은 대화수 조회");
		int n = service.noReadList(dto);
		return ResponseEntity.ok(n);
	}
	
	@GetMapping("setReadMessage.do")
	public void setReadMessage(ChatDto dto) {
		log.info("ChatController setReadMessage 메세지 읽음 처리");
		service.setReadMessage(dto);
	}
	
}