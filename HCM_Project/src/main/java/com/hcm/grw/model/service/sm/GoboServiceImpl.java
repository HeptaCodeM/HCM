package com.hcm.grw.model.service.sm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcm.grw.dto.sm.GoboDto;
import com.hcm.grw.model.mapper.sm.IGoboDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GoboServiceImpl implements IGoboService {

	@Autowired
	private IGoboDao dao;
	
	@Override
	public List<GoboDto> getAllGobo() {
		log.info("GoboServiceImpl getAllGobo Service 공지사항 글 전체조회");
		return dao.getAllGobo();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public GoboDto getDetailGobo(String no) {
		log.info("GoboServiceImpl getDetailGobo Service 공지사항 글 상세조회");
		dao.updateGoboView(no);
		return dao.getDetailGobo(no);
	}


	@Override
	public int insertGobo(GoboDto dto) {
		log.info("GoboServiceImpl insertGobo Service 공지사항 글 작성");
		return dao.insertGobo(dto);
	}

	@Override
	public int updateGoboDelFlag(String no) {
		log.info("GoboServiceImpl updateGoboDelFlag Service 공지사항 글 삭제");
		return dao.updateGoboDelFlag(no);
	}

	@Override
	public int updateGobo(GoboDto dto) {
		log.info("GoboServiceImpl updateGobo Service 공지사항 글 수정");
		return dao.updateGobo(dto);
	}

}
