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
	
	
	
}
