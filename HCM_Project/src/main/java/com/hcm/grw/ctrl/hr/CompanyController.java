package com.hcm.grw.ctrl.hr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hcm.grw.model.service.hr.CompanyService;

@Controller
public class CompanyController {

	@Autowired
	private CompanyService companyService; 
	
	
	@GetMapping(value = "/companyInfo.do")
	public String companyInfo() {
		
//		companyService.showCompanyInfo(null);
		return "hr/company/companyInfo";
	}
	
	
}
