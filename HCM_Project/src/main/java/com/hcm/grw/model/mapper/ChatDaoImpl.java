package com.hcm.grw.model.mapper;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.ChatDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ChatDaoImpl implements ChatDao {

	@Autowired
	private SqlSessionTemplate template;
	private final String NS = "com.hcm.grw.model.mapper.ChatDaoImpl.";
	
	@Override
	public int sendMessage(ChatDto dto) {
		log.info("ChatDaoImpl sendMessage DAO Access");
		return template.insert(NS + "sendMessage", dto);
	}
	
	@Override
	public List<ChatDto> selectAllMessage(ChatDto dto) {
		log.info("ChatDaoImpl selectAllMessage DAO Access");
		return template.selectList(NS + "selectAllMessage", dto);
	}
	
	@Override
	public int setReadMessage(ChatDto dto) {
		log.info("ChatDaoImpl setReadMessage DAO Access");
		return template.update(NS + "setReadMessage", dto);
	}
	
	@Override
	public int noReadList(ChatDto dto) {
		log.info("ChatDaoImpl noReadList DAO Access");
		return template.selectOne(NS + "noReadList", dto);
	}

	@Override
	public List<ChatDto> chatUserList(String ch_target) {
		log.info("ChatDaoImpl chatUserList DAO Access");
		return template.selectList(NS + "chatUserList", ch_target);
	}

}