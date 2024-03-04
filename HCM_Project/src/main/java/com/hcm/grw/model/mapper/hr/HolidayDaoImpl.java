package com.hcm.grw.model.mapper.hr;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.hr.HolidayDto;

@Repository
public class HolidayDaoImpl implements HolidayDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private String NS = "com.hcm.grw.model.mapper.hr.HolidayDaoImpl.";

	@Override
	public int registEmployeeHoliday() {
		return sqlSessionTemplate.insert(NS+"registEmployeeHoliday");
	}

	@Override
	public List<HolidayDto> holidayList(String empl_id) {
		return sqlSessionTemplate.selectList(NS+"holidayList", empl_id);
	}

}
