package com.test.kjw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcm.grw.dto.hr.EmpSignDto;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.dto.hr.SignDocBoxDto;
import com.hcm.grw.model.service.hr.CommonCodeService;
import com.hcm.grw.model.service.hr.CompanyService;
import com.hcm.grw.model.service.hr.EmpSignService;
import com.hcm.grw.model.service.hr.EmployeeListService;
import com.hcm.grw.model.service.hr.SignDocBoxService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*.xml")
public class KJW_JUnitTest {

	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	@Autowired
	private CommonCodeService codeService;
	
	@Autowired
	private EmpSignService empSignService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private SignDocBoxService boxService;
	
	@Autowired
	private EmployeeListService employeeListService;
	
	@Before
	public void test() {
		assertNotNull(sessionTemplate);
	}
	
	
	
	//@Test
	public void testSign() {	
		
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("empl_id", "20230102");
		List<EmpSignDto> list = empSignService.selectAllSign(map4);
		System.out.println(list);
		assertNotNull(list);
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("empl_id", "20230102");
//		map.put("emsi_title", "서명추가용");
//		map.put("emsi_sign_img", "www.naver.com2");
//		map.put("emsi_create_id", "20230102");
//		int cnt = empSignService.insertSign(map);
//		assertEquals(1, cnt);
		
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("emsi_seq", "2");
		map3.put("empl_id", "20230102");
		int cnt3 = empSignService.setDefaultSign(map3);
		assertEquals(1, cnt3);
		
		List<EmpSignDto> list2 = empSignService.selectAllSign(map4);
		System.out.println(list2);
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("emsi_seq", "2");
		map2.put("empl_id", "20230102");
		int cnt2 = empSignService.deleteSign(map2);
		assertEquals(1, cnt2);
		
		List<EmpSignDto> list3 = empSignService.selectAllSign(map4);
		System.out.println(list3);
		
	}
	
	//@Test
	public void testCompany() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("comp_id", "ITCOM0A1");
		map.put("comp_name", "HCMC");
		map.put("comp_num", "101-05-64127");
		map.put("comp_ceo_name", "오지수");
		map.put("comp_tel", "02-818-7950");
		map.put("comp_fax", "02-542-1142");
		map.put("comp_post", "08505");
		map.put("comp_addr1", "서울특별시 금천구 가산디지털2로 95");
		map.put("comp_addr2", "3층 305호");
		map.put("comp_create_id", "SYSTEM");
		int cnt = companyService.insertCompanyInfo(map);
		assertEquals(1, cnt);
//		
//		Map<String, Object> map3 = new HashMap<String, Object>();
//		map3.put("comp_id", "ITCOM0A1");
//		CompanyDto dto = companyService.showCompanyInfo(map3);
//		System.out.println(dto);
//		assertNotNull(dto);
//		
//		Map<String, Object>  map2 = new HashMap<String, Object>();
//		map2.put("comp_name", "HCMC수정");
//		map2.put("comp_num", "111-11-11111");
//		map2.put("comp_ceo_name", "김수정");
//		map2.put("comp_tel", "01-222-3333");
//		map2.put("comp_fax", "01-777-1111");
//		map2.put("comp_post", "99999");
//		map2.put("comp_addr1", "수정후");
//		map2.put("comp_addr2", "주소");
//		map2.put("comp_seal", "도장파일명");
//		map2.put("comp_modify_id", "SYSTEM");
//		map2.put("comp_id", "ITCOM0A1");
//		
////		int cnt2 = companyService.correctionCompanyInfo(map2);
////		assertEquals(1, cnt2);
//		
//		CompanyDto dto2 = companyService.showCompanyInfo(map3);
//		System.out.println(dto2);
		
	}
	
	//@Test
	public void testSignDocBox() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empl_id", "20220101");
		List<SignDocBoxDto> lists = boxService.selectAllDocList(map);
		System.out.println(lists);
		assertNotNull(lists);
		map.put("sidb_doc_num", "24000003");
		SignDocBoxDto dto = boxService.selectOneDocList(map);
		System.out.println(dto);
		assertNotNull(dto);
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("emdn_id", "24000003");
		map2.put("emdh_empl_id", "20220101");
		map2.put("emdh_type", "D");
		int cnt = boxService.downloadOneDoc(map2);
		assertEquals(1, cnt);
		
	}
	
	//@Test
	public void testEmployeeListService() {
		List<EmployeeDto> lists = employeeListService.selectAllEmployee();
		System.out.println(lists);
		assertNotNull(lists);
		
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("empl_name", "김재원");
//		map.put("empl_dept_cd", "DT000004");
//		map.put("empl_rank_cd", "RK000002");
//		map.put("empl_position_cd", "PN000002");
//		map.put("startdate", "2023-01-10");
//		map.put("enddate", "2023-05-20");
//		map.put("empl_id", "20230102");
//		Map<String, Object> map2 = new HashMap<String, Object>();
//		map2.put("empl_name", "김재원수정");
//		map2.put("empl_birth", "19990304");
//		map2.put("empl_email", "test1234@gmail.com");
//		map2.put("empl_phone", "010-9999-1111");
//		map2.put("empl_tel", "999");
//		map2.put("empl_fax", "02-111-9999");
//		map2.put("empl_modify_id", "SYSTEM");
//		map2.put("empl_id", "20240012");
//		
//		
//		List<EmployeeDto> list = employeeListService.searchAllEmployee(map);
//		System.out.println(list);
//		assertNotNull(list);
//		
//		EmployeeDto dto = employeeListService.selectOneEmployee(map);
//		System.out.println(dto);
//		assertNotNull(dto);
//		
//		int cnt = employeeListService.correctionEmployee(map2);
//		assertEquals(1, cnt);
//		
//		List<EmployeeDto> lists2 = employeeListService.selectAllEmployee();
//		System.out.println(lists2);
		
	}
	
	
	@Test
	public void test2() {
		String empl_phone = "010-1234-3152";
		String empl_tel = "999";
		String empl_fax = "031-744-1221";
		boolean bool1 = employeeListService.chkEmpPhoneNum(empl_phone);
		boolean bool2 = employeeListService.chkEmpTelNum(empl_tel);
		boolean bool3 = employeeListService.chkEmpFaxNum(empl_fax);
		
		System.out.println(bool1);
		System.out.println(bool2);
		System.out.println(bool3);
	}
}
