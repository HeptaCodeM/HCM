package com.hcm.grw.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.grw.dto.ChatDto;
import com.hcm.grw.model.mapper.ChatDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatDao dao;
	
	@Override
	public int sendMessage(ChatDto dto) {
		log.info("ChatServiceImpl sendMessage Service 채팅 전송");
		return dao.sendMessage(dto);
	}
	
	@Override
	public List<ChatDto> selectAllMessage(ChatDto dto) {
		log.info("ChatServiceImpl selectAllMessage Service 대화목록");
		return dao.selectAllMessage(dto);
	}

}