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

import com.hcm.grw.dto.hr.CommonCodeDto;
import com.hcm.grw.dto.hr.CompanyDto;
import com.hcm.grw.dto.hr.EmpSignDto;
import com.hcm.grw.model.service.hr.CommonCodeService;
import com.hcm.grw.model.service.hr.CompanyService;
import com.hcm.grw.model.service.hr.EmpSignService;

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
	
	@Before
	public void test() {
		assertNotNull(sessionTemplate);
	}
	
	//@Test
	public void testDept() {
		List<CommonCodeDto> list = codeService.selectAllDept();
		System.out.println(list);
		assertNotNull(list);
		
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("coco_cd", "DT000005");
//		map.put("coco_name", "개발팀");
//		map.put("coco_create_id", "SYSTEM");
//		int cnt = codeService.insertDeptOne(map);
//		assertEquals(1, cnt);
		
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("coco_cd", "DT000005");
//		int cnt = codeService.deleteDeptOne(map);
//		assertEquals(1, cnt);

//		List<CommonCodeDto> insertAfterlist = codeService.selectAllDept();
//		System.out.println(insertAfterlist);
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coco_cd", "DT000004");
		CommonCodeDto dto = codeService.selectOneDept(map);
		System.out.println(dto);
		assertNotNull(dto);
		
		
//		Map<String, Object> map2 = new HashMap<String, Object>();
//		map2.put("coco_cd", "DT000004");
//		map2.put("coco_name", "기획팀");
//		int cnt = codeService.correctionDept(map2);
//		assertEquals(1, cnt);
		
		CommonCodeDto afterDto = codeService.selectOneDept(map);
		System.out.println(afterDto);
		
	}
	
	//@Test
	public void testRank() {
		System.out.println("===================================================================");
		List<CommonCodeDto> list = codeService.selectAllRank();
		System.out.println(list);
		assertNotNull(list);
		System.out.println("===================================================================");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coco_cd", "RK000011");
		map.put("coco_name", "연구원");
		map.put("coco_create_id", "SYSTEM");
		int cnt = codeService.insertRankOne(map);
		assertEquals(1, cnt);
		System.out.println("===================================================================");		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("coco_cd", "RK000011");
		CommonCodeDto dto = codeService.selectOneRank(map2);
		System.out.println(dto);
		assertNotNull(dto);
		System.out.println("===================================================================");
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("coco_cd", "RK000011");
		map3.put("coco_name", "수정테스트");
		int cnt2 = codeService.correctionRank(map3);	
		assertEquals(1, cnt2);
		System.out.println("===================================================================");
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("coco_cd", "RK000011");
		CommonCodeDto dto2 = codeService.selectOneRank(map4);
		System.out.println(dto2);
		System.out.println("===================================================================");
		int cnt3 = codeService.deleteRankOne(map4);
		assertEquals(1, cnt3);
		System.out.println("===================================================================");
		List<CommonCodeDto> list2 = codeService.selectAllRank();
		System.out.println(list2);
		
	}
	
	//@Test
	public void testPostion() {
		System.out.println("===================================================================");
		List<CommonCodeDto> list = codeService.selectAllPosition();
		System.out.println(list);
		assertNotNull(list);
		System.out.println("===================================================================");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coco_cd", "PN000005");
		map.put("coco_name", "파트장");
		map.put("coco_create_id", "SYSTEM");
		int cnt = codeService.insertPositionOne(map);
		assertEquals(1, cnt);
		System.out.println("===================================================================");		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("coco_cd", "PN000005");
		CommonCodeDto dto = codeService.selectOnePosition(map2);
		System.out.println(dto);
		assertNotNull(dto);
		System.out.println("===================================================================");
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("coco_cd", "PN000005");
		map3.put("coco_name", "수정테스트");
		int cnt2 = codeService.correctionPosition(map3);	
		assertEquals(1, cnt2);
		System.out.println("===================================================================");
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("coco_cd", "PN000005");
		CommonCodeDto dto2 = codeService.selectOnePosition(map4);
		System.out.println(dto2);
		System.out.println("===================================================================");
		int cnt3 = codeService.deletePositionOne(map4);
		assertEquals(1, cnt3);
		System.out.println("===================================================================");
		List<CommonCodeDto> list2 = codeService.selectAllPosition();
		System.out.println(list2);
		
	}
	
	//@Test
	public void testSign() {	
		
		List<EmpSignDto> list = empSignService.selectAllSign();
		System.out.println(list);
		assertNotNull(list);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empl_id", "20230102");
		map.put("emsi_title", "서명추가용");
		map.put("emsi_sign_img", "www.naver.com2");
		map.put("emsi_create_id", "20230102");
		int cnt = empSignService.insertSign(map);
		assertEquals(1, cnt);
		
		List<EmpSignDto> list2 = empSignService.selectAllSign();
		System.out.println(list2);
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("emsi_seq", "2");
		int cnt2 = empSignService.deleteSign(map2);
		assertEquals(1, cnt2);
		
		List<EmpSignDto> list3 = empSignService.selectAllSign();
		System.out.println(list3);
		
	}
	
	@Test
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
		
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("comp_id", "ITCOM0A1");
		CompanyDto dto = companyService.showCompanyInfo(map3);
		System.out.println(dto);
		assertNotNull(dto);
		
		Map<String, Object>  map2 = new HashMap<String, Object>();
		map2.put("comp_name", "HCMC수정");
		map2.put("comp_num", "111-11-11111");
		map2.put("comp_ceo_name", "김수정");
		map2.put("comp_tel", "01-222-3333");
		map2.put("comp_fax", "01-777-1111");
		map2.put("comp_post", "99999");
		map2.put("comp_addr1", "수정후");
		map2.put("comp_addr2", "주소");
		map2.put("comp_seal", "도장파일명");
		map2.put("comp_modify_id", "SYSTEM");
		map2.put("comp_id", "ITCOM0A1");
		
		int cnt2 = companyService.correctionCompanyInfo(map2);
		assertEquals(1, cnt2);
		
		CompanyDto dto2 = companyService.showCompanyInfo(map3);
		System.out.println(dto2);
		
	}

}
