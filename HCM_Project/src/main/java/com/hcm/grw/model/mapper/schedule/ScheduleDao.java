package com.hcm.grw.model.mapper.schedule;

import java.util.Map;

public interface ScheduleDao {

	/* 휴가일 발생 스케쥴 처리-cron */
	public int registEmployeeHoliday();
	
	/*특일(공휴일)정보 입력-cron */
	public int registSpecialDay(Map<String, String> map);

	/*평/휴일(특일제외)정보 입력-cron*/
	public int registNomalDay();

	/*발령적용 스케쥴*/
	public int updateOrderSchedule();
}
