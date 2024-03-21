package com.hcm.grw.model.mapper.hr;

import java.util.List;
import java.util.Map;

import com.hcm.grw.dto.hr.HolidayDto;

public interface HolidayDao {

	/*휴가일 발생 스케쥴 처리-cron*/
	public int registEmployeeHoliday();
	
	/*휴가정보 조회 - 사원검색기능*/
	public List<HolidayDto> holidayList(Map<String, String> map);
	
	/*특일(공휴일)정보 입력*/
	public int registSpecialDay(Map<String, String> map);

	/*평/휴일(특일제외)정보 입력*/
	public int registNomalDay();
	
	/*결재 일자 선택 시 정상적인 휴가일 수 가져오기*/
	public int selectHoliDayInfo(Map<String, String> map);
	
	/*사원 총휴가정보 가져오기*/
	public Map<String, Object> selectEmpTotalHoliDayInfo(String empl_id);
}
