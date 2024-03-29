package com.hcm.grw.model.mapper.hr;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.hr.CommonCodeDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class CommonCodeDaoImpl implements CommonCodeDao {
			
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	private final String NS = "com.hcm.grw.model.mapper.hr.CommonCodeDaoImpl.";
	
	
	@Override
	public List<CommonCodeDto> selectAllRole(Map<String, Object> map) {
		log.info("CommonCodeImpl selectAllRole 진입 // {}",map);
		return sessionTemplate.selectList(NS+"selectAllRole",map);
	}

	@Override
	public int insertRoleOne(Map<String, Object> map) {
		log.info("CommonCodeImpl insertRoleOne 진입 // {}",map);
		return sessionTemplate.insert(NS+"insertRoleOne",map);
	}

	@Override
	public int deleteRoleOne(Map<String, Object> map) {
		log.info("CommonCodeImpl deleteRoleOne 진입 // {}",map);
		return sessionTemplate.update(NS+"deleteRoleOne",map);
	}

	@Override
	public CommonCodeDto selectOneRole(Map<String, Object> map) {
		log.info("CommonCodeImpl selectOneRole 진입 // {}",map);
		return sessionTemplate.selectOne(NS+"selectOneRole",map);
	}

	@Override
	public int correctionRole(Map<String, Object> map) {
		log.info("CommonCodeImpl correctionRole 진입 // {}",map);
		return sessionTemplate.update(NS+"correctionRole",map);
	}

	@Override
	public int roleCodeDuplicateChk(Map<String, Object> map) {
		log.info("CommonCodeImpl roleCodeDuplicateChk 진입 // {}",map);
		return sessionTemplate.selectOne(NS+"roleCodeDuplicateChk",map);
	}

	@Override
	public int roleNameDuplicateChk(Map<String, Object> map) {
		log.info("CommonCodeImpl roleNameDuplicateChk 진입 // {}",map);
		return sessionTemplate.selectOne(NS+"roleNameDuplicateChk",map);
	}	
	
	@Override
	public int delCodeChk(Map<String, Object> map) {
		log.info("CommonCodeImpl delCodeChk 진입 // {}",map);
		return sessionTemplate.selectOne(NS+"delCodeChk",map);
	}

}
