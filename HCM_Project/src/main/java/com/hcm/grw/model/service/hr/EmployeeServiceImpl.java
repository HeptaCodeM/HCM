package com.hcm.grw.model.service.hr;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.dto.hr.SnsInfoDto;
import com.hcm.grw.model.mapper.hr.EmployeeDao;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao dao;
	
	@Override
	public EmployeeDto getLogin(String empl_id) {
		return dao.getLogin(empl_id);
	}

	@Override
	public SnsInfoDto getSnsLoginInfo(String empl_id) {
		return dao.getSnsInfo(empl_id);
	}

	@Override
	public int getInitPwdcheck(Map<String, Object> map) {
		return dao.getInitPwdcheck(map);
	}

	@Override
	public int setInitPwd(Map<String, Object> map) {
		return dao.setInitPwd(map);
	}

	@Override
	public int registEmployee(EmployeeDto dto) {
		return dao.registEmployee(dto);
	}

	@Override
	public int updateEmployee(EmployeeDto dto) {
		return dao.updateEmployee(dto);
	}

	@Override
	public List<EmployeeDto> getAuthList() {
		return dao.getAuthList();
	}

	@Override
	public EmployeeDto getAuthDetail(String empl_id) {
		return dao.getAuthDetail(empl_id);
	}

	@Override
	public int updateAuthEmployee(Map<String, Object> map) {
		return dao.updateAuthEmployee(map);
	}

	@Override
	public SnsInfoDto getSnsInfo(String empl_id) {
		return dao.getSnsInfo(empl_id);
	}

	@Override
	public int delSnsInfo(String empl_id) {
		return dao.delSnsInfo(empl_id);
	}

	@Override
	public int registSnsLoginInfo(Map<String, Object> map) {
		return dao.registSnsLoginInfo(map);
	}

	@Override
	public int updatePwd(Map<String, Object> map) {
		return dao.updatePwd(map);
	}

}
