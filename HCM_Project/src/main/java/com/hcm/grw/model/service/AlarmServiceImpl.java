package com.hcm.grw.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hcm.grw.dto.AlarmDto;
import com.hcm.grw.model.mapper.AlarmDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AlarmServiceImpl implements AlarmService {

	@Autowired
	private AlarmDao dao;

	@Override
	public List<AlarmDto> selectNotice() {
		log.info("AlarmServiceImpl selectNotice Service 공지사항 알림 조회");
		return dao.selectNotice();
	}

	@Override
	public List<AlarmDto> selectApproval(String al_target) {
		log.info("AlarmServiceImpl selectApproval Service 승인된 결재 알림 조회");
		return dao.selectApproval(al_target);
	}

	@Override
	public List<AlarmDto> selectReject(String al_target) {
		log.info("AlarmServiceImpl selectReject Service 반려된 결재 알림 조회");
		return dao.selectReject(al_target);
	}

	@Override
	public List<AlarmDto> selectRequest(String al_target) {
		log.info("AlarmServiceImpl selectRequest Service 결재요청 알림 조회");
		return dao.selectRequest(al_target);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Map<String, List<AlarmDto>> alarmTransaction(String al_target) {
		log.info("AlarmServiceImpl alarmTransaction Service 알람 트랜잭션");
		List<AlarmDto> noticeList = dao.selectNotice();
		List<AlarmDto> approvalList = dao.selectApproval(al_target);
		List<AlarmDto> rejectList = dao.selectReject(al_target);
		List<AlarmDto> requestList = dao.selectRequest(al_target);
		Map<String, List<AlarmDto>> map = new HashMap<String, List<AlarmDto>>();
		map.put("noticeList", noticeList);
		map.put("approvalList", approvalList);
		map.put("rejectList", rejectList);
		map.put("requestList", requestList);
		return map;
	}
	
	@Override
	public List<AlarmDto> selectAllAlarm(String al_target) {
		log.info("AlarmServiceImpl selectAllAlarm Service 알림 조회");
		return dao.selectAllAlarm(al_target);
	}
	
	@Override
	public int alarmOff(List<String> al_no) {
		log.info("AlarmServiceImpl alarmOff Service 알림 끄기");
		return dao.alarmOff(al_no);
	}

}
