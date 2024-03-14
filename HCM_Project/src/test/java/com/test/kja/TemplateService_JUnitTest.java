package com.test.kja;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcm.grw.dto.doc.TempTreeDto;
import com.hcm.grw.dto.doc.TemplateDto;
import com.hcm.grw.model.service.doc.ITempTreeService;
import com.hcm.grw.model.service.doc.ITemplateService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*.xml")
public class TemplateService_JUnitTest {
	

	@Autowired
	private ITemplateService service;
	
	@Autowired
	private ITempTreeService sv;
	
	
//	@Test
	public void updateTest() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidt_temp_name", "연가신청서");
		map.put("sidt_temp_cd", "TC000001");
		map.put("sidt_temp_content", "CONTENT");
		int updateCnt = service.updateTemp(map);
		
		assertEquals(1, updateCnt);			
	}
	
//	@Test
	public void deleteTest() {
		String sidt_temp_cd = "TC000001";
		int deleteCnt = service.deleteTemp(sidt_temp_cd);
		assertEquals(1, deleteCnt);
	}
	
//	@Test
	public void listTest() {
		List<TemplateDto> list =  service.getTempList();
		assertNotEquals(0, list.size());
		for (TemplateDto x : list) {
			System.out.println(x);
		}
	}
	
//	@Test
	public void detailTest() {
		String sidt_temp_cd = "TC000001";
		TemplateDto detailTem = service.getDetailTemp(sidt_temp_cd);
		
		assertNotNull(detailTem);
	}
	
//	@Test
	public void insertTest() {
		TemplateDto dto = new TemplateDto();
		dto.setSidt_temp_name("시말서");
		dto.setSica_cd("CC000001");
		
		int insertCnt = service.insertTemp(dto);
		assertEquals(1, insertCnt);
	}
	
//	@Test
	public void getCategory() {
		List<TemplateDto> lists = service.getCategory();
		assertNotEquals(0, lists.size());
		
	}
	
	@Test
	public void getTempTree() {
		List<TempTreeDto> lists = sv.getTempTree();
		assertNotEquals(0, lists.size());
	}

}
