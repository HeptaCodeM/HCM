package com.hcm.grw.model.service.hr;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hcm.grw.comm.EmailService;
import com.hcm.grw.dto.hr.AuthDto;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.dto.hr.SnsInfoDto;
import com.hcm.grw.model.mapper.hr.EmployeeDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao dao;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public EmployeeDto getLogin(String empl_id) {
		return dao.getLogin(empl_id);
	}

	@Override
	public int updaetLoginDate(String empl_id) {	
		return dao.updaetLoginDate(empl_id);
	}
	
	@Override
	public int insertLoginHistory(Map<String, String> map) {
		return dao.insertLoginHistory(map);
	}
	
	@Override
	public EmployeeDto getUserInfo(String empl_id) {
		return dao.getUserInfo(empl_id);
	}

	@Override
	public String getSnsLoginInfo(Map<String, String> map) {
		return dao.getSnsLoginInfo(map);
	}

	@Override
	public int getInitPwdcheck(Map<String, Object> map) {
		
		
		
		return dao.getInitPwdcheck(map);
	}

	@Override
	public int setInitPwd(Map<String, Object> map) {
		String empl_pwd = map.get("empl_pwd").toString();
		String enc_empl_pwd = passwordEncoder.encode(empl_pwd);
		map.put("empl_pwd", enc_empl_pwd);

		return dao.setInitPwd(map);
	}

	@Override
	public int registEmployee(EmployeeDto dto) {
		String empl_pwd = dto.getEmpl_pwd();
		String enc_empl_pwd = passwordEncoder.encode(empl_pwd);
		dto.setEmpl_pwd(enc_empl_pwd);

		String subject = "[#{company_name}]그룹웨어 사원 등록이 완료되었습니다.";
		String content = "임시비밀번호를 안내드립니다.<br />임시 비밀번호 : "+empl_pwd;
		String toEmail = dto.getEmpl_email();
		String fromEmail = "";
		
		boolean sendFlag = emailService.sendMail(subject, content, toEmail, fromEmail, true);
		log.info("메일발송 여부 : {}", sendFlag);
		
		return dao.registEmployee(dto);
	}

	@Override
	public int updateEmployee(EmployeeDto dto) {
		//log.info("EmployeeServiceImpl updateEmployee dto : {}", dto);
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
		String enc_empl_new_pwd = passwordEncoder.encode(map.get("empl_new_pwd").toString());

		map.put("empl_new_pwd", enc_empl_new_pwd);
		
		return dao.updatePwd(map);
	}

	@Override
	public List<AuthDto> selectAuthAllList() {
		return dao.selectAuthAllList();
	}

	@Override
	public List<EmployeeDto> getUserInfoSearch(Map<String, String> map) {
		return dao.getUserInfoSearch(map);
	}

	@Override
	public int duplicationEmpEmail(String empl_email) {
		return dao.duplicationEmpEmail(empl_email);
	}
	
	
//	======================= 구분선 ===================================		
	
	
	@Override
	public List<EmployeeDto> selectAllEmployee() {
		return dao.selectAllEmployee();
	}

	@Override
	public List<EmployeeDto> searchAllEmployee(Map<String, Object> map) {
		return dao.searchAllEmployee(map);
	}

	@Override
	public EmployeeDto selectOneEmployee(Map<String, Object> map) {
		return dao.selectOneEmployee(map);
	}

	@Override
	public int correctionEmployee(Map<String, Object> map) {
		return dao.correctionEmployee(map);
	}

	@Override
	public boolean chkEmpPhoneNum(String empl_phone) {
		return dao.chkEmpPhoneNum(empl_phone);
	}

	@Override
	public boolean chkEmpTelNum(String empl_tel) {
		return dao.chkEmpTelNum(empl_tel);
	}

	@Override
	public boolean chkEmpFaxNum(String empl_fax) {
		return dao.chkEmpFaxNum(empl_fax);
	}
	
	
}
