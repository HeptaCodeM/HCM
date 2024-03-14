package com.hcm.grw.ctrl.hr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcm.grw.dto.hr.CommonCodeDto;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.model.service.hr.CommonCodeService;
import com.hcm.grw.model.service.hr.EmployeeListService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class EmployeeListController {

	
	@Autowired
	private EmployeeListService employeeListService;
	
	@Autowired
	private CommonCodeService codeService;
	
	
	@GetMapping("/hr/employee/list.do")
	public String employeeAllList(Model model) {
		log.info("EmployeeController employeeAllList 진입");
		
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

	@GetMapping("/hr/employee/modify.do")
	public String employeeModify(@RequestParam String empl_id, Model model) {
		log.info("EmployeeController employeeModify 수정페이지 진입");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empl_id", empl_id);
		EmployeeDto empInfo = employeeListService.selectOneEmployee(map);
		
		model.addAttribute("empInfo", empInfo);
		
		return "/hr/employee/modify";
	}	
	
	
	@PostMapping(value = "/hr/employee/empSearching.do")
	@ResponseBody
	public void empSearching(@RequestParam("empShCtgVal") String empShCtgVal,
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
		System.err.println("=============================================");
		StringUtils.defaultIfEmpty(empShCtgVal, null);
		StringUtils.defaultIfEmpty(empStaCtg, null);
		StringUtils.defaultIfEmpty(startDate, null);
		StringUtils.defaultIfEmpty(endDate, null);
		StringUtils.defaultIfEmpty(empSearchValue, null);
		if(dtArr.length == 0) {
			dtArr = null;
		}
		
		if(rkArr.length == 0) {
			rkArr = null;
		}
		
		if(pnArr.length == 0) {
			pnArr = null;
		}
		String searchCtg = StringUtils.defaultIfEmpty(empShCtgVal, null);
		String delCtg = StringUtils.defaultIfEmpty(empStaCtg, null);
		String start = StringUtils.defaultIfEmpty(startDate, null);
		String end = StringUtils.defaultIfEmpty(endDate, null);
		String searchVal = StringUtils.defaultIfEmpty(empSearchValue, null);
		
		System.err.println(searchCtg);
		System.err.println(delCtg);
		System.err.println(start);
		System.err.println(end);
		System.err.println(searchVal);
		System.err.println(Arrays.toString(dtArr));
		System.err.println(Arrays.toString(rkArr));
		System.err.println(Arrays.toString(pnArr));
		
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchCtg", searchCtg);
		searchMap.put("empl_name", searchVal);
		searchMap.put("empl_id", searchVal);
		searchMap.put("empl_phone", searchVal);
		searchMap.put("empl_dept_cd", dtArr.toString());
		searchMap.put("empl_rank_cd", rkArr.toString());
		searchMap.put("empl_position_cd", pnArr.toString());
		searchMap.put("empl_delflag", delCtg);
		searchMap.put("startdate", start);
		searchMap.put("enddate", end);
		
		List<EmployeeDto> lists = employeeListService.searchAllEmployee(searchMap);
		System.out.println(lists);
	}
	
	
}
