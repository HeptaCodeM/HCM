package com.hcm.grw.model.mapper.hr;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.hr.EmployeeDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class EmployeeListDaoImpl implements EmployeeListDao {

	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	private final String NS = "com.hcm.grw.model.mapper.hr.EmployeeListDaoImpl.";
	
	@Override
	public List<EmployeeDto> selectAllEmployee() {
		log.info("EmployeeListDaoImpl selectAllEmployee");
		return sessionTemplate.selectList(NS+"selectAllEmployee");
	}

	@Override
	public List<EmployeeDto> searchAllEmployee(Map<String, Object> map) {
		log.info("EmployeeListDaoImpl searchAllEmployee // {}",map);
		return sessionTemplate.selectList(NS+"searchAllEmployee",map);
	}

	@Override
	public EmployeeDto selectOneEmployee(Map<String, Object> map) {
		log.info("EmployeeListDaoImpl selectOneEmployee // {}",map);
		return sessionTemplate.selectOne(NS+"selectOneEmployee",map);
	}

	@Override
	public int correctionEmployee(Map<String, Object> map) {
		log.info("EmployeeListDaoImpl correctionEmployee // {}",map);
		return sessionTemplate.update(NS+"correctionEmployee",map);
	}
	
	
}
