package com.hcm.grw.ctrl.doc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
		log.info("SignFavoController signFavoList.do GET 즐겨찾기 결재자 불러오기");
		List<SignFavoDto> apprList = service.getFavApprList(empl_id);
		return ResponseEntity.ok(apprList);
	}
	
	@GetMapping("signFavoLineList.do")
	public ResponseEntity<?> signFavoLineList(@RequestParam String empl_id) {
		log.info("SignFavoController signFavoLineList.do GET 즐겨찾기 라인 불러오기");
		List<SignFavoDto> lineList = service.getFavApprLineList(empl_id);
		String appr_id = "";
		for(int i=0; i<lineList.size(); i++) {
			appr_id = lineList.get(i).getSiaf_appr_line();
		}
		System.out.println(appr_id);
		List<EmployeeDto> dto = service.getFav(appr_id);
		System.out.println(dto);
		return ResponseEntity.ok(lineList);
	}
	

}
