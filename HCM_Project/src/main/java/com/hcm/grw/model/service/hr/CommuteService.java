package com.hcm.grw.model.service.hr;

import java.util.List;
import java.util.Map;

import com.hcm.grw.dto.hr.CommuteDto;

public interface CommuteService {

	/*출근 등록*/
	public int registCommute(String empl_id);
	/*퇴근 등록*/
	public int updateCommute(String emco_seq);
	/*출퇴근 현황*/
	public List<CommuteDto> commuteList(Map<String, String> map);
	
}
