package com.hcm.grw.ctrl.doc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.GsonBuilder;
import com.hcm.grw.dto.doc.SignFavoDto;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.model.service.doc.ISignFavoService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("doc/")
@RestController
@Slf4j
public class SignFavoRestController {
	
	@Autowired
	private ISignFavoService service;
	
	@GetMapping(value = "signFavoList.do", produces = "text/html; charset=UTF-8")
	public ResponseEntity<?> signFavoList(@RequestParam String empl_id) throws IOException {
		log.info("SignFavoController signFavoList.do GET 즐겨찾기 결재자 목록 불러오기 요청 아이디 : {}", empl_id);
		List<SignFavoDto> apprList = service.getFavApprList(empl_id);
		
		return ResponseEntity.ok(new GsonBuilder().create().toJson(apprList));
	}
	
	@GetMapping(value = "signFavoLineList.do", produces = "text/html; charset=UTF-8")
	public ResponseEntity<?> signFavoLineList(@RequestParam String empl_id) throws JsonProcessingException {
		log.info("SignFavoController signFavoLineList.do GET 즐겨찾기 라인 목록 불러오기 요청 아이디 : {} ", empl_id);
		
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
		
		return ResponseEntity.ok(new GsonBuilder().create().toJson(resultMap));
	}
	
	@PostMapping(path = "insertFavo.do", produces = "text/html; charset=UTF-8")
	public ResponseEntity<?> insertFavo(@RequestBody Map<String, Object> map) {
		log.info("SignFavoController insertFavo.do GET 즐겨찾기 결재자 등록 요청");
		SignFavoDto dto = service.duplicateFav(map);
		
		if(dto != null) {
			return ResponseEntity.ok("이미 등록된 결재자 입니다");
		}
		int n = service.insertFavAppr(map);
		if(n == 1) {
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
	
	@PostMapping(value = "insertFavoList.do", produces = "text/html; charset=UTF-8")
	public ResponseEntity<?> insertFavoList(@RequestBody List<Map<String, Object>> list) {
		log.info("SignFavoController insertFavoList.do POST 즐겨찾기 결재라인 등록");
		SignFavoDto dto = new SignFavoDto();
		String siaf_appr_line = "";
		dto.setEmpl_id((String)list.get(0).get("empl_id"));
		dto.setSiaf_favo_name((String)list.get(0).get("siaf_favo_name"));
		for (Map<String, Object> map : list) {
			siaf_appr_line += map.get("firstApprover") + ", ";
			siaf_appr_line += map.get("secondApprover") + ", ";
			siaf_appr_line += map.get("thirdApprover") + ", ";
		}
		siaf_appr_line = siaf_appr_line.substring(siaf_appr_line.indexOf(",") + 1, siaf_appr_line.lastIndexOf(",")).trim();
		dto.setSiaf_appr_line(siaf_appr_line);
		int n = service.insertFavApprLine(dto);
		if(n > 0) {
			return ResponseEntity.ok("저장 성공");
		}
		return ResponseEntity.ok("저장 실패");
	}
	
	@GetMapping(value = "userInfo.do", produces = "text/html; charset=UTF-8")
	public ResponseEntity<?> userInfo(@RequestParam String empl_id) {
		log.info("SignFavoController userInfo.do GET 유저정보 조회 : {}", empl_id);
		List<String> list = new ArrayList<String>();
		list.add(empl_id);
		List<EmployeeDto> user = service.getFav(list);
		return ResponseEntity.ok(new GsonBuilder().create().toJson(user));
	}
	
	@GetMapping(value = "favoInfo.do", produces = "text/html; charset=UTF-8")
	public ResponseEntity<?> favoInfo(@RequestParam String siaf_favo_cd) {
		log.info("SignFavoController favoInfo.do GET 즐겨찾기 라인 정보 조회 : {}", siaf_favo_cd);
		SignFavoDto dto = service.getFavApprLine(siaf_favo_cd);
		String[] arr = dto.getSiaf_appr_line().split(",");
		String[] newArr = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			newArr[i] = arr[i].trim();
		}
		List<EmployeeDto> result = service.getFav(Arrays.asList(newArr));
		
		return ResponseEntity.ok(new GsonBuilder().create().toJson(result));
	}
	
	@GetMapping(value = "getFavo.do", produces = "text/html; charset=UTF-8")
	public ResponseEntity<?> getFavo(@RequestParam String siaf_favo_cd) {
		log.info("SignFavoController getFavo.do GET 즐겨찾기 정보 조회 : {}", siaf_favo_cd);
		SignFavoDto dto = service.getFavAppr(siaf_favo_cd);
		List<String> list = new ArrayList<String>();
		list.add(dto.getSiaf_appr_id());
		List<EmployeeDto> user = service.getFav(list);
		return ResponseEntity.ok(new GsonBuilder().create().toJson(user));
	}
	
	
}
