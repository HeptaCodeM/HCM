package com.hcm.grw.model.service.doc;

import java.util.List;

import com.hcm.grw.dto.doc.TempTreeDto;
import com.hcm.grw.dto.hr.CompanyDto;

public interface ITempTreeService {
	
	public List<TempTreeDto> getTempTree();
	
	public CompanyDto getCompInfo();

}
