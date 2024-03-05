package com.test.rjy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcm.grw.dto.doc.DocBoxDto;
import com.hcm.grw.dto.doc.SignBoxDto;
import com.hcm.grw.model.mapper.doc.IDocBoxDao;
import com.hcm.grw.model.service.doc.IDocBoxService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*.xml")
public class RJY_JUnitTest {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private SqlSessionTemplate sessionTemplate;
	@Autowired
	private IDocBoxDao dao;
	@Autowired
	private IDocBoxService service;

	// @Test
	public void test() {
		SqlSession session = context.getBean("sqlSessionTemplate", SqlSession.class);
		assertNotNull(session);
	}

	private String NS = "com.hcm.grw.model.mapper.doc.DocBoxDaoImpl.";

	// 전체문서함 조회
	 //@Test
	public void allDocs() {

		String empl_id = "20230107";
		String empl_dept_cd = "DT000004";
		Map<String, String> inMap = new HashMap<>();
		inMap.put("empl_id", empl_id);
		inMap.put("empl_dept_cd", empl_dept_cd);

		List<SignBoxDto> dto = sessionTemplate.selectList(NS + "getAllDocs", inMap);
		System.out.println(dto);
		assertNotNull(dto);
	}

	 //기안중인 문서 조회
	// @Test
	public void myGian() {

		String empl_id = "20230107";
		List<SignBoxDto> dto = sessionTemplate.selectList(NS + "getMyGian", empl_id);
		System.out.println(dto);
		assertNotNull(dto);
	}
	
	//진행중 문서 조회
	//@Test
	public void getIngDocs() {
		String empl_id = "20220101";
		List<SignBoxDto> dto = sessionTemplate.selectList(NS + "getIngDocs", empl_id);
		System.out.println(dto);
		assertNotNull(dto);
	}

	//승인된 문서 조회
	//@Test
	public void getApprovedDocs() {
		String empl_id = "20230107";
		List<SignBoxDto> dto = sessionTemplate.selectList(NS + "getApprovedDocs", empl_id);
		System.out.println(dto);
		assertNotNull(dto);
	}
	
	//반려된 문서 조회
	//@Test
	public void getDeniedDocs() {
		String empl_id = "20220101";
		List<SignBoxDto> dto = sessionTemplate.selectList(NS + "getDeniedDocs", empl_id);
		System.out.println(dto);
		assertNotNull(dto);
	}
	
	//나에게 결재요청 들어온 문서 조회
	//@Test
	public void getMyTurnDocs() {
		String empl_id = "20230102";
		List<SignBoxDto> dto = sessionTemplate.selectList(NS + "getMyTurnDocs", empl_id);
		System.out.println(dto);
		assertNotNull(dto);
	}
	
	//참조 걸린문서 조회
	//@Test
	public void getChamjoDocs() {
		String empl_id = "20230105";
		String empl_dept_cd = "DT000004";
		Map<String, String> inMap = new HashMap<>();
		inMap.put("empl_id", empl_id);
		inMap.put("empl_dept_cd", empl_dept_cd);

		List<SignBoxDto> dto = sessionTemplate.selectList(NS + "getChamjoDocs", inMap);
		System.out.println(dto);
		assertNotNull(dto);
	}
	
	//상세조회
	//@Test
	public void getDetailDocs() {
		String sidb_doc_num = "24000003";
		List<SignBoxDto> dto = sessionTemplate.selectList(NS + "getDetailDocs", sidb_doc_num);
		System.out.println(dto);
		assertNotNull(dto);
	}
	
	//상세조회 리스트방식
	@Test
	public void getDetailDocsList() {
		String sidb_doc_num = "24000003";
		List<DocBoxDto> dto = sessionTemplate.selectList(NS + "getDetailDocsList", sidb_doc_num);
		System.out.println(dto);
		assertNotNull(dto);
	}
	
	
	// @Test
	    public void approve() {
		 DocBoxDto dto = new DocBoxDto();
		 SignBoxDto dto1 = new SignBoxDto();
		 
		 dto1.setSidb_doc_num("24000001");
		 boolean result = service.approve(dto1);		
		 assertTrue(result);
	 }
	 
	//@Test
		public void denyDoc() {
			String sidb_doc_num = "24000003";
			int num = sessionTemplate.update(NS + "denyDoc", sidb_doc_num);
			
			assertEquals(num, 1);
		}
		
	//	@Test
	    public void finalApprove() {
		 
		 SignBoxDto dto1 = new SignBoxDto();
		 
		 dto1.setSidb_doc_num("24000001");
		 
		 boolean result = service.finalApprove(dto1);		
		 assertTrue(result);
	 }	
		
	     
}
