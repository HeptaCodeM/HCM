package com.hcm.grw.ctrl.doc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcm.grw.model.service.hr.EmpSignService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("doc/signManagement/")
public class SignManagementConroller {
	
	@Autowired
	private EmpSignService signService;
	
	@PostMapping("insertSign.do")
	public boolean insertSign(@RequestBody Map<String, Object> map) {
		log.info("SignManagementConroller insertSign.do 서명 저장");
		int n = signService.insertSign(map);
		if(n == 1) {
			return true;
		}
		return false;
	}
	
	@GetMapping("deleteSign.do")
	public boolean deleteSign(@RequestParam String emsi_seq, @RequestParam String empl_id) {
		log.info("SignManagementConroller deleteSign.do 서명 삭제 요청 : {}", emsi_seq);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("emsi_seq", emsi_seq);
		map.put("empl_id", empl_id);
		int n = signService.deleteSign(map);
		if(n == 1) {
			return true;
		}
		return false;
	}
	
	@GetMapping("setDefault.do")
	public boolean setDefault(@RequestParam String emsi_seq, @RequestParam String empl_id) {
		log.info("SignManagementConroller setDefault.do 기본 서명 등록 : {}", empl_id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("emsi_seq", emsi_seq);
		map.put("empl_id", empl_id);
		int n = signService.defaultSignTransaction(map);
		if(n == 1) {
			return true;
		}
		return false;
	}

}
