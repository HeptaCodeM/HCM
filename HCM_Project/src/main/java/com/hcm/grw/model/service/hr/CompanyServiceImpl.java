package com.hcm.grw.model.service.hr;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.grw.dto.hr.CompanyDto;
import com.hcm.grw.model.mapper.hr.CompanyDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDao dao;

	@Override
	public int insertCompanyInfo(Map<String, Object> map) {
		log.info("CompanyServiceImpl insertCompanyInfo // {}",map);
		return dao.insertCompanyInfo(map);
	}

	@Override
	public int correctionCompanyInfo(CompanyDto dto) {
		log.info("CompanyServiceImpl correctionCompanyInfo // {}",dto);
		return dao.correctionCompanyInfo(dto);
	}
	
	@Override
	public int insertCompanySeal(Map<String, Object> map) {
		log.info("CompanyServiceImpl insertCompanySeal // {}",map);
		return dao.insertCompanySeal(map);
	}

	@Override
	public CompanyDto showCompanyInfo(Map<String, Object> map) {
		log.info("CompanyServiceImpl showCompanyInfo // {}",map);
		return dao.showCompanyInfo(map);
	}
	
	@Override
	public CompanyDto showCompanySeal(Map<String, Object> map) {
		log.info("CompanyServiceImpl showCompanySeal // {}",map);
		return dao.showCompanySeal(map);
	}
	
}
