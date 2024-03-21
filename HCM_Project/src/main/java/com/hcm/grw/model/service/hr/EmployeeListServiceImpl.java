package com.hcm.grw.model.service.hr;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.model.mapper.hr.EmployeeListDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeListServiceImpl implements EmployeeListService {

	@Autowired
	private EmployeeListDao dao;
	
	@Override
	public List<EmployeeDto> selectAllEmployee() {
		log.info("EmployeeListServiceImpl selectAllEmployee");
		return dao.selectAllEmployee();
	}

	@Override
	public List<EmployeeDto> searchAllEmployee(Map<String, Object> map) {
		log.info("EmployeeListServiceImpl searchAllEmployee // {}",map);
		return dao.searchAllEmployee(map);
	}

	@Override
	public EmployeeDto selectOneEmployee(Map<String, Object> map) {
		log.info("EmployeeListServiceImpl selectOneEmployee // {}",map);
		return dao.selectOneEmployee(map);
	}

	@Override
	public int correctionEmployee(Map<String, Object> map) {
		log.info("EmployeeListServiceImpl correctionEmployee // {}",map);
		return dao.correctionEmployee(map);
	}

	@Override
	public boolean chkEmpPhoneNum(String empl_phone) {
		log.info("EmployeeListServiceImpl chkEmpPhoneNum // {}",empl_phone);
		return dao.chkEmpPhoneNum(empl_phone);
	}

	@Override
	public boolean chkEmpTelNum(String empl_tel) {
		log.info("EmployeeListServiceImpl chkEmpTelNum // {}",empl_tel);
		return dao.chkEmpTelNum(empl_tel);
	}

	@Override
	public boolean chkEmpFaxNum(String empl_fax) {
		log.info("EmployeeListServiceImpl chkEmpFaxNum // {}",empl_fax);
		return dao.chkEmpFaxNum(empl_fax);
	}
	
	
	
}
