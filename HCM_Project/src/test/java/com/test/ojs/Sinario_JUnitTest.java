package com.test.ojs;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcm.grw.model.service.doc.IDocBoxService;
import com.hcm.grw.model.service.doc.ISignBoxService;
import com.hcm.grw.model.service.doc.ISignFavoService;
import com.hcm.grw.model.service.doc.ISignTreeService;
import com.hcm.grw.model.service.doc.ITemplateService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*.xml")
public class Sinario_JUnitTest {
	
	@Autowired
	private ITemplateService templateService;
	@Autowired
	private ISignTreeService treeService;
	@Autowired
	private ISignBoxService boxService;
	@Autowired
	private ISignFavoService favoService;
	@Autowired
	private IDocBoxService docService;
	

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
