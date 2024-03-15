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
	public List<EmpSignDto> selectAllSign(Map<String, Object> map) {
		log.info("EmpSignDaoImpl selectAllSign // {}",map);
		return sessionTemplate.selectList(NS+"selectAllSign" , map);
	}

	@Override
	public int insertSign(Map<String, Object> map) {
		log.info("EmpSignDaoImpl insertSign // {}",map);
		return sessionTemplate.insert(NS+"insertSign",map);
	}
	
	@Override
	public int setDefaultSign(Map<String, Object> map) {
		log.info("EmpSignDaoImpl setDefaultSign // {}",map);
		return sessionTemplate.update(NS+"setDefaultSign",map);
	}

	@Override
	public int deleteSign(Map<String, Object> map) {
		log.info("EmpSignDaoImpl deleteSign // {}",map);
		return sessionTemplate.update(NS+"deleteSign",map);
	}

	@Override
	public List<EmpSignDto> defaultChk(Map<String, Object> map) {
		log.info("EmpSignDaoImpl defaultChk // {}",map);
		return sessionTemplate.selectList(NS+"defaultChk",map);
	}

	@Override
	public int setUnDefaultSign(Map<String, Object> map) {
		log.info("EmpSignDaoImpl setUnDefaultSign // {}",map);
		return sessionTemplate.update(NS+"setUnDefaultSign",map);
	}	

	@Override
	public int setAllDefaultSign(Map<String, Object> map) {
		log.info("EmpSignDaoImpl setAllDefaultSign // {}",map);
		return sessionTemplate.update(NS+"setAllDefaultSign",map);
	}
	
	@Override
	public int unSetDefaultSign(Map<String, Object> map) {
		log.info("EmpSignDaoImpl unSetDefaultSign DAO Access");
		return sessionTemplate.update(NS + "unSetDefaultSign", map);
	}
	
}
