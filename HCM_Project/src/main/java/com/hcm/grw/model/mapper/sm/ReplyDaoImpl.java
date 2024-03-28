package com.hcm.grw.model.mapper.sm;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.sm.ReplyDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ReplyDaoImpl implements IReplyDao {

	
	
	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS = "com.hcm.grw.model.mapper.sm.ReplyDaoImpl.";

	@Override
	public List<ReplyDto> getAllReply(String no) {
		log.info("ReplyDaoImpl getAllReply Dao Access");
		return session.selectList(NS+"getAllReply",no);
	}
	
	@Override
	public String maxNo(String no) {
		log.info("ReplyDaoImpl getAllReply Dao Access");
		return session.selectOne(NS+"maxNo",no);
	}
	@Override
	public List<ReplyDto> getAllReplyTwo(String no) {
		log.info("ReplyDaoImpl getAllReply Dao Access");
		return session.selectList(NS+"getAllReplyTwo",no);
	}

	@Override
	public int insertReply(ReplyDto dto) {
		log.info("ReplyDaoImpl insertReply Dao Access");
		return session.insert(NS+"insertReply",dto);
	}

	@Override
	public int insertReplyTwo(ReplyDto dto) {
		log.info("ReplyDaoImpl insertReplyTwo Dao Access");
		return session.insert(NS+"insertReplyTwo",dto);
	}

	@Override
	public int updateReply(ReplyDto dto) {
		log.info("ReplyDaoImpl updateReply Dao Access");
		return session.update(NS+"updateReply",dto);
	}

	@Override
	public int updateReplyDelFlag(String no) {
		log.info("ReplyDaoImpl updateReplyDelFlag Dao Access");
		return session.update(NS+"updateReplyDelFlag",no);
	}
	
}