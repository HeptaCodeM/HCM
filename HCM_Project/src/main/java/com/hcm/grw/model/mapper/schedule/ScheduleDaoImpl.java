package com.hcm.grw.model.mapper.schedule;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NS = "com.hcm.grw.model.mapper.schedule.ScheduleDaoImpl.";
	
	
	@Override
	public int registEmployeeHoliday() {
		return sqlSessionTemplate.insert(NS+"registEmployeeHoliday");
	}

	@Override
	public int registSpecialDay(Map<String, String> map) {
		return sqlSessionTemplate.insert(NS+"registSpecialDay", map);
	}

	@Override
	public int registNomalDay() {
		return sqlSessionTemplate.insert(NS+"registNomalDay");
	}

	@Override
	public int updateOrderSchedule() {
		return sqlSessionTemplate.update(NS+"updateOrderSchedule");
	}



}
