package com.hcm.grw.ctrl.hr;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hcm.grw.comm.Function;
import com.hcm.grw.dto.hr.CommonCodeDto;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.model.mapper.hr.EmployeeDao;
import com.hcm.grw.model.service.hr.CommonCodeService;
import com.hcm.grw.model.service.hr.EmployeeListService;
import com.hcm.grw.model.service.hr.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class EmployeeListController {

	
	@Autowired
	private EmployeeListService employeeListService;
	
	@Autowired
	private CommonCodeService codeService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeDao employeeDao; 
	
	
	@GetMapping("/hr/employee/list.do")
	public String employeeAllList(Model model) {
		log.info("EmployeeListController employeeAllList 진입");
		
		List<EmployeeDto> lists = employeeListService.selectAllEmployee();
		
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
		model.addAttribute("lists", lists);
		
		return "/hr/employee/list";
	}	

	@GetMapping("/hr/employee/modifyAdmin.do")
	public String employeeModify(Model model , Authentication authentication) {
		log.info("EmployeeListController modifyAdmin 수정페이지 진입");
		
		Map<String, Object> map = new HashMap<String, Object>();
		String empl_modify_id = authentication.getName();
		EmployeeDto fnEmployeeDto = employeeDao.getUserInfo(empl_modify_id);
		String empl_auth = fnEmployeeDto.getEmpl_auth();
		System.out.println(empl_auth);
		System.out.println(empl_modify_id);
		map.put("empl_id", empl_modify_id);
		if(empl_auth.equals("ROLE_HR_ADMIN")) {
			EmployeeDto empInfo = employeeListService.selectOneEmployee(map);
			byte[] empPic = empInfo.getEmpl_picture();
			empInfo.setEmpl_picture_str(Function.blobImageToString(empPic));
			model.addAttribute("empInfo", empInfo);
			return "/hr/employee/modifyAdmin";
		}else {
			System.out.println("에러처리");
			return "/hr/employee/modifyAdmin";
		}
		
	}	
	
	
	@PostMapping("/hr/employee/modifyAdmin.do")
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
	
	
	
	@PostMapping(value = "/hr/employee/empSearching.do")
	@ResponseBody
	public List<EmployeeDto> empSearching(@RequestParam("empShCtgVal") String empShCtgVal,
								@RequestParam("empStaCtg") String empStaCtg,
								@RequestParam("startDate") String startDate,
								@RequestParam("endDate") String endDate,
								@RequestParam("empSearchValue") String empSearchValue,
								@RequestParam("dtArr") String[] dtArr,
								@RequestParam("rkArr") String[] rkArr,
								@RequestParam("pnArr") String[] pnArr
							) {
		System.out.println(empShCtgVal);
		System.out.println(empStaCtg);
		System.out.println(startDate);
		System.out.println(endDate);
		System.out.println(empSearchValue);
		System.out.println(Arrays.toString(dtArr));
		System.out.println(Arrays.toString(rkArr));
		System.out.println(Arrays.toString(pnArr));
		
		
		
		
		String searchCtg = StringUtils.defaultIfEmpty(empShCtgVal, null);
		String delCtg = StringUtils.defaultIfEmpty(empStaCtg, null);
		String start = StringUtils.defaultIfEmpty(startDate, null);
		String end = StringUtils.defaultIfEmpty(endDate, null);
		String searchVal = StringUtils.defaultIfEmpty(empSearchValue, null);
		
//		System.err.println(searchCtg);
//		System.err.println(delCtg);
//		System.err.println(start);
//		System.err.println(end);
//		System.err.println(searchVal);
//		System.err.println(Arrays.toString(dtArr));
//		System.err.println(Arrays.toString(rkArr));
//		System.err.println(Arrays.toString(pnArr));
		
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchCtg", searchCtg);
		searchMap.put("empl_name", searchVal);
		searchMap.put("empl_id", searchVal);
		searchMap.put("empl_phone", searchVal);
		searchMap.put("empl_delflag", delCtg);
		searchMap.put("startdate", start);
		searchMap.put("enddate", end);
		
		if(dtArr.length == 0) {
			dtArr = null;
		}else {
			searchMap.put("empl_dept_cd", "work");
		}
		
		if(rkArr.length == 0) {
			rkArr = null;
		}else{
			searchMap.put("empl_rank_cd", "work");
		}
		
		if(pnArr.length == 0) {
			pnArr = null;
		}else {
			searchMap.put("empl_position_cd", "work");
		}
		
		searchMap.put("dtArr", dtArr);
		searchMap.put("rkArr", rkArr);
		searchMap.put("pnArr", pnArr);
		
		
		List<EmployeeDto> searchlists = employeeListService.searchAllEmployee(searchMap);
		System.out.println(searchlists);
		Gson returnList = new GsonBuilder().create();
		return searchlists;
	}
	
	
}
