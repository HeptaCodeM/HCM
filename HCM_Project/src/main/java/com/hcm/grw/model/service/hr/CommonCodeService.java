package com.hcm.grw.model.service.hr;

import java.util.List;
import java.util.Map;

import com.hcm.grw.dto.hr.CommonCodeDto;

public interface CommonCodeService {

	public List<CommonCodeDto> selectAllRole(Map<String, Object> map);
	
	public int insertRoleOne(Map<String, Object> map);
	
	public int deleteRoleOne(Map<String, Object> map);
	
	public CommonCodeDto selectOneRole(Map<String, Object> map);
	
	public int correctionRole(Map<String, Object> map);	

	public int roleCodeDuplicateChk(Map<String, Object> map);
	
	public int roleNameDuplicateChk(Map<String, Object> map);
	
	public int delCodeChk(Map<String, Object> map);	
}
