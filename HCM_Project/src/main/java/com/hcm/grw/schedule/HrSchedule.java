package com.hcm.grw.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hcm.grw.model.service.hr.HolidayService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HrSchedule {
	
	@Autowired
	private HolidayService holidayService;
	
	@Scheduled(cron="0 0 0/1 * * *")
	public void registEmployeeHoliday() {
		log.info("HrSchedule registEmployeeHoliday 매년 휴가정보 입력처리");
		int n = holidayService.registEmployeeHoliday();
		log.info("처리 수 : {}", n);
		
		
	}
	
}
