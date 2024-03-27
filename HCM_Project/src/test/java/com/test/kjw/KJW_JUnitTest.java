package com.test.kjw;



import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*.xml")
public class KJW_JUnitTest {

//	@Autowired
//	private SqlSessionTemplate sessionTemplate;
//
//	@Autowired
//	private EmpSignService empSignService;
//	
//	
//	@Before
//	public void test() {
//		assertNotNull(sessionTemplate);
//	}
//	
//	
//	
//	//@Test
//	public void testSign() {	
//		
//		Map<String, Object> map4 = new HashMap<String, Object>();
//		map4.put("empl_id", "20230102");
//		List<EmpSignDto> list = empSignService.selectAllSign(map4);
//		System.out.println(list);
//		assertNotNull(list);
//		
//	}

}
