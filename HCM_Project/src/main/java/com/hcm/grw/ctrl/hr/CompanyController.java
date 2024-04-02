package com.hcm.grw.ctrl.hr;

import java.io.IOException;
import java.sql.Blob;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hcm.grw.comm.Function;
import com.hcm.grw.dto.hr.CompanyDto;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.model.mapper.hr.EmployeeDao;
import com.hcm.grw.model.service.hr.CompanyService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CompanyController {

	@Autowired
	private CompanyService companyService; 
	
	@Autowired
	private EmployeeDao employeeDao; 
	
	
	@GetMapping(value = "/hr/company/companyInfoAdmin.do")
	public String companyInfo(Model model, Authentication authentication) {
		log.info("{} 회사정보 화면 진입", Function.getMethodName());
		String userId = authentication.getName();
		System.out.println(userId);
		EmployeeDto fnEmployeeDto = employeeDao.getUserInfo(userId);
		String empl_auth = fnEmployeeDto.getEmpl_auth();
		System.out.println(empl_auth);
		if(empl_auth.equals("ROLE_HR_ADMIN")) {
			model.addAttribute("empl_auth",empl_auth);
		}
		Map<String, Object> companyMap = new HashMap<String, Object>();
		companyMap.put("comp_id", "ITCOM0A1");
		CompanyDto companyDto = companyService.showCompanyInfo(companyMap);
		Map<String, Object> sealMap = new HashMap<String, Object>();
		sealMap.put("comp_id", "ITCOM0A1");
		CompanyDto sealDto = companyService.showCompanySeal(sealMap);
		byte[] sealImg = sealDto.getComp_seal();
		
		
		model.addAttribute("sealImg",Base64Utils.encodeToString(sealImg));
		model.addAttribute("companyDto", companyDto);
		return "hr/company/companyInfoAdmin";
	}
	
	@GetMapping(value = "/hr/company/companyInfoCorrectionAdmin.do")
	public String companyInfoCorrection(Model model) {
		log.info("{} 회사정보 수정 화면진입", Function.getMethodName());
		Map<String, Object> companyMap = new HashMap<String, Object>();
		companyMap.put("comp_id", "ITCOM0A1");
		CompanyDto companyDto = companyService.showCompanyInfo(companyMap);
		model.addAttribute("companyDto", companyDto);
		return "hr/company/companyInfoCorrectionAdmin";
	}
	
	@PostMapping(value = "/hr/company/correctionCompanyInfoAdmin.do")
	public @ResponseBody void correctionCompanyInfo(HttpServletRequest request,@RequestParam("comp_seal") List<MultipartFile> file)throws IOException {
		log.info("{} 회사정보 수정", Function.getMethodName());
		String comp_name = request.getParameter("comp_name");
		String comp_num = request.getParameter("comp_num");
		String comp_ceo_name = request.getParameter("comp_ceo_name");
		String comp_tel = request.getParameter("comp_tel");
		String comp_fax = request.getParameter("comp_fax");
		String comp_email = request.getParameter("comp_email");
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
		companyDto.setComp_email(comp_email);
		companyDto.setComp_addr1(comp_addr1);
		companyDto.setComp_addr2(comp_addr2);
		companyDto.setComp_post(comp_post);
		companyDto.setComp_id(comp_id);
		companyDto.setComp_modify_id(comp_modify_id);
		
		System.out.println(companyDto);
		
        if(!file.stream().allMatch(MultipartFile::isEmpty)) {
			for(MultipartFile f : file){
		        log.info("f.isEmpty() : {}", f.isEmpty());
		        log.info("f.getSize() : {}", f.getSize());
		        log.info("f.getContentType() : {}", f.getContentType());
		        companyDto.setComp_seal(f.getBytes());
			}
		}
        else {
//			Map<String, Object> sealChkMap = new HashMap<String, Object>();
//			sealChkMap.put("comp_id", "ITCOM0A1");
//			CompanyDto sealDto = companyService.showCompanySeal(sealChkMap);
//			byte[] sealImg = sealDto.getComp_seal();
//			companyDto.setComp_seal(sealImg);
			companyDto.setComp_seal(null);
		}
		
		int cnt = companyService.correctionCompanyInfo(companyDto);
		
		if(cnt == 1) {
			Function.alertLocation("정상적으로 수정 되었습니다.", "/hr/company/companyInfoAdmin.do", "","","");
			return;
//			return "redirect:/hr/company/companyInfo.do";
		}else {
			Function.alertHistoryBack("수정 시 오류가 발생하였습니다.", "", "");
			return;
//			return "redirect:/hr/company/companyInfo.do";
		}
		
	}
	
	@GetMapping(value = "/hr/company/showCompanySealAdmin.do")
	public String showCompanySeal(Model model) {
		log.info("{} 회사정보 직인 확인", Function.getMethodName());
		Map<String, Object> sealMap = new HashMap<String, Object>();
		sealMap.put("comp_id", "ITCOM0A1");
		CompanyDto sealDto = companyService.showCompanySeal(sealMap);
		byte[] sealImg = sealDto.getComp_seal();
		
		System.out.println(Base64Utils.encodeToString(sealImg));
		model.addAttribute("sealImg",Base64Utils.encodeToString(sealImg));
		return "hr/company/showCompanySealAdmin";
	}
	
}
