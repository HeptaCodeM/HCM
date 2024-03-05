package com.hcm.grw.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hcm.grw.dto.hr.CommonCodeDto;
import com.hcm.grw.model.service.hr.CommonCodeService;

@Controller
public class KJWHrController {

	@Autowired
	private CommonCodeService codeService;
	
	@GetMapping(value = "/hrDept.do")
	public String hrDept(Model model) {
		List<CommonCodeDto> deptList = codeService.selectAllDept();
		model.addAttribute("deptList",deptList);
		return "hr/KJW/hrDept";
	}
	
	
}
