package com.hcm.grw.model.mapper.hr;

import java.util.Map;

import com.hcm.grw.dto.hr.CompanyDto;

public interface ICompanyDao {

	public int insertCompanyInfo(Map<String, Object> map);
	
	public int correctionCompanyInfo(Map<String, Object> map);
	
	public CompanyDto showCompanyInfo(Map<String, Object> map);

}
