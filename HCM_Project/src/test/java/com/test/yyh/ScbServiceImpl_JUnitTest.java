package com.test.yyh;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcm.grw.dto.sm.ScbDto;
import com.hcm.grw.model.mapper.sm.IScbDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*.xml")
public class ScbServiceImpl_JUnitTest {

	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	@Autowired
	private IScbDao dao;
	
	@Test
	public void test() throws ParseException {
		
		String start = "2024-03-05";
		String end = "2024-03-06";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date str = sdf.parse(start);
		Date en = sdf.parse(end);
		
		ScbDto dto = new ScbDto();
		
//		dto.setScbo_cgory_no("100");
//		dto.setScbo_empno("22233");
//		dto.setScbo_writer("우종서");
//		dto.setScbo_title("tttttt");
//		dto.setScbo_content("ㅇㅇㅇㅇㅇㅇ");
//		dto.setScbo_modify_id("222");
//		
//		dto.setScbo_start(str);
//		dto.setScbo_end(en);
//		dto.setScbo_bigo("");
		
//		dto.setScbo_cgory_no("200");
//		dto.setScbo_title("변경타이틀");
//		dto.setScbo_content("변경 내용");
//		dto.setScbo_start(str);
//		dto.setScbo_end(en);
//		dto.setScbo_modify_id("111111");
//		dto.setScbo_no("1");
//		dto.setScbo_bigo("")
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		dto.setType(list);
		dto.setScbo_empno("20220101");
		dto.setDaygridmonth("2024-03");
		
		List<ScbDto> lists  = dao.getAllCalendar(dto);
		System.out.println(lists);
		
		assertNotNull(lists);
		for(ScbDto vo : lists) {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+vo.getScbo_title());
		}
	}

}
