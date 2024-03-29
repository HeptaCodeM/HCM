package com.hcm.grw.ctrl.hr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import com.hcm.grw.dto.hr.OrderInfoAdminDto;
import com.hcm.grw.dto.hr.OrderInfoDetailDto;
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
	
	/* 발령리스트(임직원) */
	@GetMapping("orderList.do")
	public String orderList(Model model,
							Authentication authentication) {
		log.info("{} 발령현황 조회", Function.getMethodName());
		
		if(authentication == null) {
			Function.alertHistoryBack("로그인 정보가 없습니다.("+Function.getMethodName()+")", "/login/login.do", "");
			return null;
		}
		
		return "hr/order/orderList";
	}
	
	/* 발령리스트(임직원)-REST */
	@PostMapping(value="orderSearchList.do", produces = "application/json;")
	public @ResponseBody String orderSearchList(Model model,
												Authentication authentication) {
		log.info("{} 발령현황 조회-REST", Function.getMethodName());
	
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

	
	/* 발령리스트(관리자) */
	@GetMapping("orderAdminList.do")
	public String orderAdminList(Model model,
								 Authentication authentication) {
		log.info("{} 발령현황 조회", Function.getMethodName());
		
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

	
	/* 발령리스트(관리자) - REST */
	@PostMapping(value="orderSearchAdminList.do", produces = "application/json;")
	public @ResponseBody String orderSearchAdminList(@RequestParam(required = false) Map<String, Object> orderSearchMap, 
													 @RequestParam(name="empl_dept_cd", required = false) String[] arr_empl_dept_cd,
													 @RequestParam(name="empl_rank_cd", required = false) String[] arr_empl_rank_cd,
													 @RequestParam(name="empl_position_cd", required = false) String[] arr_empl_position_cd,
													 @RequestParam(name="emod_type", required = false) String[] arr_emod_type,
													 Model model,
													 Authentication authentication) {
		log.info("{} 발령현황 조회-REST", Function.getMethodName());
	
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
	
	
	/* 발령등록페이지 */
	@GetMapping("registOrderAdmin.do")
	public String registOrderAdmin(Model model,
			 					   Authentication authentication) {
		log.info("{} 발령현황 입력 화면", Function.getMethodName());

		if(authentication == null) {
			Function.alertHistoryBack("로그인 정보가 없습니다.("+Function.getMethodName()+")", "/login/login.do", "");
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

	/* 발령등록처리 */
	@PostMapping("registOrderAdminOk.do")
	public @ResponseBody void registOrderAdminOk(Model model,
							 					 Authentication authentication,
							 					 @RequestParam(name="orderRows", required = true) int orderRows,
							 					 @RequestParam(name="emod_order_dt", required = true) String[] arr_emod_order_dt,
							 					 @RequestParam(name="empl_id", required = true) String[] arr_empl_id,
							 					 @RequestParam(name="emod_type", required = true) String[] arr_emod_type,
							 					 @RequestParam(name="emod_prev_dept", required = true) String[] arr_emod_prev_dept,
							 					 @RequestParam(name="emod_order_dept", required = false) String[] arr_emod_order_dept,
							 					 @RequestParam(name="emod_prev_rank", required = true) String[] arr_emod_prev_rank,
							 					 @RequestParam(name="emod_order_rank", required = false) String[] arr_emod_order_rank,
							 					 @RequestParam(name="emod_prev_position", required = false) String[] arr_emod_prev_position,
							 					 @RequestParam(name="emod_order_position", required = false) String[] arr_emod_order_position
							 					 ) {
		log.info("{} 발령현황 입력 처리", Function.getMethodName());
		
		
		String emor_create_id = "";
		if(authentication == null) {
			Function.alertHistoryBack("로그인 정보가 없습니다.("+Function.getMethodName()+")", "/login/login.do", "");
			return;
		}else {
			emor_create_id = authentication.getName();
		}
		
		OrderInfoAdminDto adminDto;
		List<OrderInfoDetailDto> detailListDto;
		OrderInfoDetailDto detailDto;
		
		adminDto = new OrderInfoAdminDto();
		adminDto.setEmor_create_id(emor_create_id);
		detailListDto = new ArrayList<OrderInfoDetailDto>();
		
		//log.info("orderRows : {}", orderRows);
		if(orderRows < 1) {
			Function.alertHistoryBack("발령데이터 정보가 없습니다.", "", "");
			return;
		}else {
			String emod_order_dt = "";
			String empl_id = "";
			String emod_type = "";
			String emod_prev_dept = "";
			String emod_prev_rank = "";
			String emod_prev_position = "";
			String emod_order_dept = "";
			String emod_order_rank = "";
			String emod_order_position = "";
			
			for(int i=0;i<orderRows;i++) {
				emod_order_dt = StringUtils.defaultIfEmpty(arr_emod_order_dt[i], "");
				empl_id = StringUtils.defaultIfEmpty(arr_empl_id[i], "");
				emod_type = StringUtils.defaultIfEmpty(arr_emod_type[i], "");
				emod_prev_dept = StringUtils.defaultIfEmpty(arr_emod_prev_dept[i], "");
				emod_prev_rank = StringUtils.defaultIfEmpty(arr_emod_prev_rank[i], "");
				emod_prev_position = StringUtils.defaultIfEmpty(arr_emod_prev_position[i], "");
				if(arr_emod_order_dept == null) {
					emod_order_dept = "";
				}else {
					emod_order_dept = StringUtils.defaultIfEmpty(arr_emod_order_dept[i], "");
				}
				if(arr_emod_order_rank == null) {
					emod_order_rank = "";
				}else {
					emod_order_rank = StringUtils.defaultIfEmpty(arr_emod_order_rank[i], "");
				}
				if(arr_emod_order_position == null) {
					emod_order_position = "";
				}else {
					emod_order_position = StringUtils.defaultIfEmpty(arr_emod_order_position[i], "");
				}
				// 데이터가 모두 유효한 것만 입력 처리
				if(
					(emod_order_dt != "" && empl_id != "" && emod_type != "" && emod_prev_dept != "" && emod_prev_rank != "")
					&&
					(
						(emod_type.equals("OR000002") && emod_order_dept != "") ||
						(emod_type.equals("OR000003") && emod_order_rank != "") ||
						(emod_type.equals("OR000004") && emod_order_position != "")
					)
				) {
					log.info("detailDto 시작==================");
					detailDto = new OrderInfoDetailDto();
					detailDto.setEmod_order_dt(emod_order_dt);
					detailDto.setEmpl_id(empl_id);
					detailDto.setEmod_type(emod_type);
					detailDto.setEmod_prev_dept(emod_prev_dept);
					detailDto.setEmod_prev_rank(emod_prev_rank);
					detailDto.setEmod_prev_position(emod_prev_position);
					if(emod_type.equals("OR000002")) {
						detailDto.setEmod_order_dept(emod_order_dept);
					} else if(emod_type.equals("OR000003")) {
						detailDto.setEmod_order_rank(emod_order_rank);
					} else if(emod_type.equals("OR000004")) {
						detailDto.setEmod_order_position(emod_order_position);
					} else {
						Function.alertHistoryBack("발령구분에 대한 입력정보가 없습니다.", "", "");
						return;
					}
					detailDto.setEmod_create_id(emor_create_id);
					detailListDto.add(detailDto);
					log.info("detailListDto 개별데이터 : {}", detailListDto);
				}
			}
			adminDto.setListDetailDto(detailListDto);
		}
		
		log.info("등록데이터 : {}", adminDto);
		
		boolean flag = orderService.registOrderAdmin(adminDto);
		if(flag) {
			Function.alertLocation("등록처리가 완료 되었습니다.", "/hr/order/orderAdminDetail.do?emor_id="+adminDto.getEmor_id(), "", "", "");
			return;
		}else {
			Function.alertHistoryBack("등록 중 오류가 발생하였습니다.", "", "");
			return;
		}
		
	}

	/* 발령상세 */
	@GetMapping("orderAdminDetail.do")
	public String orderAdminDetail(@RequestParam(required = true) String emor_id,
									Model model,
									Authentication authentication) {
		log.info("{} 수정페이지 진입", Function.getMethodName());
		
		if(authentication == null) {
			Function.alertHistoryBack("로그인 정보가 없습니다.("+Function.getMethodName()+")", "/login/login.do", "");
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
		
		
		List<OrderInfoListDto> detailLists = orderService.selectOrderAdminDetail(emor_id);
		
		model.addAttribute("deptList", deptList);
		model.addAttribute("rankList", rankList);
		model.addAttribute("positionList", positionList);
		model.addAttribute("orderList", orderList);		
		
		model.addAttribute("emor_id", emor_id);
		model.addAttribute("detailLists", detailLists);
		
		return "hr/order/orderAdminDetail";
	}
	
	/* 발령 세부정보 삭제 */
	@PostMapping(value="deleteOrderAdminDetail.do", produces = "application/json;")
	public @ResponseBody String deleteOrderAdminDetail(@RequestParam(name="emor_id", required = false) String emor_id,
													 @RequestParam(name="emod_seq", required = false) String emod_seq,
													 Model model,
													 Authentication authentication) {
		log.info("{} 수정페이지 진입", Function.getMethodName());
	
		String empl_id = "";
		if(authentication == null) {
			return "{\"error\":\"로그인 정보가 없습니다\"}";
		}
		
		if(StringUtils.defaultIfEmpty(emor_id, "") != "" || StringUtils.defaultIfEmpty(emod_seq, "") != "") {
			OrderInfoDetailDto delDto = new OrderInfoDetailDto();
			delDto.setEmor_id(emor_id);
			delDto.setEmod_seq(Integer.valueOf(emod_seq));
			int n = orderService.deleteOrderAdminDetail(delDto);
			if(n>0) {
		        return "{\"result\":\"true\"}";
			}else {
		        return "{\"result\":\"false\"}";
			}
		}else {
			return "{\"error\":\"전달값이 없습니다.\"}";
		}

	}

	//발령 삭제처리
	@GetMapping("deleteOrderAdminOk.do")
	public @ResponseBody void deleteOrderAdminOk(@RequestParam(required = true) String emor_id,
													Model model,
													Authentication authentication) {
		log.info("{} 발령현황 전체삭제 처리", Function.getMethodName());
		
		if(authentication == null) {
			Function.alertHistoryBack("로그인 정보가 없습니다.("+Function.getMethodName()+")", "/login/login.do", "");
			return;
		}
		
		OrderInfoDetailDto detailDto = new OrderInfoDetailDto();
		detailDto.setEmor_id(emor_id);
		
		int cnt = orderService.deleteOrderAdmin(detailDto);
		if(cnt > 0) {
			Function.alertLocation("삭제가 완료 되었습니다.", "/hr/order/orderAdminList.do", "", "", "");
			return;
		}else {
			Function.alertHistoryBack("삭제에 실패 하였습니다.", "", "");
			return;
		}
	}
	
	// 발령확정 처리
	@GetMapping("confirmOrderAdminOk.do")
	public @ResponseBody void confirmOrderAdminOk(@RequestParam(required = true) String emor_id,
			Model model,
			Authentication authentication) {
		log.info("{} 발령현황 확정 처리", Function.getMethodName());
		
		String emor_modify_id = "";
		if(authentication == null) {
			Function.alertHistoryBack("로그인 정보가 없습니다.("+Function.getMethodName()+")", "/login/login.do", "");
			return;
		}else {
			emor_modify_id = authentication.getName();
		}
		
		OrderInfoAdminDto adminDto = new OrderInfoAdminDto();
		adminDto.setEmor_id(emor_id);
		adminDto.setEmor_modify_id(emor_modify_id);
		
		int cnt = orderService.confirmOrderAdmin(adminDto);
		if(cnt > 0) {
			Function.alertLocation("확정이 완료 되었습니다.", "/hr/order/orderAdminDetail.do?emor_id="+emor_id, "", "", "");
			return;
		}else {
			Function.alertHistoryBack("확정에 실패 하였습니다.", "", "");
			return;
		}
	}	


	@PostMapping("modifyOrderAdminOk.do")
	public @ResponseBody void modifyOrderAdminOk(Model model,
												 Authentication authentication,
												 @RequestParam(name="orderRows", required = true) int orderRows,
												 @RequestParam(name="emor_id", required = true) String emor_id,
												 @RequestParam(name="emod_seq", required = true) int[] arr_emod_seq,
												 @RequestParam(name="emod_order_dt", required = true) String[] arr_emod_order_dt,
												 @RequestParam(name="empl_id", required = true) String[] arr_empl_id,
												 @RequestParam(name="emod_type", required = true) String[] arr_emod_type,
												 @RequestParam(name="emod_prev_dept", required = true) String[] arr_emod_prev_dept,
												 @RequestParam(name="emod_order_dept", required = false) String[] arr_emod_order_dept,
												 @RequestParam(name="emod_prev_rank", required = true) String[] arr_emod_prev_rank,
												 @RequestParam(name="emod_order_rank", required = false) String[] arr_emod_order_rank,
												 @RequestParam(name="emod_prev_position", required = false) String[] arr_emod_prev_position,
												 @RequestParam(name="emod_order_position", required = false) String[] arr_emod_order_position
												 ) {
		log.info("{} 발령현황 수정 처리", Function.getMethodName());


		String emod_modify_id = "";
		if(authentication == null) {
		Function.alertHistoryBack("로그인 정보가 없습니다.("+Function.getMethodName()+")", "/login/login.do", "");
		return;
		}else {
			emod_modify_id = authentication.getName();
		}


		OrderInfoDetailDto detailInsertDto;
		OrderInfoDetailDto detailUpdateDto;
		
		List<OrderInfoDetailDto> detailInsertListDto = new ArrayList<OrderInfoDetailDto>();
		List<OrderInfoDetailDto> detailUpdateListDto = new ArrayList<OrderInfoDetailDto>();
		
		
		//log.info("orderRows : {}", orderRows);
		if(orderRows < 1) {
			Function.alertHistoryBack("발령데이터 정보가 없습니다.", "", "");
			return;
		}else {
			int emod_seq = 0;
			String emod_order_dt = "";
			String empl_id = "";
			String emod_type = "";
			String emod_prev_dept = "";
			String emod_prev_rank = "";
			String emod_prev_position = "";
			String emod_order_dept = "";
			String emod_order_rank = "";
			String emod_order_position = "";
			
			for(int i=0;i<orderRows;i++) {
				emod_seq = Integer.parseInt(StringUtils.defaultIfEmpty(String.valueOf(arr_emod_seq[i]), "0"));
				emod_order_dt = StringUtils.defaultIfEmpty(arr_emod_order_dt[i], "");
				empl_id = StringUtils.defaultIfEmpty(arr_empl_id[i], "");
				emod_type = StringUtils.defaultIfEmpty(arr_emod_type[i], "");
				emod_prev_dept = StringUtils.defaultIfEmpty(arr_emod_prev_dept[i], "");
				emod_prev_rank = StringUtils.defaultIfEmpty(arr_emod_prev_rank[i], "");
				emod_prev_position = StringUtils.defaultIfEmpty(arr_emod_prev_position[i], "");
				if(arr_emod_order_dept == null) {
					emod_order_dept = "";
				}else {
					emod_order_dept = StringUtils.defaultIfEmpty(arr_emod_order_dept[i], "");
				}
				if(arr_emod_order_rank == null) {
					emod_order_rank = "";
				}else {
					emod_order_rank = StringUtils.defaultIfEmpty(arr_emod_order_rank[i], "");
				}
				if(arr_emod_order_position == null) {
					emod_order_position = "";
				}else {
					emod_order_position = StringUtils.defaultIfEmpty(arr_emod_order_position[i], "");
				}
				// 데이터가 모두 유효한 것만 입력 처리
				if(
					(emod_order_dt != "" && empl_id != "" && emod_type != "" && emod_prev_dept != "" && emod_prev_rank != "")
					&&
					(
						(emod_type.equals("OR000002") && emod_order_dept != "") ||
						(emod_type.equals("OR000003") && emod_order_rank != "") ||
						(emod_type.equals("OR000004") && emod_order_position != "")
					)
				) {
					if(emod_seq > 0) {	// 기존 있는 데이터 수정처리
						log.info("detailUpdateDto 시작==================");
						detailUpdateDto = new OrderInfoDetailDto();
						detailUpdateDto.setEmor_id(emor_id);
						detailUpdateDto.setEmod_seq(emod_seq);
						detailUpdateDto.setEmod_order_dt(emod_order_dt);
						detailUpdateDto.setEmpl_id(empl_id);
						detailUpdateDto.setEmod_type(emod_type);
						detailUpdateDto.setEmod_prev_dept(emod_prev_dept);
						detailUpdateDto.setEmod_prev_rank(emod_prev_rank);
						detailUpdateDto.setEmod_prev_position(emod_prev_position);
						if(emod_type.equals("OR000002")) {
							detailUpdateDto.setEmod_order_dept(emod_order_dept);
						} else if(emod_type.equals("OR000003")) {
							detailUpdateDto.setEmod_order_rank(emod_order_rank);
						} else if(emod_type.equals("OR000004")) {
							detailUpdateDto.setEmod_order_position(emod_order_position);
						} else {
							Function.alertHistoryBack("발령구분에 대한 입력정보가 없습니다.", "", "");
							return;
						}
						detailUpdateDto.setEmod_modify_id(emod_modify_id);
						detailUpdateListDto.add(detailUpdateDto);
						
					} else {				// 신규 입력 데이터 입력처리
						log.info("detailInsertDto 시작==================");
						detailInsertDto = new OrderInfoDetailDto();
						detailInsertDto.setEmor_id(emor_id);
						detailInsertDto.setEmod_seq(emod_seq);
						detailInsertDto.setEmod_order_dt(emod_order_dt);
						detailInsertDto.setEmpl_id(empl_id);
						detailInsertDto.setEmod_type(emod_type);
						detailInsertDto.setEmod_prev_dept(emod_prev_dept);
						detailInsertDto.setEmod_prev_rank(emod_prev_rank);
						detailInsertDto.setEmod_prev_position(emod_prev_position);
						if(emod_type.equals("OR000002")) {
							detailInsertDto.setEmod_order_dept(emod_order_dept);
						} else if(emod_type.equals("OR000003")) {
							detailInsertDto.setEmod_order_rank(emod_order_rank);
						} else if(emod_type.equals("OR000004")) {
							detailInsertDto.setEmod_order_position(emod_order_position);
						} else {
							Function.alertHistoryBack("발령구분에 대한 입력정보가 없습니다.", "", "");
							return;
						}
						detailInsertDto.setEmod_create_id(emod_modify_id);
						detailInsertListDto.add(detailInsertDto);
					}


				}
				log.info("***** detailInsertListDto 개별데이터 : {}", detailInsertListDto);
				log.info("***** detailUpdateListDto 개별데이터 : {}", detailUpdateListDto);
			}
			
			boolean flag = orderService.updateOrderAdminDetail(detailInsertListDto, detailUpdateListDto, orderRows);
			if(flag) {
				Function.alertLocation("수정처리가 완료 되었습니다.", "/hr/order/orderAdminDetail.do?emor_id="+emor_id, "", "", "");
				return;
			}else {
				Function.alertHistoryBack("수정 중 오류가 발생하였습니다.", "", "");
				return;
			}
			
		}
				
		
		
		
		
	}


}
