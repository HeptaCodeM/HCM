package com.hcm.grw.model.service.sm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.grw.dto.sm.ReplyDto;
import com.hcm.grw.model.mapper.sm.IReplyDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReplyServiceImpl implements IReplyService {
	
	@Autowired
	private IReplyDao dao;
	
	
	@Override
	public List<ReplyDto> getAllReply(String no) {
		log.info("ReplyServiceImpl getAllReply Service 공지사항 댓글 전체 조회");
		return dao.getAllReply(no);
	}
	
	@Override
	public List<ReplyDto> getAllReplyTwo(String no) {
		log.info("ReplyServiceImpl getAllReply Service 공지사항 대댓글 전체 조회");
		return dao.getAllReplyTwo(no);
	}

	
	@Override
	public String maxNo(String no) {
		log.info("ReplyServiceImpl getAllReply Service 공지사항 댓글 Max SCBO_NO 조회");
		return dao.maxNo(no);
	}
	@Override
	public int insertReply(ReplyDto dto) {
		log.info("ReplyServiceImpl insertReply Service 공지사항 댓글 등록");
		return dao.insertReply(dto);
	}

	@Override
	public int insertReplyTwo(ReplyDto dto) {
		log.info("ReplyServiceImpl insertReplyTwo Service 공지사항 대댓글 등록");
		return dao.insertReplyTwo(dto);
	}

	@Override
	public int updateReply(ReplyDto dto) {
		log.info("ReplyServiceImpl updateReply Service 공지사항 댓글 업데이트");
		return dao.updateReply(dto);
	}

	@Override
	public int updateReplyDelFlag(String no) {
		log.info("ReplyServiceImpl updateReplyDelFlag Service 공지사항 댓글 삭제");
		return dao.updateReplyDelFlag(no);
	}
	
	

}
