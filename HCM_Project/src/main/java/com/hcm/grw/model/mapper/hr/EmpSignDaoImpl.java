package com.hcm.grw.model.mapper.hr;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.hr.EmpSignDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class EmpSignDaoImpl implements EmpSignDao {

	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	private final String NS = "com.hcm.grw.model.mapper.hr.EmpSignDaoImpl.";
	
	@Override
	public List<EmpSignDto> selectAllSign() {
		log.info("EmpSignDaoImpl selectAllSign");
		return sessionTemplate.selectList(NS+"selectAllSign");
	}

	@Override
	public int insertSign(Map<String, Object> map) {
		log.info("EmpSignDaoImpl insertSign // {}",map);
		return sessionTemplate.insert(NS+"insertSign",map);
	}

	@Override
	public int deleteSign(Map<String, Object> map) {
		log.info("EmpSignDaoImpl deleteSign // {}",map);
		return sessionTemplate.update(NS+"deleteSign",map);
	}	

}
