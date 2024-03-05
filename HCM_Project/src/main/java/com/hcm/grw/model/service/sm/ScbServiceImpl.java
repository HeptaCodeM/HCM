package com.hcm.grw.model.service.sm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.grw.dto.sm.ScbDto;
import com.hcm.grw.model.mapper.sm.IScbDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ScbServiceImpl implements IScbService {
	
	@Autowired
	private IScbDao dao;
	
	@Override
	public int insertScbo(ScbDto dto) {
		log.info("ScbServiceImpl insertScbo Service 일정등록");
		return dao.insertScbo(dto);
	}

	@Override
	public int updateScbo(ScbDto dto) {
		log.info("ScbServiceImpl updateScbo Service 일정수정");
		return dao.updateScbo(dto);
	}

	@Override
	public int updateScboDelFlag(ScbDto dto) {
		log.info("ScbServiceImpl updateScboDelFlag Service 일정삭제");
		return dao.updateScboDelFlag(dto);
	}

	
	
	
	
}
