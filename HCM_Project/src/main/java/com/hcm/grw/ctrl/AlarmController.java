package com.hcm.grw.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.GsonBuilder;
import com.hcm.grw.dto.AlarmDto;
import com.hcm.grw.model.service.AlarmService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AlarmController {
	
	@Autowired
	private AlarmService service;
	
	@GetMapping(value = "offAlarmOne.do", produces = "text/html; charset=UTF-8")
	public ResponseEntity<?> offAlarmOne(@RequestParam String al_no) {
		List<String> alarmList = List.of(al_no);
		log.info("AlarmController offAlarmOne 모든 알림 비활성 요청 : {}", al_no);
		service.alarmOff(alarmList);
		return ResponseEntity.ok("성공");
	}
	
	@PostMapping(value = "offAlarm.do", produces = "text/html; charset=UTF-8")
	public ResponseEntity<?> offAlarm(@RequestBody List<String> alarmList) {
		log.info("AlarmController offAlarm 알림 비활성 요청 수 : {}", alarmList.size());
		service.alarmOff(alarmList);
		return ResponseEntity.ok("성공");
	}
	
	@GetMapping(value = "getAlarmList.do", produces = "text/html; charset=UTF-8")
	public ResponseEntity<?> getAlarmList(@RequestParam String al_target) {
		log.info("AlarmController getAlarmList 알림전체목록 : {}", al_target);
		List<AlarmDto> alarmList = service.selectAllAlarm(al_target);
		return ResponseEntity.ok(new GsonBuilder().create().toJson(alarmList));
	}
	

}
