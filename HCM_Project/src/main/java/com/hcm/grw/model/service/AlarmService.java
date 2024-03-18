package com.hcm.grw.model.service;

import java.util.List;
import java.util.Map;

import com.hcm.grw.dto.AlarmDto;

public interface AlarmService {

	public List<AlarmDto> selectNotice();
	
	public List<AlarmDto> selectApproval(String al_target);
	
	public List<AlarmDto> selectReject(String al_target);
	
	public List<AlarmDto> selectRequest(String al_target);
	
	public Map<String, List<AlarmDto>> alarmTransaction(String al_target);
	
	public List<AlarmDto> selectAllAlarm(String al_target);
	
	public int alarmOff(List<String> al_no);
	
}
