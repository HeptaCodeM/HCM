package com.hcm.grw.ctrl.hr;

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
import com.hcm.grw.dto.hr.HolidayDto;
import com.hcm.grw.model.service.hr.HolidayService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/hr/holiday/**")
public class HolidayController {
	
	@Autowired
	private HolidayService holidayService;

	@GetMapping("holidayList.do")
	public String holidayList(Authentication authentication, 
							  HttpServletResponse resp) {
		log.info("HolidayController holidayList 휴가현황 조회");
		resp.setContentType("text/html; charset=UTF-8;");
		
		if(authentication == null) {
			Function.alertHistoryBack(resp, "로그인 정보가 없습니다.", "/login/login.do", "");
			return null;
		}
		
		return "hr/holiday/holidayList";
	}
	
	@PostMapping(value="holidaySearchList.do", produces = "application/json;")
	public @ResponseBody String holidaySearchList(@RequestParam(required = false) Map<String, Object> dateMap, 
							  Authentication authentication, 
							  Model model,
							  HttpServletResponse resp) {
		log.info("HolidayController holidayList 휴가현황 조회");
		resp.setContentType("text/html; charset=UTF-8;");
		
		String empl_id = "";
		if(authentication == null) {
			return "로그인 정보가 없습니다";
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
	
}
