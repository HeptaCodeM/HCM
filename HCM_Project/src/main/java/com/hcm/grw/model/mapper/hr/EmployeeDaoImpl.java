package com.hcm.grw.model.mapper.hr;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.dto.hr.SnsInfoDto;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private String NS = "com.hcm.grw.model.mapper.hr.EmployeeDaoImpl";
	
	@Override
	public EmployeeDto getLogin(String empl_id) {
		
		return sqlSessionTemplate.selectOne(NS+"getLogin", empl_id);
	}

	@Override
	public SnsInfoDto getSnsLoginInfo(String empl_id) {
		
		return sqlSessionTemplate.selectOne(NS+"getSnsLoginInfo", empl_id);
	}

	@Override
	public int getInitPwdcheck(Map<String, Object> map) {
		
		return sqlSessionTemplate.selectOne(NS+"getInitPwdcheck", map);
	}

	@Override
	public int setInitPwd(Map<String, Object> map) {
		
		return sqlSessionTemplate.update(NS+"setInitPwd", map);
	}

	@Override
	public int registEmployee(EmployeeDto dto) {
		
		return sqlSessionTemplate.insert(NS+"registEmployee", dto);
	}

	@Override
	public int updateEmployee(EmployeeDto dto) {
		
		return sqlSessionTemplate.update(NS+"updateEmployee", dto);
	}

	@Override
	public List<EmployeeDto> getAuthList() {
		
		return sqlSessionTemplate.selectList(NS+"getAuthList");
	}

	@Override
	public EmployeeDto getAuthDetail(String empl_id) {
		
		return sqlSessionTemplate.selectOne(NS+"getAuthDetail", empl_id);
	}

	@Override
	public int updateAuthEmployee(Map<String, Object> map) {
		
		return sqlSessionTemplate.update(NS+"updateAuthEmployee", map);
	}

	@Override
	public SnsInfoDto getSnsInfo(String empl_id) {
		
		return sqlSessionTemplate.selectOne(NS+"getSnsInfo", empl_id);
	}

	@Override
	public int delSnsInfo(String empl_id) {
		
		return sqlSessionTemplate.delete(NS+"delSnsInfo", empl_id);
	}

	@Override
	public int registSnsLoginInfo(Map<String, Object> map) {
		
		return sqlSessionTemplate.insert(NS+"registSnsLoginInfo", map);
	}

	@Override
	public int updatePwd(Map<String, Object> map) {
		
		return sqlSessionTemplate.update(NS+"updatePwd", map);
	}

}
