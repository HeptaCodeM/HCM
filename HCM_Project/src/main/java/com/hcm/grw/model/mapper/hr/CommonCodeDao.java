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
	
	// ======================= 구분선 ======================= 
	
	public List<CommonCodeDto> selectAllDept();
	
	public int insertDeptOne(Map<String, Object> map);
	
	public int deleteDeptOne(Map<String, Object> map);
	
	public CommonCodeDto selectOneDept(Map<String, Object> map);
	
	public int correctionDept(Map<String, Object> map);
	
	// ======================= 구분선 =======================  
	
	public List<CommonCodeDto> selectAllRank();
	
	public int insertRankOne(Map<String, Object> map);
	
	public int deleteRankOne(Map<String, Object> map);
	
	public CommonCodeDto selectOneRank(Map<String, Object> map);
	
	public int correctionRank(Map<String, Object> map);
	
	// ======================= 구분선 ======================= 
	
	public List<CommonCodeDto> selectAllPosition();
	
	public int insertPositionOne(Map<String, Object> map);
	
	public int deletePositionOne(Map<String, Object> map);
	
	public CommonCodeDto selectOnePosition(Map<String, Object> map);
	
	public int correctionPosition(Map<String, Object> map);
	
	
}
