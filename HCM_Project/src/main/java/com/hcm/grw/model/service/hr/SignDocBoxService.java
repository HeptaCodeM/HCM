package com.hcm.grw.model.service.hr;

import java.util.List;
import java.util.Map;

import com.hcm.grw.dto.hr.SignDocBoxDto;

public interface SignDocBoxService {

	public List<SignDocBoxDto> selectAllDocList(Map<String, Object> map);
	
	public SignDocBoxDto selectOneDocList(Map<String, Object> map);
	
	public int downloadOneDoc(Map<String, Object> map);
	
	public List<SignDocBoxDto> selectAllDownloadDocList(Map<String, Object> map);
}
