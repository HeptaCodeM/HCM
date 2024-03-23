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
import com.hcm.grw.dto.hr.OrderInfoListDto;
import com.hcm.grw.model.service.hr.CommonCodeService;
import com.hcm.grw.model.service.hr.OrderService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/hr/order/**")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CommonCodeService codeService;
	
	@GetMapping("orderList.do")
	public String orderList(Model model,
							Authentication authentication,
							HttpServletResponse resp) {
		log.info("OrderController orderList 발령현황 조회");
		resp.setContentType("text/html; charset=UTF-8;");
		
		String empl_id = "";
		if(authentication == null) {
			Function.alertHistoryBack(resp, "로그인 정보가 없습니다.", "/login/login.do", "");
			return null;
		}
		
		return "hr/order/orderList";
	}
	
	@PostMapping(value="orderSearchList.do", produces = "application/json;")
	public @ResponseBody String orderSearchList(Model model,
												Authentication authentication,
												HttpServletResponse resp) {
		log.info("HolidayController orderSearchList 발령현황 조회");
		resp.setContentType("text/html; charset=UTF-8;");
	
		String empl_id = "";
		if(authentication == null) {
			return "{\"error\":\"로그인 정보가 없습니다\"}";
		}else {
			empl_id = authentication.getName();
		}

		List<OrderInfoListDto> orderDto = orderService.getOrderList(empl_id);
		try {
	        // ObjectMapper를 사용하여 객체를 JSON 문자열로 변환
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(orderDto);
		} catch (JsonProcessingException e) {
	        return "{\"error\":\"JSON 변환 오류\"}";
		}
	}

	
	@GetMapping("orderAdminList.do")
	public String orderAdminList(Model model,
								 Authentication authentication,
								 HttpServletResponse resp) {
		log.info("OrderController orderAdminList 발령현황 조회");
		resp.setContentType("text/html; charset=UTF-8;");
		
		if(authentication == null) {
			Function.alertHistoryBack(resp, "로그인 정보가 없습니다.", "/login/login.do", "");
			return null;
		}

		Map<String, Object> mapDept = new HashMap<String, Object>();
		mapDept.put("role", "DT");
		
		Map<String, Object> mapRank = new HashMap<String, Object>();
		mapRank.put("role", "RK");

		Map<String, Object> mapPosit = new HashMap<String, Object>();
		mapPosit.put("role", "PN");

		Map<String, Object> mapOrder = new HashMap<String, Object>();
		mapOrder.put("role", "OR");
		
		List<CommonCodeDto> deptList = codeService.selectAllRole(mapDept);
		List<CommonCodeDto> rankList = codeService.selectAllRole(mapRank);
		List<CommonCodeDto> positionList = codeService.selectAllRole(mapPosit);
		List<CommonCodeDto> orderList = codeService.selectAllRole(mapOrder);

		model.addAttribute("deptList", deptList);
		model.addAttribute("rankList", rankList);
		model.addAttribute("positionList", positionList);
		model.addAttribute("orderList", orderList);
		
		return "hr/order/orderAdminList";
	}
	
	@PostMapping(value="orderSearchAdminList.do", produces = "application/json;")
	public @ResponseBody String orderSearchAdminList(@RequestParam(required = false) Map<String, Object> orderSearchMap, 
													 @RequestParam(name="empl_dept_cd", required = false) String[] arr_empl_dept_cd,
													 @RequestParam(name="empl_rank_cd", required = false) String[] arr_empl_rank_cd,
													 @RequestParam(name="empl_position_cd", required = false) String[] arr_empl_position_cd,
													 @RequestParam(name="emod_type", required = false) String[] arr_emod_type,
													 Model model,
													 Authentication authentication,
													 HttpServletResponse resp) {
		log.info("HolidayController orderSearchAdminList 발령현황 조회");
		resp.setContentType("text/html; charset=UTF-8;");
	
		String empl_id = "";
		if(authentication == null) {
			return "{\"error\":\"로그인 정보가 없습니다\"}";
		}
		
		// 부서,직위,직책 정보
		if(arr_empl_dept_cd != null && arr_empl_dept_cd.toString() != "") {
			orderSearchMap.put("empl_dept_cd", Arrays.asList(arr_empl_dept_cd));
		}
		if(arr_empl_rank_cd != null && arr_empl_rank_cd.toString() != "") {
			orderSearchMap.put("empl_rank_cd", Arrays.asList(arr_empl_rank_cd));
		}
		if(arr_empl_position_cd != null && arr_empl_position_cd.toString() != "") {
			orderSearchMap.put("empl_position_cd", Arrays.asList(arr_empl_position_cd));
		}
		if(arr_emod_type != null && arr_emod_type.toString() != "") {
			orderSearchMap.put("emod_type", Arrays.asList(arr_emod_type));
		}
		log.info("orderSearchMap : {}", orderSearchMap);

		if(orderSearchMap.get("empl_dept_cd")==null || orderSearchMap.get("empl_dept_cd")=="") {
			log.info("empl_dept_cd : 공백");
		}
		if(orderSearchMap.get("empl_rank_cd")==null || orderSearchMap.get("empl_rank_cd")=="") {
			log.info("empl_rank_cd : 공백");
		}
		if(orderSearchMap.get("empl_position_cd")==null || orderSearchMap.get("empl_position_cd")=="") {
			log.info("empl_position_cd : 공백");
		}		
		if(orderSearchMap.get("emod_type")==null || orderSearchMap.get("emod_type")=="") {
			log.info("emod_type : 공백");
		}		
		
		List<OrderInfoListDto> orderDto = orderService.getOrderAdminList(orderSearchMap);
		try {
	        // ObjectMapper를 사용하여 객체를 JSON 문자열로 변환
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(orderDto);
		} catch (JsonProcessingException e) {
	        return "{\"error\":\"JSON 변환 오류\"}";
		}
	}
	
	
	@GetMapping("registOrderAdmin.do")
	public String registOrderAdmin(Model model,
			 					   Authentication authentication,
			 					   HttpServletResponse resp) {
		log.info("HolidayController registOrderAdmin 발령현황 입력 화면");
		resp.setContentType("text/html; charset=UTF-8;");

		if(authentication == null) {
			Function.alertHistoryBack(resp, "로그인 정보가 없습니다.", "/login/login.do", "");
			return null;
		}
		
		//부서,직위,직책,발령구분
		Map<String, Object> mapDept = new HashMap<String, Object>();
		mapDept.put("role", "DT");
		
		Map<String, Object> mapRank = new HashMap<String, Object>();
		mapRank.put("role", "RK");

		Map<String, Object> mapPosit = new HashMap<String, Object>();
		mapPosit.put("role", "PN");

		Map<String, Object> mapOrder = new HashMap<String, Object>();
		mapOrder.put("role", "OR");
		
		List<CommonCodeDto> deptList = codeService.selectAllRole(mapDept);
		List<CommonCodeDto> rankList = codeService.selectAllRole(mapRank);
		List<CommonCodeDto> positionList = codeService.selectAllRole(mapPosit);
		List<CommonCodeDto> orderList = codeService.selectAllRole(mapOrder);

		model.addAttribute("deptList", deptList);
		model.addAttribute("rankList", rankList);
		model.addAttribute("positionList", positionList);
		model.addAttribute("orderList", orderList);
		
		return "hr/order/registOrderAdmin";
	}

	@PostMapping("registOrderAdminOk.do")
	public @ResponseBody void registOrderAdminOk(Model model,
							 					 Authentication authentication,
							 					 HttpServletResponse resp) {
		log.info("HolidayController registOrderAdmin 발령현황 입력 처리");
		resp.setContentType("text/html; charset=UTF-8;");
		
		String empl_id = "";
		if(authentication == null) {
			Function.alertHistoryBack(resp, "로그인 정보가 없습니다.", "/login/login.do", "");
			return;
		}else {
			empl_id = authentication.getName();
		}
	
	}
	
	
}
