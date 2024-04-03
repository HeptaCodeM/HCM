package com.hcm.grw.ctrl.hr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcm.grw.comm.Function;
import com.hcm.grw.dto.doc.SignBoxDto;
import com.hcm.grw.dto.hr.EmpSignDto;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.dto.hr.SignDocBoxDto;
import com.hcm.grw.model.service.doc.IDocBoxService;
import com.hcm.grw.model.service.hr.EmpSignService;
import com.hcm.grw.model.service.hr.SignDocBoxService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CertificateController {

	@Autowired
	private SignDocBoxService boxService;
	
	@Autowired
	private IDocBoxService docService;
	
	@Autowired
	private EmpSignService signService;
	
	
	@GetMapping(value = "/hr/certificate/certificate.do")
	public String certificate(Model model , Authentication authentication) {
		log.info("{} 결제완료 증명서 리스트 진입", Function.getMethodName());
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
		return "hr/certificate/certificate";
	}
	
	
	@GetMapping(value = "/hr/certificate/selectOneCertificate.do")
	public String selectOneCertificate(String sidb_doc_num, Model model, Authentication authentication, SignBoxDto dto, String docNum, HttpSession session) {
		log.info("{} 결제완료 증명서 상세 진입", Function.getMethodName());
		System.out.println(docNum);
		String empl_id = authentication.getName();
		System.out.println(empl_id);
		Map<String, Object> docMap = new HashMap<String, Object>();
		docMap.put("sidb_doc_num", docNum);
		docMap.put("empl_id", empl_id);
		SignDocBoxDto boxDto = boxService.selectOneDocList(docMap);
		System.out.println(boxDto.getSidb_doc_json());
		model.addAttribute("boxDto",boxDto);
		
		
//		=====================================================		
		dto.setSidb_doc_num(docNum);
		List<SignBoxDto> docDto = docService.getDetailDocsList(dto);
		EmployeeDto sessionDto = (EmployeeDto)session.getAttribute("userInfoVo");
		
		//세션정보와 비교해서 문서조회권한 확인 
		String empl_Id = docDto.get(0).getEmpl_id();
		String empl_Ref = docDto.get(0).getEmpl_ref();
		String empl_DeptCd = docDto.get(0).getEmpl_dept_cd();
		String appr_Id1 = docDto.get(0).getAppr_id();
		String appr_Id2 = null;
		String appr_Id3 = null;
		String doc_Stat = docDto.get(0).getSidb_doc_stat();
				
		int n = 0;
		
		if (docDto.size() > 1) {
		    appr_Id2 = docDto.get(1).getAppr_id();
		}

		if (docDto.size() > 2) {
		    appr_Id3 = docDto.get(2).getAppr_id();
		}

		if ((empl_Ref != null && empl_Ref.contains(sessionDto.getEmpl_id()) && (doc_Stat.equals('3') || doc_Stat.equals('4'))) ||
		    (empl_DeptCd != null && empl_DeptCd.contains(sessionDto.getEmpl_dept_cd())&& (doc_Stat.equals('3') || doc_Stat.equals('4')))  ||
		    (appr_Id1 != null && appr_Id1.contains(sessionDto.getEmpl_id())) || 
		    (appr_Id2 != null && appr_Id2.contains(sessionDto.getEmpl_id())) ||
		    (appr_Id3 != null && appr_Id3.contains(sessionDto.getEmpl_id())) ||
		    (empl_Id != null && empl_Id.contains(sessionDto.getEmpl_id()))) {
		    
			 n+= 1;
		}

		// EMPL_REF에서 가져온 모든 사원 번호를 저장할 ArrayList를 선언
		List<String> allEmployeeIds = new ArrayList<>();

		    String emplRef = docDto.get(0).getEmpl_ref();
		    if (emplRef != null && !emplRef.isEmpty()) {
		        String[] emplIds = emplRef.split(",");
		        allEmployeeIds = Arrays.asList(emplIds);
		}
//		System.out.println(allEmployeeIds);
		for (String string : allEmployeeIds) {
			System.out.println(string);
		}
		String trimId = "";
		StringBuilder employeeNamesBuilder = new StringBuilder();
		for (String id : allEmployeeIds) {
			trimId = id.trim();
			System.out.println(trimId);
		    String employeeName = docService.findEmployeeName(trimId);
		    employeeNamesBuilder.append(employeeName).append(", ");
		}
		
		if (employeeNamesBuilder.length() > 0) {
		    employeeNamesBuilder.setLength(employeeNamesBuilder.length() - 2);
		}

		String concatenatedNames = employeeNamesBuilder.toString();
		docDto.get(0).setEmpl_ref(concatenatedNames);
		
		
		// 참조 부서명 가져오기
				List<String> allDeptIds = new ArrayList<>();

				    String deptRef = docDto.get(0).getEmpl_dept_cd();
				    if (deptRef != null && !deptRef.isEmpty()) {
				        String[] deptIds = deptRef.split(",");
				        allDeptIds = Arrays.asList(deptIds);
				}
				
				String trimDeptId = "";
				StringBuilder deptNamesBuilder = new StringBuilder();
				for (String id : allDeptIds) {
					trimDeptId = id.trim();
					System.out.println(trimDeptId);
				    String deptName = docService.findDeptName(trimDeptId);
				    deptNamesBuilder.append(deptName).append(", ");
				}
				
				if (deptNamesBuilder.length() > 0) {
					deptNamesBuilder.setLength(deptNamesBuilder.length() - 2);
				}

				String concatDeptNames = deptNamesBuilder.toString();
				docDto.get(0).setEmpl_dept_cd(concatDeptNames);
		
				
				
				
				Map<String, Object> signMap = new HashMap<String, Object>();
				signMap.put("empl_id", sessionDto.getEmpl_id());
				List<EmpSignDto> signList = signService.selectAllSign(signMap);
				model.addAttribute("signList", signList);
				
				
		 
		model.addAttribute("docDto", docDto);
//		=====================================================		
		return "hr/certificate/selectOneCertificate";
	}
	
	
	@GetMapping(value = "/hr/certificate/downloadDoc.do")
	@ResponseBody
	public boolean downloadDoc(@RequestParam("emdh_type") String emdh_type ,
							@RequestParam("emdn_id") String emdn_id ,
							Authentication authentication) {
		log.info("{} 결제완료 증명서 PDF/PRINT", Function.getMethodName());
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
