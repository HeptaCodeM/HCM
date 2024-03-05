package com.hcm.grw.ctrl.hr;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcm.grw.dto.hr.CommonCodeDto;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.model.mapper.hr.EmployeeListDao;
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
	
	@Autowired
	private EmployeeListDao employeeListDao;
	
	
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
	public @ResponseBody void registEmployee(EmployeeDto emp, HttpServletResponse resp) throws IOException {
		log.info("EmployeeController registEmployee 등록처리");
		
		resp.setContentType("text/html;charset=utf-8;");
		
        // Random 객체 생성
        Random random = new Random();

        // 8자리 숫자 생성
        int randomNumber = random.nextInt(90000000) + 10000000;
        emp.setEmpl_pwd(String.valueOf(randomNumber));
        String birth = emp.getEmpl_birth();
        emp.setEmpl_birth(birth.replace("-", ""));
        emp.setEmpl_auth("ROLE_USER");
        emp.setEmpl_create_id("20220101");
        
		log.info("등록값 : {}", emp);
		
		int n = employeeService.registEmployee(emp);
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		if(n < 1) {
			sb.append("alert('등록 시 오류가 발생하였습니다.'); history.back();");
		}else {
			sb.append("alert('정상적으로 등록 되었습니다.');");
			sb.append("location.href='/hr/employee/list.do';");
		}
		sb.append("</script>");
		
		resp.getWriter().print(sb);
	}

	
	@GetMapping("/hr/employee/list.do")
	public String employeeAllList(Model model) {
		log.info("EmployeeController employeeAllList 진입");
		
		List<EmployeeDto> lists = employeeListDao.selectAllEmployee();
		
		model.addAttribute("lists", lists);
		
		return "/hr/employee/list";
	}	

	@GetMapping("/hr/employee/modify.do")
	public String employeeModify(@RequestParam String empl_id, Model model) {
		log.info("EmployeeController employeeModify 수정페이지 진입");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empl_id", empl_id);
		EmployeeDto empInfo = employeeListDao.selectOneEmployee(map);
		
		model.addAttribute("empInfo", empInfo);
		
		return "/hr/employee/modify";
	}	
	
}
