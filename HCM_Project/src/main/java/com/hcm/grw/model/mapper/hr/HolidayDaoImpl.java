package com.hcm.grw.model.mapper.hr;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.hr.HolidayAdminDto;
import com.hcm.grw.dto.hr.HolidayDto;

@Repository
public class HolidayDaoImpl implements HolidayDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private String NS = "com.hcm.grw.model.mapper.hr.HolidayDaoImpl.";


	@Override
	public List<HolidayDto> holidayList(Map<String, String> map) {
		return sqlSessionTemplate.selectList(NS+"holidayList", map);
	}

	@Override
	public List<HolidayAdminDto> holidayAdminList(Map<String, Object> map) {
		return sqlSessionTemplate.selectList(NS+"selectEmpTotalHoliDayInfoAdmin", map);
	}

	@Override
	public int selectHoliDayInfo(Map<String, String> map) {
		return sqlSessionTemplate.selectOne(NS+"selectHoliDayInfo", map);
	}

	@Override
	public Map<String, Object> selectEmpTotalHoliDayInfo(String empl_id) {
		return sqlSessionTemplate.selectOne(NS+"selectEmpTotalHoliDayInfo", empl_id);
	}

	
}
