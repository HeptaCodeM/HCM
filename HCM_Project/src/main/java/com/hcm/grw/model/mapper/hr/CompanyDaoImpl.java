package com.hcm.grw.model.mapper.hr;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.hr.CompanyDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class CompanyDaoImpl implements CompanyDao {

	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	private final String NS = "com.hcm.grw.model.mapper.hr.CompanyDaoImpl.";
	
	@Override
	public int insertCompanyInfo(Map<String, Object> map) {
		log.info("CompanyDaoImpl insertCompanyInfo // {}",map);
		return sessionTemplate.insert(NS+"insertCompanyInfo",map);
	}

	@Override
	public int correctionCompanyInfo(Map<String, Object> map) {
		log.info("CompanyDaoImpl correctionCompanyInfo // {}",map);
		return sessionTemplate.update(NS+"correctionCompanyInfo", map);
	}

	@Override
	public CompanyDto showCompanyInfo(Map<String, Object> map) {
		log.info("CompanyDaoImpl showCompanyInfo // {}",map);
		return sessionTemplate.selectOne(NS+"showCompanyInfo",map);
	}

}
