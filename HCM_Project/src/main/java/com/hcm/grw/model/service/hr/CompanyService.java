package com.hcm.grw.model.service.hr;

import java.util.Map;

import com.hcm.grw.dto.hr.CompanyDto;

public interface CompanyService {

	public int insertCompanyInfo(Map<String, Object> map);
	
	public int correctionCompanyInfo(CompanyDto dto);
	
	public int insertCompanySeal(Map<String, Object> map);
	
	public CompanyDto showCompanyInfo(Map<String, Object> map);
	
	public CompanyDto showCompanySeal(Map<String, Object> map);	
	
}
