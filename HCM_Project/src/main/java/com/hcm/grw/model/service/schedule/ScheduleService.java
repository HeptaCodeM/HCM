package com.hcm.grw.model.service.schedule;

import java.util.Map;

public interface ScheduleService {

	/*휴가일 발생 스케쥴 처리-cron*/
	public int registEmployeeHoliday();
	
	/*특일(공휴일)정보 입력*/
	public int registSpecialDay(Map<String, String> map);

	/*평/휴일(특일제외)정보 입력*/
	public int registNomalDay();
	
	/* 인사발령적용 스케쥴 */
	public int updateOrderSchedule();
}
