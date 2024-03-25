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
	public int setReadMessage(ChatDto dto) {
		log.info("ChatServiceImpl setReadMessage Service 읽음처리");
		return dao.setReadMessage(dto);
	}
	
	@Override
	public int noReadList(ChatDto dto) {
		log.info("ChatServiceImpl noReadList Service 안읽은 목록 수");
		return dao.noReadList(dto);
	}

	@Override
	public List<ChatDto> chatUserList(ChatDto dto) {
		log.info("ChatServiceImpl chatUserList Service 정렬을 위한 유저 목록 조회");
		return dao.chatUserList(dto);
	}

	@Override
	public List<String> userList(String empl_id) {
		log.info("ChatServiceImpl userList Service 본인을 제외한 모든 유저 아이디 가져오기");
		return dao.userList(empl_id);
	}

}
