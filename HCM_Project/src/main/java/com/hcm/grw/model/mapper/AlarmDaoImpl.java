package com.hcm.grw.model.mapper;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.AlarmDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class AlarmDaoImpl implements AlarmDao {

	@Autowired
	private SqlSessionTemplate template;
	private final String NS = "com.hcm.grw.model.mapper.AlarmDaoImpl.";
	
	@Override
	public List<AlarmDto> selectNotice() {
		log.info("AlarmDaoImpl selectNotice DAO Access");
		return template.selectList(NS + "selectNotice");
	}
	
	@Override
	public List<AlarmDto> selectApproval(String al_target) {
		log.info("AlarmDaoImpl selectApproval DAO Access");
		return template.selectList(NS + "selectApproval", al_target);
	}
	
	@Override
	public List<AlarmDto> selectReject(String al_target) {
		log.info("AlarmDaoImpl selectReject DAO Access");
		return template.selectList(NS + "selectReject", al_target);
	}
	
	@Override
	public List<AlarmDto> selectRequest(String al_target) {
		log.info("AlarmDaoImpl selectRequest DAO Access");
		return template.selectList(NS + "selectRequest", al_target);
	}
	
	@Override
	public List<AlarmDto> selectAllAlarm(String al_target) {
		log.info("AlarmDaoImpl selectAllAlarm DAO Access");
		return template.selectList(NS + "selectAllAlarm", al_target);
	}

	@Override
	public int alarmOff(List<String> al_no) {
		log.info("AlarmDaoImpl alarmOff DAO Access");
		return template.update(NS + "alarmOff", al_no);
	}
	
}
