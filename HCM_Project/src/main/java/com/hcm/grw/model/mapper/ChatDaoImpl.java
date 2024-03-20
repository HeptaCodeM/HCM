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
	public int setReadMessage(String ch_target) {
		log.info("ChatDaoImpl setReadMessage DAO Access");
		return template.update(NS + "setReadMessage", ch_target);
	}
	
	@Override
	public int noReadList(String ch_target) {
		log.info("ChatDaoImpl noReadList DAO Access");
		return template.selectOne(NS + "noReadList", ch_target);
	}

}
