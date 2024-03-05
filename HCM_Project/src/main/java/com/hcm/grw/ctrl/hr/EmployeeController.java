package com.hcm.grw.ctrl.hr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcm.grw.dto.hr.CommonCodeDto;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.model.service.hr.CommonCodeService;
import com.hcm.grw.model.service.hr.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class EmployeeController {

	@Autowired
	private CommonCodeService codeService;
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping("/hr/employee/regist.do")
	public String registEmployee(Model model) {
		log.info("EmployeeController registEmployee 진입");
		
		List<CommonCodeDto> deptList = codeService.selectAllDept();
		List<CommonCodeDto> rankList = codeService.selectAllRank();
		List<CommonCodeDto> positionList = codeService.selectAllPosition();

		model.addAttribute("deptList", deptList);
		model.addAttribute("rankList", rankList);
		model.addAttribute("positionList", positionList);
		
		return "/hr/employee/regist";
	}

	@PostMapping("/hr/employee/regist.do")
	public String registEmployee(EmployeeDto emp) {
		log.info("EmployeeController registEmployee 등록처리");
		
		
		return null;
	}


}
