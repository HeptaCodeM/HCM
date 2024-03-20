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
	
	@Override
	public int setReadMessage(String ch_target) {
		log.info("ChatServiceImpl setReadMessage Service 읽음처리");
		return dao.setReadMessage(ch_target);
	}
	
	@Override
	public int noReadList(String ch_target) {
		log.info("ChatServiceImpl noReadList Service 안읽은 목록 수");
		return dao.noReadList(ch_target);
	}

}
