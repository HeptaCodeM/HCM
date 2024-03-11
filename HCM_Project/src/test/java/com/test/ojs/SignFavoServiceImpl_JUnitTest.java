package com.test.ojs;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcm.grw.dto.doc.SignFavoDto;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.model.service.doc.ISignFavoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*.xml")
public class SignFavoServiceImpl_JUnitTest {

	@Autowired
	private SqlSessionTemplate sessionTemplate;
	@Autowired
	private ISignFavoService service;
	
	@Test
	public void test() {
		assertNotNull(sessionTemplate);
	}
	
	@Test
	public void signFavoServiceImplTest() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		SignFavoDto dto1 = new SignFavoDto(null , "20230108", "", "20220101", "", "1", new EmployeeDto());
		map.put("empl_id", dto1.getEmpl_id());
		map.put("siaf_appr_id", dto1.getSiaf_appr_id());
		SignFavoDto dto2 = new SignFavoDto(null , "20230108", "업무보고용", "", "20220101, 20230104, 20230108", "2", new EmployeeDto());
		int n1 = service.insertFavAppr(map);
		int n2 = service.insertFavApprLine(dto2);
		
		assertEquals(n1 + n2, 2);
		
		SignFavoDto dto3 = service.getFavAppr(dto1.getSiaf_favo_cd());
		SignFavoDto dto4 = service.getFavApprLine(dto2.getSiaf_favo_cd());
		
		assertNotNull(dto3);
		assertNotNull(dto4);
		System.out.println(dto3);
		System.out.println(dto4);
	
		dto3.setSiaf_appr_line("20220101, 20230104, 20230107");
		int n3 = service.updateFav(dto4);
		int n4 = service.deleteFav(dto4.getSiaf_favo_cd());
		
		assertEquals(n3 + n4, 2);
		
		SignFavoDto dto5 = new SignFavoDto("", "20230108", "업무보고용", "", "20220101, 20230104, 20230108", "2", new EmployeeDto());
		service.insertFavApprLine(dto5);
		
		List<SignFavoDto> list1 = service.getFavApprList(dto1.getEmpl_id());
		List<SignFavoDto> list2 = service.getFavApprLineList(dto5.getEmpl_id());
		
		assertNotEquals(0, list1.size());
		assertNotEquals(0, list2.size());
		
	}

}
