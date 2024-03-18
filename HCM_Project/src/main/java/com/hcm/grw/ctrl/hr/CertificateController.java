package com.hcm.grw.ctrl.hr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hcm.grw.dto.hr.SignDocBoxDto;
import com.hcm.grw.model.service.hr.SignDocBoxService;

@Controller
public class CertificateController {

	@Autowired
	private SignDocBoxService boxService;
	
	
	@GetMapping(value = "/hr/certificate/certificate.do")
	public String certificate(Model model , Authentication authentication) {
		Map<String, Object> docMap = new HashMap<String, Object>();
		String empl_id = authentication.getName();
		System.out.println(empl_id);
		docMap.put("empl_id", empl_id);
		List<SignDocBoxDto> docList = boxService.selectAllDocList(docMap);
		System.out.println(docList);
		model.addAttribute("docList" ,docList);
		return "hr/certificate/certificateMain";
	}
	
	
	@GetMapping(value = "/hr/certificate/selectOneCertificate.do")
	public String selectOneCertificate(String sidb_doc_num , Model model , Authentication authentication) {
		System.out.println(sidb_doc_num);
		String empl_id = authentication.getName();
		System.out.println(empl_id);
		Map<String, Object> docMap = new HashMap<String, Object>();
		docMap.put("sidb_doc_num", sidb_doc_num);
		docMap.put("empl_id", empl_id);
		SignDocBoxDto boxDto = boxService.selectOneDocList(docMap);
		System.out.println(boxDto.getSidb_doc_json());
		model.addAttribute("boxDto",boxDto);
		return "hr/certificate/selectOneCertificate";
	}
	
}
