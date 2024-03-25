package com.hcm.grw.model.service.hr;

import java.util.List;
import java.util.Map;

import com.hcm.grw.dto.hr.HolidayAdminDto;
import com.hcm.grw.dto.hr.HolidayDto;

public interface HolidayService {
	
	/*휴가정보 조회 - 사원검색기능*/
	public List<HolidayDto> holidayList(Map<String, String> map);

	/*휴가정보 조회(사용자) - 정보검색*/
	public List<HolidayAdminDto> holidayAdminList(Map<String, Object> map);

	/*결재 일자 선택 시 정상적인 휴가일 수 가져오기*/
	/**
	* @param map : <br>
	* ==== Map Key정보 ====<br>
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
