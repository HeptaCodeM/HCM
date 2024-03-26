package com.hcm.grw.model.mapper.doc;

import java.util.List;

import com.hcm.grw.dto.doc.TempTreeDto;
import com.hcm.grw.dto.hr.CompanyDto;

public interface ITempTreeDao {
	
	public List<TempTreeDto> getTempTree();
	
	public CompanyDto getCompInfo();

}
