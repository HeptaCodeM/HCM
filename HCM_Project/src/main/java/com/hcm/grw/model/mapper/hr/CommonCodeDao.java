package com.hcm.grw.model.mapper.hr;

import java.util.List;
import java.util.Map;

import com.hcm.grw.dto.hr.CommonCodeDto;

public interface CommonCodeDao {

	public List<CommonCodeDto> selectAllRole(Map<String, Object> map);
	
	public int insertRoleOne(Map<String, Object> map);
	
	public int deleteRoleOne(Map<String, Object> map);
	
	public CommonCodeDto selectOneRole(Map<String, Object> map);
	
	public int correctionRole(Map<String, Object> map);	
	
	public CommonCodeDto roleCodeDuplicateChk(Map<String, Object> map);
	
	public CommonCodeDto roleNameDuplicateChk(Map<String, Object> map);
	
}
