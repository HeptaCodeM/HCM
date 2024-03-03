package com.hcm.grw.model.service.hr;

import java.util.List;

import com.hcm.grw.dto.hr.HolidayDto;

public interface HolidayService {

	/*휴가일 발생 스케쥴 처리-cron*/
	public int registEmployeeHoliday();
	
	/*휴가정보 조회 - 사원검색기능*/
	public List<HolidayDto> holidayAdminList(String empl_id);
	
}
