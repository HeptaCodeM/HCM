package com.hcm.grw.model.service;

import java.util.List;

import com.hcm.grw.dto.ChatDto;

public interface ChatService {

	public int sendMessage(ChatDto dto);
	
	public List<ChatDto> selectAllMessage(ChatDto dto);
	
	public int setReadMessage(ChatDto dto);
	
	public int noReadList(ChatDto dto);
	
	public List<ChatDto> chatUserList(String ch_target);
	
}
