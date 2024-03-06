package com.hcm.grw.ctrl.doc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hcm.grw.dto.doc.SignFavoDto;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.model.service.doc.ISignFavoService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("doc/")
@RestController
@Slf4j
public class SignFavoController {
	
	@Autowired
	private ISignFavoService service;
	
	@GetMapping("signFavoList.do")
	public ResponseEntity<?> signFavoList(@RequestParam String empl_id) {
		log.info("SignFavoController signFavoList.do GET 즐겨찾기 결재자 불러오기 요청 아이디 : {}", empl_id);
		List<SignFavoDto> apprList = service.getFavApprList(empl_id);
		return ResponseEntity.ok(apprList);
	}
	
	@GetMapping("signFavoLineList.do")
	public ResponseEntity<?> signFavoLineList(@RequestParam String empl_id) throws JsonProcessingException {
		log.info("SignFavoController signFavoLineList.do GET 즐겨찾기 라인 불러오기 요청 아이디 : {} ", empl_id);
		
		List<SignFavoDto> lineList = service.getFavApprLineList(empl_id);
		
		List<List<EmployeeDto>> empList = new ArrayList<List<EmployeeDto>>();
		List<List<List<EmployeeDto>>> resultList = new ArrayList<List<List<EmployeeDto>>>();
		for(int i=0; i<lineList.size(); i++) {
			
			String[] arr = lineList.get(i).getSiaf_appr_line().split(",");
			String[] trimArr = new String[arr.length];
			
			for(int j=0; j<trimArr.length; j++) {
				trimArr[j] = arr[j].trim();
			}
			
			List<EmployeeDto> list = service.getFav(Arrays.asList(trimArr));
			empList.add(list);
			
		}
		resultList.add(empList);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("lineList", lineList);
		resultMap.put("resultList", resultList);
		
		return ResponseEntity.ok(resultMap);
	}
	
	@GetMapping(value = "insertFavo.do", produces = "text/html; charset=UTF-8")
	public ResponseEntity<?> insertFavo(@RequestParam String siaf_appr_id, @RequestParam String empl_id) {
		log.info("SignFavoController insertFavo.do GET 즐겨찾기 결재자 등록 요청 값 : {} {}", siaf_appr_id, empl_id);
		SignFavoDto dto = new SignFavoDto();
		dto.setEmpl_id(empl_id);
		dto.setSiaf_appr_id(siaf_appr_id);
		int n = service.insertFavAppr(dto);
		if(n != 0) {
			return ResponseEntity.ok("저장 성공");
		}
		return ResponseEntity.ok("저장 실패");
	}
	
	@GetMapping(value = "deleteFav.do", produces = "text/html; charset=UTF-8")
	public ResponseEntity<?> deleteFav(@RequestParam String siaf_favo_cd) {
		log.info("SignFavoController deleteFav.do GET 즐겨찾기 결재자 삭제 : {}", siaf_favo_cd);
		int n = service.deleteFav(siaf_favo_cd);
		if(n != 0) {
			return ResponseEntity.ok("삭제 성공");
		}
		return ResponseEntity.ok("삭제 실패");
	}
	
}
