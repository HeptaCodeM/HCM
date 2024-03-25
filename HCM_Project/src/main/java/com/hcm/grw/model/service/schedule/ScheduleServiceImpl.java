package com.hcm.grw.model.service.schedule;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.grw.model.mapper.schedule.ScheduleDao;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleDao dao;


	@Override
	public int registEmployeeHoliday() {
		return dao.registEmployeeHoliday();
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
	public int updateOrderSchedule() {
		return dao.updateOrderSchedule();
	}
	

}
