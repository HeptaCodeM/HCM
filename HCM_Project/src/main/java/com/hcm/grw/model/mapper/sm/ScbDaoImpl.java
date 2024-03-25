package com.hcm.grw.model.mapper.sm;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.sm.ScbDto;

import lombok.extern.slf4j.Slf4j;


@Repository
@Slf4j
public class ScbDaoImpl implements IScbDao {

	
	
	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS = "com.hcm.grw.model.mapper.sm.ScbDaoImpl.";

	@Override
	public List<ScbDto> getAllCalendar(ScbDto dto) {
		log.info("ScbDaoImpl getAllCalendar Dao Access");
		return session.selectList(NS+"getAllCalendar", dto);
	}
	
	@Override
	public ScbDto detailScbo(Map<String, Object> map) {
		log.info("ScbDaoImpl getAllCalendar Dao Access");
		return session.selectOne(NS+"detailScbo", map);
	}
	@Override
	public int insertScbo(ScbDto dto) {
		log.info("ScbDaoImpl getAllReply Dao Access");
		return session.insert(NS+"insertScbo",dto);
	}

	@Override
	public int updateScbo(ScbDto dto) {
		log.info("ScbDaoImpl getAllReply Dao Access");
		return session.update(NS+"updateScbo",dto);
	}

	@Override
	public int updateScboDelFlag(ScbDto dto) {
		log.info("ScbDaoImpl getAllReply Dao Access");
		return session.update(NS+"updateScboDelFlag",dto);
	}
	
	
}
