package com.hcm.grw.model.mapper.hr;

import java.util.List;
import java.util.Map;

import com.hcm.grw.dto.hr.HolidayDto;

public interface HolidayDao {

	/*휴가일 발생 스케쥴 처리-cron*/
	public int registEmployeeHoliday();
	
	/*휴가정보 조회 - 사원검색기능*/
	public List<HolidayDto> holidayList(String empl_id);
	
	/*특일(공휴일)정보 입력*/
	public int registSpecialDay(Map<String, String> map);
}
