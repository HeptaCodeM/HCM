package com.hcm.grw.model.service.hr;

import java.util.List;
import java.util.Map;

import com.hcm.grw.dto.hr.EmployeeDto;

public interface EmployeeListService {

	public List<EmployeeDto> selectAllEmployee();
	
	public List<EmployeeDto> searchAllEmployee(Map<String, Object> map);
	
	public EmployeeDto selectOneEmployee(Map<String, Object> map);
	
	public int correctionEmployee(Map<String, Object> map);
	
	public boolean chkEmpPhoneNum(String empl_phone);

	public boolean chkEmpTelNum(String empl_tel);
	
	public boolean chkEmpFaxNum(String empl_fax);
	
}