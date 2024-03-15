package com.hcm.grw.model.service.hr;

import java.util.List;
import java.util.Map;

import com.hcm.grw.dto.hr.HolidayDto;

public interface HolidayService {

	/*휴가일 발생 스케쥴 처리-cron*/
	public int registEmployeeHoliday();
	
	/*휴가정보 조회 - 사원검색기능*/
	public List<HolidayDto> holidayList(String empl_id);
	
	/*특일(공휴일)정보 입력*/
	public int registSpecialDay(Map<String, String> map);

	/*평/휴일(특일제외)정보 입력*/
	public int registNomalDay();

	/*결재 일자 선택 시 정상적인 휴가일 수 가져오기*/
	/**
	* @param map : <br>
	* ==== Map정보 ====<br>
	* sidb_doc_be : 휴가시작일(2024-01-01)<br>
	* sidb_doc_end : 휴가종료일(2024-01-05)
	* @author SDJ
	* @since 2024.03.15
	* @return 정상적인 휴가적용일(int)<br>
	* @deprecated 전자결재 휴가화면 휴가일자 선택 시 정상적인 휴가일자 가져오기<br>
	*/
	public int selectHoliDayInfo(Map<String, String> map);

	/*사원 총휴가정보 가져오기*/
	/**
	* @param empl_id : 사원코드(String)
	* @author SDJ
	* @since 2024.03.15
	* @deprecated 임직원의 총휴가일과 사용 및 잔여 휴가일을 조회한다.<br>
	* @return 정상적인 휴가적용일(int)<br>
	* ==== Map Key정보 ====<br>
	* TOTAL_HOLIDAY : 총 휴가부여일<br>
	* USE_HOLIDAY : 현재까지 사용 휴가일<br>
	* REST_HOLIDAY : 잔여 휴가일
	*/
	public Map<String, Object> selectEmpTotalHoliDayInfo(String empl_id);
}
