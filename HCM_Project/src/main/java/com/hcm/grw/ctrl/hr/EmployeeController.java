package com.hcm.grw.ctrl.hr;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/hr/employee/**")
public class EmployeeController {

	@Autowired
	private CommonCodeService codeService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeListService employeeListService;
	
	@Autowired
	private PasswordEncoder encoder;

	@GetMapping("registAdmin.do")
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

	@PostMapping("registAdmin.do")
	public @ResponseBody void registEmployeeOk(@RequestParam("empl_picture") List<MultipartFile> file, 
												@RequestParam Map<String, String> map, 
												HttpServletResponse resp, 
												Authentication authentication) throws IOException {
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

	@GetMapping("modify.do")
	public String employeeModify(Model model, Authentication authentication, HttpServletResponse resp) throws IOException {
		log.info("EmployeeController employeeModify 수정페이지 진입");
		resp.setContentType("text/html;charset=utf-8;");
		
		String empl_id = "";
		if(authentication == null) {
			resp.getWriter().print(Function.alertHistoryBack("로그인 정보가 없습니다.", "", ""));
			return null;
		}else {
			empl_id = authentication.getName();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empl_id", empl_id);

		EmployeeDto empInfo = employeeListService.selectOneEmployee(map);
		byte[] empPic = empInfo.getEmpl_picture();
		empInfo.setEmpl_picture_str(Function.blobImageToString(empPic));
		
		model.addAttribute("empInfo", empInfo);
		
		return "/hr/employee/modify";
	}	
	
	
	@PostMapping("modifyOk.do")
	public @ResponseBody void employeeModifyOk(@RequestParam("empl_picture") List<MultipartFile> file, 
												@RequestParam Map<String, String> map, 
												HttpServletResponse resp, 
												Authentication authentication) throws IOException {
		log.info("EmployeeController employeeModifyOk 수정처리");
		resp.setContentType("text/html;charset=utf-8;");
		
        String empl_modify_id = "";
		if(authentication == null) {
			resp.getWriter().print(Function.alertHistoryBack("로그인 정보가 없습니다.", "", ""));
			return;
		}else {
			empl_modify_id = authentication.getName();
		}

        EmployeeDto emp = new EmployeeDto();

        emp.setEmpl_phone(map.get("empl_phone"));
        emp.setEmpl_tel(map.get("empl_tel"));
        emp.setEmpl_fax(map.get("empl_fax"));
		emp.setEmpl_modify_id(empl_modify_id);
        emp.setEmpl_id(map.get("empl_id"));
        
        if(!file.stream().allMatch(MultipartFile::isEmpty)) {
			for(MultipartFile f : file){
		        log.info("f.isEmpty() : {}", f.isEmpty());
		        log.info("f.getSize() : {}", f.getSize());
		        log.info("f.getContentType() : {}", f.getContentType());
				emp.setEmpl_picture(f.getBytes());
			}
		}
        else {
			emp.setEmpl_picture(null);
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


	// 차후 rest로 변경, 사원 간략정보 확인용
	@GetMapping("selectEmployeeOne.do")
	public EmployeeDto selectEmployeeOne(String empl_id, HttpServletResponse resp) throws IOException {
		log.info("EmployeeController employeeModify 수정페이지 진입");
		resp.setContentType("text/html; charset=utf-8");
		
		if(StringUtils.isEmpty(empl_id)) {
			resp.getWriter().print(Function.alertHistoryBack("요청 사번이 없습니다.", "", ""));
			return null;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empl_id", empl_id);

		EmployeeDto empInfo = employeeListService.selectOneEmployee(map);
		byte[] empPic = empInfo.getEmpl_picture();
		empInfo.setEmpl_picture_str(Function.blobImageToString(empPic));
		
		return empInfo;
	}

	@GetMapping("updatePwd.do")
	public String chgPwd(Authentication authentication, 
						 HttpServletResponse resp) throws IOException {

		log.info("EmployeeController updatePwd 변경 진입");
		resp.setContentType("text/html; charset=utf-8");
		
		if(authentication == null) {
			resp.getWriter().print(Function.alertHistoryBack("로그인 정보가 없습니다.", "", ""));
			return null;
		}
		
		return "hr/employee/updatePwd";
	}

	
	@PostMapping("updatePwdOk.do")
	public @ResponseBody void updatePwdOk(@RequestParam Map<String, Object> chgPwdMap,
							  Authentication authentication,
							  HttpServletResponse resp) throws IOException {
		
		log.info("LoginController updatePwdOk 변경 처리");
		resp.setContentType("text/html; charset=utf-8");

		if(authentication == null) {
			resp.getWriter().print(Function.alertHistoryBack("로그인 정보가 없습니다.", "", ""));
			return;
		}

		EmployeeDto empDto = employeeService.getLogin(authentication.getName());
		String originPwd = empDto.getEmpl_pwd();
		log.info("originPwd : {}", originPwd);
		
		chgPwdMap.put("empl_modify_id", authentication.getName());
		chgPwdMap.put("empl_id", authentication.getName());

		boolean isPwFlag = encoder.matches(chgPwdMap.get("empl_pwd").toString(), originPwd);
		log.info("isPwFlag : {}", isPwFlag);
		
		if(isPwFlag) {
			chgPwdMap.put("empl_pwd", originPwd);

			int cnt = employeeService.updatePwd(chgPwdMap);
	
			if(cnt == 0) {
				resp.getWriter().print(Function.alertHistoryBack("비밀번호 변경에 실패하였습니다.", "", ""));
			}else {
				resp.getWriter().print(Function.alertLocation("비밀번호 변경이 완료 되었습니다.", "/hr/employee/updatePwd.do", "", "", ""));
			}
		}else {
			resp.getWriter().print(Function.alertHistoryBack("현재 비밀번호와 일치하지 않습니다.", "", ""));
		}
	}
	
	
	@GetMapping("authManageList.do")
	public String authManageList(Model model) throws IOException {
		log.info("LoginController authManageList 권한리스트");
	
		List<EmployeeDto> lists = employeeService.getAuthList();
		
		model.addAttribute("lists", lists);
		
		return "hr/employee/authManageList";
	}
	
	
}
