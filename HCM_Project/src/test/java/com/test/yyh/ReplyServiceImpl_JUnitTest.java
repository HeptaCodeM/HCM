package com.test.yyh;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcm.grw.dto.sm.ReplyDto;
import com.hcm.grw.model.mapper.sm.IReplyDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*.xml")
public class ReplyServiceImpl_JUnitTest {

	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	
	@Autowired
	private IReplyDao dao ;
	@Test
	public void test() {
		
		
		List<ReplyDto> lists = dao.getAllReply("1");
		System.out.println(lists);
		assertNotNull(lists);
		
		ReplyDto dto = new ReplyDto();
//		dto.setGobo_no("1");
//		System.out.println(dto);
//		dto.setRebo_content("댓글11111111");
//		dto.setRebo_writer("서종우");
//		dto.setRebo_writer_id("whddn1");
//		dto.setRebo_modify_id("ddd");
//		dto.setGobo_no("1");
//		dto.setRebo_content("댓글11111111");
//		dto.setRebo_writer("우우우");
//		dto.setRebo_writer_id("whddn1");
//		dto.setRebo_modify_id("ddd");
//		dto.setRebo_step("1");
//		 int n  = dao.insertReplyTwo(dto);
//		 assertNotNull(n);
//		 dto.setRebo_content("댓글11111111");
//		 dto.setRebo_modify_id("21asda1");
//		 dto.setRebo_no("7");
//		int m = dao.updateReply(dto);
		int m = dao.updateReplyDelFlag("0");
		assertNotNull(m);
		
	}

}
