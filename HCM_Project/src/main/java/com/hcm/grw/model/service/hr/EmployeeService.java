package com.hcm.grw.model.service.hr;

import java.util.List;
import java.util.Map;

import com.hcm.grw.dto.hr.AuthDto;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.dto.hr.SnsInfoDto;

public interface EmployeeService {

	/*일반로그인*/
	public EmployeeDto getLogin(String empl_id);
	/*유저정보*/
	public EmployeeDto getUserInfo(String empl_id);
	/*유저검색*/
	public List<EmployeeDto> getUserInfoSearch(Map<String, String> map);
	/*소셜로그인*/
	public SnsInfoDto getSnsLoginInfo(String empl_id);

	/*인증번호 메일발송*/
	public int getInitPwdcheck(Map<String, Object> map);
	/*비밀번호 초기화 처리*/
	public int setInitPwd(Map<String, Object> map);
	/*사원등록*/
	public int registEmployee(EmployeeDto dto);
	/*사원관리(임직원) - 수정*/
	public int updateEmployee(EmployeeDto dto);

	/*권한관리*/
	/*권한관리 리스트*/
	public List<EmployeeDto> getAuthList();
	/*권한관리 상세페이지*/
	public EmployeeDto getAuthDetail(String empl_id);
	/*권한 등록처리 및 해당 페이지 권한적용*/
	public int updateAuthEmployee(Map<String, Object> map);

	/*간편로그인 관리*/
	/*간편로그인 조회*/
	public SnsInfoDto getSnsInfo(String empl_id);
	/*간편로그인 삭제*/
	public int delSnsInfo(String empl_id);
	/*등록처리-OAuth2*/
	public int registSnsLoginInfo(Map<String, Object> map);
	/*비밀번호 변경*/
	public int updatePwd(Map<String, Object> map);
	/*권한정보 조회*/
	public List<AuthDto> selectAuthAllList();
	
	
	
//	========================= 작업구분선 ==================================
	
	public List<EmployeeDto> selectAllEmployee();
	
	public List<EmployeeDto> searchAllEmployee(Map<String, Object> map);
	
	public EmployeeDto selectOneEmployee(Map<String, Object> map);
	
	public int correctionEmployee(Map<String, Object> map);
	
	public boolean chkEmpPhoneNum(String empl_phone);

	public boolean chkEmpTelNum(String empl_tel);
	
	public boolean chkEmpFaxNum(String empl_fax);
	
	
}
