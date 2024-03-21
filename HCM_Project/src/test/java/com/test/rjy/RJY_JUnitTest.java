package com.test.rjy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
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

import com.hcm.grw.dto.doc.SignBoxDto;
import com.hcm.grw.dto.doc.SignJsonDto;
import com.hcm.grw.model.mapper.doc.IDocBoxDao;
import com.hcm.grw.model.service.doc.IApprDenyService;
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
   

   @Autowired
   private IApprDenyService apprService;

   // @Test
   public void test() {
      SqlSession session = context.getBean("sqlSessionTemplate", SqlSession.class);
      assertNotNull(session);
   }

   private String NS = "com.hcm.grw.model.mapper.doc.DocBoxDaoImpl.";
   private String NS1 = "com.hcm.grw.model.mapper.doc.ApprDenyServiceImpl.";

   // 전체문서함 조회
   // @Test
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

   // 기안중인 문서 조회
   // @Test
   public void myGian() {

      String empl_id = "20230107";
      List<SignBoxDto> dto = sessionTemplate.selectList(NS + "getMyGian", empl_id);
      System.out.println(dto);
      assertNotNull(dto);
   }

   // 진행중 문서 조회
   // @Test
   public void getIngDocs() {
      String empl_id = "20220101";
      List<SignBoxDto> dto = sessionTemplate.selectList(NS + "getIngDocs", empl_id);
      System.out.println(dto);
      assertNotNull(dto);
   }

   // 승인된 문서 조회
   // @Test
   public void getApprovedDocs() {
      String empl_id = "20230107";
      List<SignBoxDto> dto = sessionTemplate.selectList(NS + "getApprovedDocs", empl_id);
      System.out.println(dto);
      assertNotNull(dto);
   }

   // 반려된 문서 조회
   // @Test
   public void getDeniedDocs() {
      String empl_id = "20220101";
      List<SignBoxDto> dto = sessionTemplate.selectList(NS + "getDeniedDocs", empl_id);
      System.out.println(dto);
      assertNotNull(dto);
   }

   // 나에게 결재요청 들어온 문서 조회
   // @Test
   public void getMyTurnDocs() {
      String empl_id = "20230102";
      List<SignBoxDto> dto = sessionTemplate.selectList(NS + "getMyTurnDocs", empl_id);
      System.out.println(dto);
      assertNotNull(dto);
   }

   // 참조 걸린문서 조회
   // @Test
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

   // 상세조회
   // @Test
   public void getDetailDocs() {
      String sidb_doc_num = "24000003";
      List<SignBoxDto> dto = sessionTemplate.selectList(NS + "getDetailDocs", sidb_doc_num);
      System.out.println(dto);
      assertNotNull(dto);
   }

   
   //상세조회 리스트방식
   //@Test
   public void getDetailDocsList() {
      String sidb_doc_num = "24000003";
      List<SignBoxDto> dto = sessionTemplate.selectList(NS + "getDetailDocsList", sidb_doc_num);
      System.out.println(dto);
      assertNotNull(dto);
   }

   
   //  @Test 
     public void approve1() { 
     
     SignBoxDto dto1 = new SignBoxDto();
     
     SignJsonDto dto2 = new SignJsonDto();
     
     dto1.setSidb_doc_num("24000001");
     
     List<SignJsonDto> list = new ArrayList<SignJsonDto>();
     
     
     dto2.setAppr_reply("회사의 사활 어쩌구 "); 
     list.add(dto2);
     
     dto1.setSidb_doc_json(list);
     
    

     boolean result = service.approve(dto1); 
     assertTrue(result); 
     }
    

   // DTO 추가버전
   // @Test
   public void approve() {
      SignBoxDto dto1 = new SignBoxDto();

      dto1.setSidb_doc_num("24000001");

//      dto1.setAppr_reply("입력돼주겠니 제발?");

      boolean result = service.approve(dto1);
      assertTrue(result);
   }

   // @Test
   public void denyDoc() {
      String sidb_doc_num = "24000003";
      int num = sessionTemplate.update(NS + "denyDoc", sidb_doc_num);

      assertEquals(num, 1);
   }

   // @Test
   public void deny() {
      SignBoxDto dto = new SignBoxDto();

      dto.setSidb_doc_num("24000003");

   //   dto.setAppr_reply("일처리 똑바로해");

      boolean result = apprService.deny(dto);
      assertTrue(result);
   }

   // @Test
   public void finalApprove() {

      SignBoxDto dto1 = new SignBoxDto();

      dto1.setSidb_doc_num("24000001");

      boolean result = service.finalApprove(dto1);
      assertTrue(result);
   }

   
    @Test 
     public void getDocs() { 
     
     SignBoxDto dto = new SignBoxDto();
    
     
     dto.setEmpl_id("20220101");
    
     List<SignBoxDto> table=dao.getAllDocsTable(dto);
     List<SignBoxDto> json=dao.getAllDocsJson(dto);
       
//     System.out.println("그럼 얘는 어떻게 찍힘???@@@"+ table );
//       System.out.println("나는테이블"+table.get(0).getSidb_doc_num());
//       System.out.println("나는json@@@@@"+json.get(1).getSidb_doc_num());
//     boolean result = service.getDocs(dto); 

     //합치기
     List<SignBoxDto> fusion = new ArrayList<>();
     
       for(int i=0; i<table.size(); i++) {
          String apprName = "";
          String apprDepth= "";
          String apprFlag= "";
          for(int j=0; j<json.size(); j++) {
             if(table.get(i).getSidb_doc_num().equals(json.get(j).getSidb_doc_num())) {
                System.out.println("같은 문서번호" + j);
                apprName += json.get(j).getAppr_name() + ",";
                apprFlag += json.get(j).getAppr_flag() + ",";
                apprDepth += json.get(j).getAppr_depth() + ",";
             }
          }
          table.get(i).setAppr_name(apprName);
          table.get(i).setAppr_depth(apprDepth);
          table.get(i).setAppr_flag(apprFlag);
          fusion.add(table.get(i));
       }
       
       for(SignBoxDto a : fusion) {
          System.out.println("[완성된 DTO] 문서번호 : " + a.getSidb_doc_num() + " 결재자 : " + a.getAppr_name()
          					+ " 승인여부 : " + a.getAppr_flag() + " 결재순서 : " + a.getAppr_depth());
       }

       
       for (int i=0; i<table.size(); i++) {
	       table.get(i).setAppr_name0(fusion.get(i).getAppr_name().split(",")[0].trim());
	       table.get(i).setAppr_depth0(fusion.get(i).getAppr_depth().split(",")[0].trim());
	       table.get(i).setAppr_flag0(fusion.get(i).getAppr_flag().split(",")[0].trim());
	       if (fusion.get(i).getAppr_name().split(",").length >= 2) {
	    	   table.get(i).setAppr_name1(fusion.get(i).getAppr_name().split(",")[1].trim());
	    	   table.get(i).setAppr_depth1(fusion.get(i).getAppr_depth().split(",")[1].trim());
		       table.get(i).setAppr_flag1(fusion.get(i).getAppr_flag().split(",")[1].trim());
	       }
	       if (fusion.get(i).getAppr_name().split(",").length >= 3) {
	    	   table.get(i).setAppr_name2(fusion.get(i).getAppr_name().split(",")[2].trim());
	    	   table.get(i).setAppr_depth2(fusion.get(i).getAppr_depth().split(",")[2].trim());
		       table.get(i).setAppr_flag2(fusion.get(i).getAppr_flag().split(",")[2].trim());
	       }
	       
       }
//       
       System.out.println("진짜최종"+table);
       
//     SignBoxDto table1 = table.get(0);
//     SignBoxDto json1 = json.get(0);
//     SignBoxDto json2 = json.get(1);
     
//      SignBoxDto fusionResult = new SignBoxDto();
//      fusionResult.setSidb_doc_num(table1.getSidb_doc_num());
//      fusionResult.setSidb_doc_title(table1.getSidb_doc_title());
//      fusionResult.setSidb_doc_writedt(table1.getSidb_doc_writedt());
//      fusionResult.setEmpl_name(table1.getEmpl_name());
//      fusionResult.setAppr_name(json1.getAppr_name());
//      fusionResult.setAppr_name1(json2.getAppr_name());
//       fusion.add(fusionResult);
//       System.out.println("제발!!!!!!!!!성공!!!!!!!!!" + fusion.get(0));
     
//       int len = table.size();
//       int jlen = json.size();
       
//       System.out.println("table 사이즈"+len + "@@@@ json 사이즈"+ jlen);
      
       
//       for (int i = 0; i < len; i++) {
//           for (int j = 0; j < jlen; j++) {
//               if (table.get(i).getSidb_doc_num() == json.get(j).getSidb_doc_num()) {
//                   try {
//                       // 메소드 이름을 동적으로 생성하여 호출
//                       String methodName = "setAppr_name" + j;
//                       
//                       //setAppr_name0,1,2 메소드 가져오기 
//                       Method method = SignBoxDto.class.getMethod(methodName, String.class);
//                       
//                       //table(0)에 json(0)(1)(2) appr_name 담기
//                       method.invoke(table.get(i), json.get(j).getAppr_name());
//                   } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//                       e.printStackTrace(); // 예외 처리
//                   }
//               }
//           }
//       }
       
//       System.out.println("진짜최종"+table);
    // assertTrue(result); 
//     assertNotNull(table);
     }

}