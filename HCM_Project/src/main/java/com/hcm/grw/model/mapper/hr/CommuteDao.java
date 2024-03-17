package com.hcm.grw.model.mapper.hr;

import java.util.List;
import java.util.Map;

import com.hcm.grw.dto.hr.CommuteDto;

public interface CommuteDao {

	/*출근 등록*/
	public int registCommute(CommuteDto dto);
	/*퇴근 등록*/
	public int updateCommute(String empl_id);
	/*출퇴근 현황*/
	public List<CommuteDto> commuteList(Map<String, String> map);
	/*출근상태 확인*/
	public CommuteDto selectCommuteDayInfo(String empl_id);
}
