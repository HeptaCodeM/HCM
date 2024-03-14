package com.test.ojs;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcm.grw.dto.doc.SignBoxDto;
import com.hcm.grw.dto.doc.SignFileDto;
import com.hcm.grw.dto.doc.SignJsonDto;
import com.hcm.grw.dto.doc.SignTempBoxDto;
import com.hcm.grw.model.service.doc.ISignBoxService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*.xml")
public class SignBoxServiceImpl_JUnitTest {

	@Autowired
	private SqlSessionTemplate sessionTemplate;
	@Autowired
	private ISignBoxService service;
	
	@Test
	public void test() {
		assertNotNull(sessionTemplate);
	}
	
	@Test
	public void signBoxServiceImplTest() {
		
		SignBoxDto dto = new SignBoxDto();
		dto.setEmpl_id("20230107");
		dto.setSidb_doc_title("[오지수]사직 신청의 건");
		dto.setSidb_doc_content("템플릿입니다");
		dto.setSica_cd("CC000001");
		dto.setSidb_doc_expiredt("2024-03-10");
		dto.setSidt_temp_cd("TC000004");
		dto.setSidb_doc_be("2024-03-12");
		dto.setSidb_doc_end("2024-03-12");
		
		List<SignJsonDto> list = new ArrayList<SignJsonDto>();
		
		SignJsonDto jDto1 = new SignJsonDto();
		jDto1.setAppr_id("20220101");
		jDto1.setAppr_depth("1");
		list.add(jDto1);
		
		SignJsonDto jDto2 = new SignJsonDto();
		jDto2.setAppr_id("20230105");
		jDto2.setAppr_depth("2");
		list.add(jDto2);
		
		SignJsonDto jDto3 = new SignJsonDto();
		jDto3.setAppr_id("20230109");
		jDto3.setAppr_depth("3");
		list.add(jDto3);
		
		dto.setSidb_doc_json(list);
		
		int n1 = service.insertDoc(dto);
		assertEquals(1, n1);
		
		SignTempBoxDto tDto = new SignTempBoxDto();
		tDto.setEmpl_id("20230107");
		tDto.setSitb_doc_title("[오지수]사직 신청의 건");
		tDto.setSitb_doc_content("템플릿입니다");
		tDto.setSica_cd("CC000001");
		tDto.setSitb_doc_expiredt("2024-03-10");
		tDto.setSidt_temp_cd("TC000004");
		tDto.setSitb_doc_be("2024-03-12");
		tDto.setSitb_doc_end("2024-03-12");
		
		List<SignJsonDto> list2 = new ArrayList<SignJsonDto>();
		
		SignJsonDto jDto4 = new SignJsonDto();
		jDto4.setAppr_id("20220101");
		jDto4.setAppr_depth("1");
		list2.add(jDto4);
		
		SignJsonDto jDto5 = new SignJsonDto();
		jDto5.setAppr_id("20230105");
		jDto5.setAppr_depth("2");
		list2.add(jDto5);
		
		SignJsonDto jDto6 = new SignJsonDto();
		jDto6.setAppr_id("20230109");
		jDto6.setAppr_depth("3");
		list2.add(jDto6);
		tDto.setSitb_doc_json(list2);
		int n2 = service.insertTempDoc(tDto);
		assertEquals(1, n2);
		
		SignFileDto fDto = new SignFileDto("", "24000002", "파아일.png", "DJIFOIQOJKOQWNKN", "12385313", null, "");
		int n3 = service.insertDocFile(fDto);
		assertEquals(1, n3);
		
		dto.setEmpl_ref("20230106, 20230109");
		dto.setEmpl_dept_cd("DT000003");
		dto.setSidb_doc_alflag("Y");
		dto.setSidb_doc_num("24000009");
		int n4 = service.updateDoc(dto);
		assertEquals(1, n4);
		int n = service.insertTransaction(dto, fDto);
		assertEquals(n, 1);
		
		SignTempBoxDto dto2 = service.getTempDoc("24000005");
		assertNotNull(dto2);
		
		int n5 = service.deleteDoc("24000001");
		assertEquals(1, n5);
	}

}
