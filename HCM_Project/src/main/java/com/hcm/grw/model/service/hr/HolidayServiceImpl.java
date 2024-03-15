package com.hcm.grw.model.service.hr;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.grw.dto.hr.HolidayDto;
import com.hcm.grw.model.mapper.hr.HolidayDao;

@Service
public class HolidayServiceImpl implements HolidayService {

	@Autowired
	private HolidayDao dao;
	
	@Override
	public int registEmployeeHoliday() {
		return dao.registEmployeeHoliday();
	}

	@Override
	public List<HolidayDto> holidayList(String empl_id) {
		return dao.holidayList(empl_id);
	}

	@Override
	public int registSpecialDay(Map<String, String> map) {
		return dao.registSpecialDay(map);
	}

	@Override
	public int registNomalDay() {
		return dao.registNomalDay();
	}

	@Override
	public int selectHoliDayInfo(Map<String, String> map) {
		return dao.selectHoliDayInfo(map);
	}

	@Override
	public Map<String, Object> selectEmpTotalHoliDayInfo(String empl_id) {
		return dao.selectEmpTotalHoliDayInfo(empl_id);
	}

}
