package com.hcm.grw.model.mapper.hr;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.hr.AuthDto;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.dto.hr.SnsInfoDto;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private String NS = "com.hcm.grw.model.mapper.hr.EmployeeDaoImpl.";
	
	@Override
	public EmployeeDto getLogin(String empl_id) {
		return sqlSessionTemplate.selectOne(NS+"getLogin", empl_id);
	}

	@Override
	public int insertLoginHistory(Map<String, String> map) {
		return sqlSessionTemplate.insert(NS+"insertLoginHistory", map);
	}
	
	@Override
	public EmployeeDto getUserInfo(String empl_id) {
		return sqlSessionTemplate.selectOne(NS+"getUserInfo", empl_id);
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

	@Override
	public List<AuthDto> selectAuthAllList() {
		return sqlSessionTemplate.selectList(NS+"selectAuthAllList");
	}

	@Override
	public List<EmployeeDto> getUserInfoSearch(Map<String, String> map) {
		return sqlSessionTemplate.selectList(NS+"getUserInfoSearch", map);
	}

	public int duplicationEmpEmail(String empl_email) {
		return sqlSessionTemplate.selectOne(NS+"duplicationEmpEmail", empl_email);
	}
	
	
//	======================= 구분선 ===================================	
	
	@Override
	public List<EmployeeDto> selectAllEmployee() {
		return sqlSessionTemplate.selectList(NS+"selectAllEmployee");
	}

	@Override
	public List<EmployeeDto> searchAllEmployee(Map<String, Object> map) {
		return sqlSessionTemplate.selectList(NS+"searchAllEmployee",map);
	}

	@Override
	public EmployeeDto selectOneEmployee(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne(NS+"selectOneEmployee",map);
	}

	@Override
	public int correctionEmployee(Map<String, Object> map) {
		return sqlSessionTemplate.update(NS+"correctionEmployee",map);
	}

	@Override
	public boolean chkEmpPhoneNum(String empl_phone) {
		int cnt = sqlSessionTemplate.selectOne(NS+"chkEmpPhoneNum",empl_phone);
		return (cnt > 0)?false:true;
	}

	@Override
	public boolean chkEmpTelNum(String empl_tel) {
		int cnt = sqlSessionTemplate.selectOne(NS+"chkEmpTelNum",empl_tel);
		return (cnt > 0)?false:true;
	}

	@Override
	public boolean chkEmpFaxNum(String empl_fax) {
		int cnt = sqlSessionTemplate.selectOne(NS+"chkEmpFaxNum",empl_fax);
		return (cnt > 0)?false:true;
	}

}
