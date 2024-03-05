package com.hcm.grw.model.mapper.sm;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.sm.GoboDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class GoboDaoImpl implements IGoboDao {

	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS = "com.hcm.grw.model.mapper.sm.GoboDaoImpl.";
	
	
	
	
	@Override
	public List<GoboDto> getAllGobo() {
		log.info("GoboDaoImpl getAllGobo Dao Access");
		return session.selectList(NS+"getAllGobo");
	}

	@Override
	public GoboDto getDetailGobo(String no) {
		log.info("GoboDaoImpl getDetailGobo Dao Access");
		return session.selectOne(NS+"getDetailGobo", no);
	}

	@Override
	public int updateGoboView(String no) {
		log.info("GoboDaoImpl updateGoboView Dao Access");
		return session.update(NS+"updateGoboView",no);
	}

	@Override
	public int insertGobo(GoboDto dto) {
		log.info("GoboDaoImpl insertGobo Dao Access");
		return session.insert(NS+"insertGobo",dto);
	}

	@Override
	public int updateGoboDelFlag(String no) {
		log.info("GoboDaoImpl updateGoboDelFlag Dao Access");
		return session.update(NS+"updateGoboDelFlag",no);
	}

	@Override
	public int updateGobo(GoboDto dto) {
		log.info("GoboDaoImpl updateGobo Dao Access");
		return session.update(NS+"updateGobo",dto);
	}

	
	
	
}
