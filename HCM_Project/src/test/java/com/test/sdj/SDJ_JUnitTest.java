package com.test.sdj;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcm.grw.dto.hr.CommuteDto;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.dto.hr.HolidayDto;
import com.hcm.grw.dto.hr.OrderInfoDetailDto;
import com.hcm.grw.dto.hr.OrderInfoListDto;
import com.hcm.grw.dto.hr.SnsInfoDto;
import com.hcm.grw.model.mapper.hr.EmployeeDao;
import com.hcm.grw.model.service.hr.CommuteService;
import com.hcm.grw.model.service.hr.EmployeeService;
import com.hcm.grw.model.service.hr.HolidayService;
import com.hcm.grw.model.service.hr.OrderService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*.xml")
@Slf4j
public class SDJ_JUnitTest {

	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CommuteService commuteService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private HolidayService holidayService;
	
	@Autowired
	private EmployeeDao empDao;
	
	@Before
	public void test() {
		assertNotNull(sessionTemplate);
	}
	
	//@Test
	public void EmployeeTest() {
		//사원정보 입력
		/*
		EmployeeDto empInsertDto = new EmployeeDto();
		empInsertDto.setEmpl_pwd("1111");
		empInsertDto.setEmpl_name("테스트");
		empInsertDto.setEmpl_birth("20200304");
		empInsertDto.setEmpl_gender("M");
		empInsertDto.setEmpl_email("test@gmail.com");
		empInsertDto.setEmpl_phone("010-2222-3333");
		empInsertDto.setEmpl_joindate("2019-03-02");
		empInsertDto.setEmpl_dept_cd("DT000002");
		empInsertDto.setEmpl_rank_cd("RK000003");
		empInsertDto.setEmpl_auth("ROLE_HR_ADMIN");
		empInsertDto.setEmpl_create_id("SYSTEM");
		log.info("사원정보 입력 : {}", empInsertDto);
		int n = employeeService.registEmployee(empInsertDto);
		assertEquals(1, n);

		String empl_id = empInsertDto.getEmpl_id();
		*/
		String empl_id = null;
		log.info("사번 : {}", empl_id);
		if(empl_id == null) {
			empl_id = "20240012";
		}

		// 사원정보 수정
		EmployeeDto empUpdateDto = new EmployeeDto();
		empUpdateDto.setEmpl_phone("010-4444-5555");
		empUpdateDto.setEmpl_tel("556");
		empUpdateDto.setEmpl_fax("02-1234-9999");
		//empUpdateDto.setEmpl_picture(null);
		empUpdateDto.setEmpl_modify_id("SYSTEM");
		empUpdateDto.setEmpl_id(empl_id);
		log.info("사원정보 수정 정보 : {}", empUpdateDto);
		int n1 = employeeService.updateEmployee(empUpdateDto);
		assertEquals(1, n1);
		
		//인번호 메일발송 본인확인
		Map<String, Object> initPwdChkMap = new HashMap<String, Object>();
		initPwdChkMap.put("empl_id", empl_id);
		initPwdChkMap.put("empl_name", "테스트");
		initPwdChkMap.put("empl_email", "test@gmail.com");
		log.info("메일발송 본인확인 정보 : {}", initPwdChkMap);
		int n2 = employeeService.getInitPwdcheck(initPwdChkMap);
		assertEquals(1, n2);
		
		//비밀번호 초기화
		Map<String, Object> initPwdMap = new HashMap<String, Object>();
		initPwdMap.put("empl_pwd", "2222");
		initPwdMap.put("empl_modify_id", empl_id);
		initPwdMap.put("empl_id", empl_id);
		log.info("비밀번호 초기화 정보 : {}", initPwdMap);
		int n3 = employeeService.setInitPwd(initPwdMap);
		assertEquals(1, n3);

		//비밀번호 수정
		Map<String, Object> chgPwdMap = new HashMap<String, Object>();
		chgPwdMap.put("empl_new_pwd", "1111");
		chgPwdMap.put("empl_modify_id", empl_id);
		chgPwdMap.put("empl_id", empl_id);
		chgPwdMap.put("empl_pwd", "2222");
		log.info("비밀번호 변경정보 : {}", chgPwdMap);
		int n7 = employeeService.updatePwd(chgPwdMap);
		assertEquals(1, n7);
		
		//로그인
		EmployeeDto loginDto = employeeService.getLogin(empl_id);
		log.info("로그인 정보 : {}", loginDto);
		assertNotNull(loginDto);
		
		//권한관리 리스트
		List<EmployeeDto> authLists = employeeService.getAuthList();
		log.info("권한리스트 : {}", authLists);
		assertNotEquals(0, authLists.size());

		//권한관리 상세
		EmployeeDto authDetail = employeeService.getAuthDetail(empl_id);
		log.info("권한상세 : {}", authDetail);
		assertNotNull(authDetail);

		//권한부여 처리
		Map<String, Object> authMap = new HashMap<String, Object>();
		authMap.put("empl_auth", "ROLE_HR_ADMIN");
		authMap.put("empl_modify_id", "SYSTEM");
		authMap.put("empl_id", empl_id);
		log.info("권한부여 처리 : {}", authMap);
		int n4 = employeeService.updateAuthEmployee(authMap);
		assertEquals(1, n4);

		//SNS간편로그인 등록
		Map<String, Object> snsLoginMap = new HashMap<String, Object>();
		snsLoginMap.put("emsn_id", "1234567890");
		snsLoginMap.put("empl_id", empl_id);
		snsLoginMap.put("emsn_email", "test@gmail.com");
		log.info("sns간편로그인 등록 : {}", snsLoginMap);
		int n5 = employeeService.registSnsLoginInfo(snsLoginMap);
		assertEquals(1, n5);
		
		//SNS간편로그인 조회
		SnsInfoDto snsDto = employeeService.getSnsInfo(empl_id);
		String emsn_id = snsDto.getEmsn_id();
		log.info("sns간편로그인 조회 : {}", snsDto);
		assertNotNull(snsDto);

		//SNS간편로그인 삭제
		int n6 = employeeService.delSnsInfo(emsn_id);
		log.info("sns간편로그인 삭제 수 : {}", n6);
		assertEquals(1, n6);
	}

	//@Test
	public void CommuteTest() {
		//출근 등록
		String empl_id = "20240012";
		CommuteDto dto = new CommuteDto();
		dto.setEmpl_id(empl_id);
		log.info("출근등록 id :  {}", empl_id);
		int n1 = commuteService.registCommute(dto);
		log.info("출근처리 수 : {}", n1);
		assertEquals(1, n1);
		
		//퇴근등록
		int n2 = commuteService.updateCommute(empl_id);
		log.info("퇴근등록 :  {}", dto);
		assertEquals(1, n2);
	}
	
	//@Test
	public void OrderTest() {
		String createId = "20220101";
		/*
		OrderInfoMasterDto odDtoMaster = new OrderInfoMasterDto();
		List<OrderInfoDetailDto> odDtoDetails = new ArrayList<OrderInfoDetailDto>();
		OrderInfoDetailDto odDtoDetail = new OrderInfoDetailDto();
		odDtoMaster.setEmor_create_id(createId);
		
		odDtoDetail.setEmpl_id("20240012");
		odDtoDetail.setEmod_order_dt("2024-03-05");
		odDtoDetail.setEmod_type("OR000002");
		odDtoDetail.setEmod_prev_dept("DT000002");
		odDtoDetail.setEmod_order_dept("DT000004");
		odDtoDetail.setEmod_prev_rank("RK000003");
		odDtoDetail.setEmod_order_rank(null);
		odDtoDetail.setEmod_prev_position(null);
		odDtoDetail.setEmod_order_position(null);
		odDtoDetail.setEmod_create_id(createId);
		odDtoDetails.add(odDtoDetail);
		
		boolean flag = orderService.registOrderAdmin(odDtoMaster, odDtoDetails);
		assertEquals(true, flag);
		*/
		
		// 발령수정
		List<OrderInfoDetailDto> odDtoDetails = new ArrayList<OrderInfoDetailDto>();
		OrderInfoDetailDto odDtoDetail = new OrderInfoDetailDto();
		odDtoDetail.setEmor_id("O2024002");
		odDtoDetail.setEmod_seq(1);
		odDtoDetail.setEmpl_id("20240012");
		odDtoDetail.setEmod_order_dt("2024-03-05");
		odDtoDetail.setEmod_type("OR000002");
		odDtoDetail.setEmod_prev_dept("DT000002");
		odDtoDetail.setEmod_order_dept("DT000003");
		odDtoDetail.setEmod_prev_rank("RK000003");
		odDtoDetail.setEmod_order_rank(null);
		odDtoDetail.setEmod_prev_position(null);
		odDtoDetail.setEmod_order_position(null);
		odDtoDetail.setEmod_modify_id(createId);
		odDtoDetails.add(odDtoDetail);
		
		boolean flag = orderService.updateOrderAdminDetail(odDtoDetails, null, odDtoDetails.size());
		assertEquals(true, flag);
		
		// 발령 리스트
		List<OrderInfoListDto> lists = orderService.getOrderList("20220101");
		log.info("리스트 정보 : {}", lists);
		assertNotEquals(0, lists.size());
	}
	
	//@Test
	public void HolidayTest() {
		//휴가정보 조회
		String empl_id = "20230107";
		Map<String, String> map = new HashMap<String, String>(){{
			put("empl_id", "20230107");
		}};
		List<HolidayDto> had = holidayService.holidayList(map);
		log.info("휴가정보 리스트 : {}", had);
		assertNotEquals(0, had.size());
	}
	
}
