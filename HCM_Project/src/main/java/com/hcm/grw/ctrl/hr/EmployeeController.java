package com.hcm.grw.ctrl.hr;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hcm.grw.comm.Function;
import com.hcm.grw.dto.hr.CommonCodeDto;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.model.service.hr.CommonCodeService;
import com.hcm.grw.model.service.hr.EmployeeListService;
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
	private EmployeeListService employeeListService;
	

	@GetMapping("/hr/employee/registAdmin.do")
	public String registEmployee(Model model) {
		log.info("EmployeeController registEmployee 진입");
		
		Map<String, Object> mapDept = new HashMap<String, Object>();
		mapDept.put("role", "DT");
		
		Map<String, Object> mapRank = new HashMap<String, Object>();
		mapRank.put("role", "RK");
		
		Map<String, Object> mapPosit = new HashMap<String, Object>();
		mapPosit.put("role", "PN");
		
		List<CommonCodeDto> deptList = codeService.selectAllRole(mapDept);
		List<CommonCodeDto> rankList = codeService.selectAllRole(mapRank);
		List<CommonCodeDto> positionList = codeService.selectAllRole(mapPosit);

		model.addAttribute("deptList", deptList);
		model.addAttribute("rankList", rankList);
		model.addAttribute("positionList", positionList);
		
		return "/hr/employee/registAdmin";
	}

	@PostMapping("/hr/employee/registAdmin.do")
	public @ResponseBody void registEmployeeOk(@RequestParam("empl_picture") List<MultipartFile> file, @RequestParam Map<String, String> map, HttpServletResponse resp, Authentication authentication) throws IOException {
		log.info("EmployeeController registEmployeeOk 등록처리");
		log.info("input map : {}", map);
		log.info("MultipartFile : {}", file);
		resp.setContentType("text/html;charset=utf-8;");

        // Random 객체 생성
        Random random = new Random();
        // 8자리 숫자 생성
        int randomNumber = random.nextInt(90000000) + 10000000;
		

        EmployeeDto emp = new EmployeeDto();

		emp.setEmpl_pwd(String.valueOf(randomNumber));
        emp.setEmpl_birth(map.get("empl_birth").replace("-",""));
        emp.setEmpl_email(map.get("empl_email"));
        emp.setEmpl_name(map.get("empl_name"));
        emp.setEmpl_gender(map.get("empl_gender"));
        emp.setEmpl_fax(map.get("empl_fax"));
        emp.setEmpl_phone(map.get("empl_phone"));
        emp.setEmpl_tel(map.get("empl_tel"));
        emp.setEmpl_dept_cd(map.get("empl_dept_cd"));
        emp.setEmpl_rank_cd(map.get("empl_rank_cd"));
        emp.setEmpl_position_cd(map.get("empl_position_cd"));
        emp.setEmpl_joindate(map.get("empl_joindate"));
        emp.setEmpl_auth("ROLE_USER");
        emp.setEmpl_create_id(authentication.getName());
		log.info("등록값1 : {}", emp);

		if(file != null) {
			for(MultipartFile f : file){
				emp.setEmpl_picture(f.getBytes());
			}
		}
        
		log.info("등록값2 : {}", emp);
		
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

	@GetMapping("/hr/employee/modify.do")
	public String employeeModify(Model model, Authentication authentication) {
		log.info("EmployeeController employeeModify 수정페이지 진입");
		
		String empl_id = authentication.getName();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empl_id", empl_id);

		EmployeeDto empInfo = employeeListService.selectOneEmployee(map);
		byte[] empPic = empInfo.getEmpl_picture();
		empInfo.setEmpl_picture_str(Function.blobImageToString(empPic));
		
		model.addAttribute("empInfo", empInfo);
		
		return "/hr/employee/modify";
	}	
	
	
	@PostMapping("/hr/employee/modify.do")
	public @ResponseBody void employeeModifyOk(@RequestParam("empl_picture") List<MultipartFile> file, @RequestParam Map<String, String> map, HttpServletResponse resp, Authentication authentication) throws IOException {
		log.info("EmployeeController employeeModifyOk 수정처리");
		
        String empl_modify_id = authentication.getName();
        EmployeeDto emp = new EmployeeDto();

        emp.setEmpl_phone(map.get("empl_phone"));
        emp.setEmpl_tel(map.get("empl_tel"));
        emp.setEmpl_fax(map.get("empl_fax"));
		emp.setEmpl_modify_id(empl_modify_id);
        emp.setEmpl_id(map.get("empl_id"));

		if(file != null) {
			for(MultipartFile f : file){
				emp.setEmpl_picture(f.getBytes());
			}
		}
        
        

		log.info("수정값 : {}", emp);
		
		int n = employeeService.updateEmployee(emp);
		String msg;
		if(n < 1) {
			msg = Function.alertHistoryBack("수정 시 오류가 발생하였습니다.", "", "");

			//sb.append("alert('수정 시 오류가 발생하였습니다.'); history.back();");
		}else {
			msg = Function.alertLocation("정상적으로 수정 되었습니다.", "/hr/employee/modify.do", "","","");
			//sb.append("alert('정상적으로 수정 되었습니다.');");
			//sb.append("location.href='/hr/employee/list.do';");
		}
		
		resp.getWriter().print(msg);
	}

	
	
}
