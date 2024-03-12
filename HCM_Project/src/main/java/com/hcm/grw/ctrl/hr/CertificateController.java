package com.hcm.grw.ctrl.hr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hcm.grw.dto.hr.SignDocBoxDto;
import com.hcm.grw.model.service.hr.SignDocBoxService;

@Controller
public class CertificateController {

	@Autowired
	private SignDocBoxService boxService;
	
	
	@GetMapping(value = "/certificate.do")
	public String certificate(Model model) {
		Map<String, Object> docMap = new HashMap<String, Object>();
		// TODO login세션 생기면 수정하고 테스트 [재원]
		docMap.put("empl_id", "20220101");
		List<SignDocBoxDto> docList = boxService.selectAllDocList(docMap);
		System.out.println(docList);
		model.addAttribute("docList" ,docList);
		return "hr/certificate/certificateMain";
	}
	
}
