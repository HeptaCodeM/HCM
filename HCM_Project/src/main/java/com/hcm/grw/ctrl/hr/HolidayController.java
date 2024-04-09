package com.hcm.grw.ctrl.hr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcm.grw.comm.Function;
import com.hcm.grw.dto.hr.CommonCodeDto;
import com.hcm.grw.dto.hr.HolidayAdminDto;
import com.hcm.grw.dto.hr.HolidayDto;
import com.hcm.grw.model.service.hr.CommonCodeService;
import com.hcm.grw.model.service.hr.HolidayService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/hr/holiday/**")
public class HolidayController {
	
	@Autowired
	private HolidayService holidayService;

	@Autowired
	CommonCodeService codeService;
	
	/* 휴가현황 (임직원) */
	@GetMapping("holidayList.do")
	public String holidayList(Authentication authentication) {
		log.info("{} 휴가현황 조회", Function.getMethodName());
		
		if(authentication == null) {
			Function.alertHistoryBack("로그인 정보가 없습니다.("+Function.getMethodName()+")", "/login/login.do", "");
			return null;
		}
		
		return "hr/holiday/holidayList";
	}
	
	@PostMapping(value="holidaySearchList.do", produces = "application/json;")
	public @ResponseBody String holidaySearchList(@RequestParam(required = false) Map<String, Object> dateMap, 
							  Authentication authentication, 
							  Model model) {
		log.info("{} 휴가현황 조회", Function.getMethodName());
		
		String empl_id = "";
		if(authentication == null) {
			return "{\"error\":\"로그인 정보가 없습니다\"}";
		}else {
			empl_id = authentication.getName();
		}

		/*
		휴가 집계정보
		TOTAL_HOLIDAY
		USE_HOLIDAY
		REST_HOLIDAY
		*/
		Map<String, Object> holidayTotalMap = holidayService.selectEmpTotalHoliDayInfo(empl_id);
		log.info("holidayTotalMap : {}", holidayTotalMap);
		// 휴가정보 없을때 초기값 처리
		if(holidayTotalMap == null) {
			holidayTotalMap = new HashMap<String, Object>();
			holidayTotalMap.put("TOTAL_HOLIDAY", "0");
			holidayTotalMap.put("USE_HOLIDAY", "0");
			holidayTotalMap.put("REST_HOLIDAY", "0");
			holidayTotalMap.put("STANDARD_HOLIDAY", "-");
		}
		/*
		휴가 사용현황 리스트
		*/
		Map<String, String> holidaySearchMap = new HashMap<String, String>(){{
			put("empl_id", authentication.getName());
			put("sdate", String.valueOf(dateMap.get("sdate")));
			put("edate", String.valueOf(dateMap.get("edate")));
		}};
		List<HolidayDto> holidayLists = holidayService.holidayList(holidaySearchMap);
		
	    try {
	        // ObjectMapper를 사용하여 객체를 JSON 문자열로 변환
	        ObjectMapper objectMapper = new ObjectMapper();
	        String holidayTotalMapJson = objectMapper.writeValueAsString(holidayTotalMap);
	        String holidayListsJson = objectMapper.writeValueAsString(holidayLists);

	        // JSON 문자열 반환
	        return "{\"holidayTotalMap\":" + holidayTotalMapJson + ",\"holidayLists\":" + holidayListsJson + "}";
	    } catch (JsonProcessingException e) {
	        e.printStackTrace();
	        return "{\"error\":\"JSON 변환 오류\"}";
	    }	
	}


	/* 휴가현황 (관리자) */
	@GetMapping("holidayAdminList.do")
	public String holidayAdminList(Authentication authentication, 
							  		Model model) {
		log.info("{} 휴가현황 조회(관리자)", Function.getMethodName());
		
		if(authentication == null) {
			Function.alertHistoryBack("로그인 정보가 없습니다.("+Function.getMethodName()+")", "/login/login.do", "");
			return null;
		}

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
		
		return "hr/holiday/holidayAdminList";
	}
	
	@PostMapping(value="holidaySearchAdminList.do", produces = "application/json;")
	public @ResponseBody String holidaySearchAdminList(@RequestParam(required = false) Map<String, Object> holidaySearchMap, 
														@RequestParam(name="empl_dept_cd", required = false) String[] arr_empl_dept_cd,
														@RequestParam(name="empl_rank_cd", required = false) String[] arr_empl_rank_cd,
														@RequestParam(name="empl_position_cd", required = false) String[] arr_empl_position_cd,
													  	Authentication authentication, 
													  	Model model) {
		log.info("{} 휴가현황 조회(관리자)", Function.getMethodName());

		String empl_id = "";
		if(authentication == null) {
			return "{\"error\":\"로그인 정보가 없습니다\"}";
		}
		
		if(arr_empl_dept_cd != null && arr_empl_dept_cd.toString() != "") {
			holidaySearchMap.put("empl_dept_cd", Arrays.asList(arr_empl_dept_cd));
		}
		if(arr_empl_rank_cd != null && arr_empl_rank_cd.toString() != "") {
			holidaySearchMap.put("empl_rank_cd", Arrays.asList(arr_empl_rank_cd));
		}
		if(arr_empl_position_cd != null && arr_empl_position_cd.toString() != "") {
			holidaySearchMap.put("empl_position_cd", Arrays.asList(arr_empl_position_cd));
		}
		log.info("holidaySearchMap : {}", holidaySearchMap);
		
		/*
		휴가 사용현황 리스트
		*/
		List<HolidayAdminDto> holidayLists = holidayService.holidayAdminList(holidaySearchMap);
		log.info("holidayLists : {}", holidayLists);
		
	    try {
	        // ObjectMapper를 사용하여 객체를 JSON 문자열로 변환
	        ObjectMapper objectMapper = new ObjectMapper();
	        String holidayListsJson = objectMapper.writeValueAsString(holidayLists);

	        // JSON 문자열 반환
	        return "{\"holidayLists\":" + holidayListsJson + "}";
	    } catch (JsonProcessingException e) {
	        e.printStackTrace();
	        return "{\"error\":\"JSON 변환 오류\"}";
	    }	
	}
		
}
