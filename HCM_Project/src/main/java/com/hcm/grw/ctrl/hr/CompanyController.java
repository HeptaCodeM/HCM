package com.hcm.grw.ctrl.hr;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	@GetMapping(value = "/companyInfoCorrection.do")
	public String companyInfoCorrection(Model model) {
		Map<String, Object> companyMap = new HashMap<String, Object>();
		companyMap.put("comp_id", "ITCOM0A1");
		CompanyDto companyDto = companyService.showCompanyInfo(companyMap);
		model.addAttribute("companyDto", companyDto);
		return "hr/company/companyInfoCorrection";
	}
	
	@PostMapping(value = "/correctionCompanyInfo.do")
	public String correctionCompanyInfo(HttpServletRequest request) {
		String comp_name = request.getParameter("comp_name");
		String comp_num = request.getParameter("comp_num");
		String comp_ceo_name = request.getParameter("comp_ceo_name");
		String comp_tel = request.getParameter("comp_tel");
		String comp_fax = request.getParameter("comp_fax");
		String comp_addr1 = request.getParameter("comp_addr1");
		String comp_addr2 = request.getParameter("comp_addr2");
		String comp_post = request.getParameter("comp_post");
		String comp_id = request.getParameter("comp_id");
		String comp_modify_id = "SYSTEM";
		
		CompanyDto companyDto = new CompanyDto();
		companyDto.setComp_name(comp_name);
		companyDto.setComp_num(comp_num);
		companyDto.setComp_ceo_name(comp_ceo_name);
		companyDto.setComp_tel(comp_tel);
		companyDto.setComp_fax(comp_fax);
		companyDto.setComp_addr1(comp_addr1);
		companyDto.setComp_addr2(comp_addr2);
		companyDto.setComp_post(comp_post);
		companyDto.setComp_id(comp_id);
		companyDto.setComp_modify_id(comp_modify_id);
		
		System.out.println(companyDto);
		
		int cnt = companyService.correctionCompanyInfo(companyDto);
		if(cnt == 1) {
			return "redirect:./companyInfo.do";
		}else {
			return "redirect:./companyInfo.do";
		}
		
	}
	
}
