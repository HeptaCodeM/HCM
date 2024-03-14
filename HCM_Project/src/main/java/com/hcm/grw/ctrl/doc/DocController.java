package com.hcm.grw.ctrl.doc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hcm.grw.dto.doc.SignBoxDto;
import com.hcm.grw.model.mapper.doc.IDocBoxDao;
import com.hcm.grw.model.service.doc.IDocBoxService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DocController {

	
	@Autowired
	private IDocBoxService docService;
	
	@Autowired
	private IDocBoxDao dao;
	
	
	@GetMapping("/doc/docBox/getDetail.do")
	public String getDetailBoard(Model model, SignBoxDto dto, String docNum ) {
		
		dto.setSidb_doc_num(docNum);
		List<SignBoxDto> docDto= docService.getDetailDocsList(dto);
		model.addAttribute("docDto",docDto);
		log.info("상세조회  데이터 리스트 결과{}", docDto);
		return "/doc/boardDetail";
	}
	
	
	@GetMapping(value="/doc/docBox.do")
	public String allDocs(Model model) {
		log.info("결재함 진입");
		  SignBoxDto dto = new SignBoxDto();
		    
		     
		     dto.setEmpl_id("20220101");
		    
		     List<SignBoxDto> table=dao.getAllDocsTable(dto);
		     List<SignBoxDto> json=dao.getAllDocsJson(dto);
		       

		     //합치기
		     List<SignBoxDto> fusion = new ArrayList<>();
		     
		       for(int i=0; i<table.size(); i++) {
		          String apprName = "";
		          String apprDepth= "";
		          String apprFlag= "";
		          for(int j=0; j<json.size(); j++) {
		             if(table.get(i).getSidb_doc_num().equals(json.get(j).getSidb_doc_num())) {
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
		       System.out.println(table);
		       model.addAttribute("lists", table);
		       return "/doc/docBox";
}
	
	@GetMapping(value="/doc/gianBox.do")
	public String gian(Model model) {
		log.info("기안함 진입");
		  SignBoxDto dto = new SignBoxDto();
		    
		     
		     dto.setEmpl_id("20220101");
		    
		     List<SignBoxDto> table=dao.getMyGian(dto);
		     List<SignBoxDto> json=dao.getAllDocsJson(dto);
		       

		     //합치기
		     List<SignBoxDto> fusion = new ArrayList<>();
		     
		       for(int i=0; i<table.size(); i++) {
		          String apprName = "";
		          String apprDepth= "";
		          String apprFlag= "";
		          for(int j=0; j<json.size(); j++) {
		             if(table.get(i).getSidb_doc_num().equals(json.get(j).getSidb_doc_num())) {
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
		       System.out.println(table);
		       model.addAttribute("lists", table);
		       return "/doc/docBox";
}
	
	

	
	
	@GetMapping(value="/doc/allDocs.do", produces = "application/json;")
	@ResponseBody
	public String createAjax(){
	    log.info("HomeController createAjax 아작스처리 시작");
	 //   List<JobsVo> lists = dao.ajax();
		  SignBoxDto dto = new SignBoxDto();

	    dto.setEmpl_id("20220101");
	    
	     List<SignBoxDto> table=dao.getAllDocsTable(dto);
	     List<SignBoxDto> json=dao.getAllDocsJson(dto);
	       

	     //합치기
	     List<SignBoxDto> fusion = new ArrayList<>();
	     
	       for(int i=0; i<table.size(); i++) {
	          String apprName = "";
	          String apprDepth= "";
	          String apprFlag= "";
	          for(int j=0; j<json.size(); j++) {
	             if(table.get(i).getSidb_doc_num().equals(json.get(j).getSidb_doc_num())) {
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
	    Gson data = new GsonBuilder().create();
	    return data.toJson(table); 
	    
	}
	
}