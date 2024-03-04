package com.hcm.grw.model.service.hr;

import java.util.List;

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

}
