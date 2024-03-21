package com.hcm.grw.ctrl.hr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		List<SignDocBoxDto> docDownloadList = boxService.selectAllDownloadDocList(docMap);
		System.out.println(docList);
		System.out.println(docDownloadList);
		model.addAttribute("docList" ,docList);
		model.addAttribute("docDownloadList" ,docDownloadList);
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
	
	
	@GetMapping(value = "/hr/certificate/downloadDoc.do")
	@ResponseBody
	public boolean downloadDoc(@RequestParam("emdh_type") String emdh_type ,
							@RequestParam("emdn_id") String emdn_id ,
							Authentication authentication) {
		Map<String, Object> docMap = new HashMap<String, Object>();
		String emdh_empl_id = authentication.getName();
		System.out.println(emdh_empl_id);
		System.out.println(emdh_type);
		System.out.println(emdn_id);
		
		docMap.put("emdh_empl_id", emdh_empl_id); 
		docMap.put("emdh_type", emdh_type); 
		docMap.put("emdn_id", emdn_id); 
		
		
		int cnt = boxService.downloadOneDoc(docMap);
		
		return (cnt > 0)?true:false;
		
	}
	
}
