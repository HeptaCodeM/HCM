package com.hcm.grw.model.mapper.hr;

import java.util.List;
import java.util.Map;

import com.hcm.grw.dto.hr.EmpSignDto;

public interface IEmpSignDao {

	public List<EmpSignDto> selectAllSign();
	
	public int insertSign(Map<String, Object> map);
	
	public int deleteSign(Map<String, Object> map);
	
}
