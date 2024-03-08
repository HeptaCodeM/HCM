package com.hcm.grw.ctrl.hr;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hcm.grw.dto.hr.CompanyDto;
import com.hcm.grw.model.service.hr.CompanyService;

@Controller
public class CompanyController {

	@Autowired
	private CompanyService companyService; 
	
	
	@GetMapping(value = "/companyInfo.do")
	public String companyInfo(Model model) {
		Map<String, Object> companyMap = new HashMap<String, Object>();
		companyMap.put("comp_id", "ITCOM0A1");
		CompanyDto companyDto = companyService.showCompanyInfo(companyMap);
		model.addAttribute("companyDto", companyDto);
		return "hr/company/companyInfo";
	}
	
	
}
