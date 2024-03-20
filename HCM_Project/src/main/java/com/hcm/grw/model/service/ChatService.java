package com.hcm.grw.model.service;

import java.util.List;

import com.hcm.grw.dto.ChatDto;

public interface ChatService {

	public int sendMessage(ChatDto dto);
	
	public List<ChatDto> selectAllMessage(ChatDto dto);
	
	public int setReadMessage(String ch_target);
	
	public int noReadList(String ch_target);
	
}
