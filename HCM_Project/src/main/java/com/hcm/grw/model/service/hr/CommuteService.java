package com.hcm.grw.model.service.hr;

import java.util.List;
import java.util.Map;

import com.hcm.grw.dto.hr.CommuteDto;

public interface CommuteService {

	/*출근 등록*/
	public int registCommute(CommuteDto dto);
	/*퇴근 등록*/
	public int updateCommute(CommuteDto dto);
	/*출퇴근 현황*/
	public List<CommuteDto> commuteList(Map<String, String> map);
	
}
