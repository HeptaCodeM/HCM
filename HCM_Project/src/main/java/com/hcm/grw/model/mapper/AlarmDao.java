package com.hcm.grw.model.mapper;

import java.util.List;

import com.hcm.grw.dto.AlarmDto;

public interface AlarmDao {
	
	public List<AlarmDto> selectNotice();
	
	public List<AlarmDto> selectApproval(String al_target);
	
	public List<AlarmDto> selectReject(String al_target);
	
	public List<AlarmDto> selectRequest(String al_target);
	
	public List<AlarmDto> selectAllAlarm(String al_target);
	
	public int alarmOff(List<String> al_no);

}
