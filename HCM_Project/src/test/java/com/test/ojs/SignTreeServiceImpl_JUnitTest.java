package com.test.ojs;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcm.grw.dto.doc.SignTreeDto;
import com.hcm.grw.model.service.doc.ISignTreeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*.xml")
public class SignTreeServiceImpl_JUnitTest {

	@Autowired
	private SqlSessionTemplate sessionTemplate;
	@Autowired
	private ISignTreeService service;
	
	@Test
	public void test() {
		assertNotNull(sessionTemplate);
	}
	
	@Test
	public void signTreeServiceImplTest() {
		List<SignTreeDto> treeList = service.getSignTree();
		assertNotEquals(0, treeList);
		for (SignTreeDto signTreeDto : treeList) {
			System.out.println(signTreeDto);
		}
		SignTreeDto dto1 = new SignTreeDto("20230133", "DT000004", "전민균", "glyphicon glyphicon-user", "인턴", "0");
		SignTreeDto dto2 = new SignTreeDto("20230133", "DT000002", "전민균", null, "사원", "1");
		int n1 = service.insertTree(dto1);
		int n2 = service.updateTree(dto2);
		
		assertEquals(n1 + n2, 2);
	}

}
