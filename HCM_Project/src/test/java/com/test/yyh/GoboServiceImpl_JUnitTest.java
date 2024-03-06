package com.test.yyh;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcm.grw.dto.sm.GoboDto;
import com.hcm.grw.model.service.sm.GoboServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*.xml")
public class GoboServiceImpl_JUnitTest {

	@Autowired
	private GoboServiceImpl service;
	
	@Test
	public void test() throws ParseException {
		List<GoboDto> lists = service.getAllGobo();
		System.out.println(lists);
		
		
		GoboDto dto = new GoboDto();
		Date date = new Date();
		SimpleDateFormat sdfm = new SimpleDateFormat("yyyy-mm-dd");
		date = sdfm.parse("2024-03-05");
		
		
//		int n = service.updateGoboView("1");
//		System.out.println(n);
		/*
		 * // dto.setGobo_title("제목"); dto.setGobo_content("내용");
		 * dto.setGobo_writer("작성자"); dto.setGobo_writer_id("123123");
		 * dto.setGobo_bigo(""); int m = service.insertGobo(dto);
		 */
		
//		dto.setGobo_title("22222");
//		dto.setGobo_content("22222");
//		dto.setGobo_modify_id("22222");
//		dto.setGobo_modify_date(new Date());
//		dto.setGobo_bigo("22222");
//		dto.setGobo_no("4");
//		
//		int m = service.updateGobo(dto);
		
		dto.setGobo_no("2");
		int n =service.updateGoboDelFlag("2");
		assertNotNull(n);
		
		
	}

}
