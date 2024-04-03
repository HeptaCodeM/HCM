package com.hcm.grw.ctrl.doc;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hcm.grw.comm.FileCommonService;
import com.hcm.grw.dto.doc.SignBoxDto;
import com.hcm.grw.dto.doc.SignFileDto;
import com.hcm.grw.dto.doc.SignTempBoxDto;
import com.hcm.grw.dto.doc.TempTreeDto;
import com.hcm.grw.dto.doc.TemplateDto;
import com.hcm.grw.model.service.doc.ISignBoxService;
import com.hcm.grw.model.service.doc.ITempTreeService;
import com.hcm.grw.model.service.doc.ITemplateService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/doc/")
public class TempTreeController {

	@Autowired
	private ITempTreeService service;
	@Autowired
	private ITemplateService tService;
	@Autowired
	private ISignBoxService bService;
	
	@GetMapping("getTempTree.do")
	public ResponseEntity<?> tempTree(){
		log.info("TempTreeController getTempTree.do GET 템플릿트리 비동기 요청");
		List<TempTreeDto> lists = service.getTempTree();
		Gson gson = new GsonBuilder().create();
		
		return ResponseEntity.ok(gson.toJson(lists));
	}
	
	@GetMapping(value = "getDoc.do", produces = "text/html; charset=UTF-8")
	public ResponseEntity<?> getDoc(@RequestParam String sidt_temp_cd) {
		log.info("TempTreeController getDoc.do GET 템플릿 불러오기 전달값 : {}",sidt_temp_cd );
		TemplateDto dto = tService.getDetailTemp(sidt_temp_cd);
		return ResponseEntity.ok(dto.getSidt_temp_content());
	}
	
	
	@PostMapping(value = "/insertDoc.do", produces = "text/html; charset=UTF-8")
	public ResponseEntity<?> insertDoc(@RequestPart(value = "file", required = false) MultipartFile file, 
									   @RequestPart("dto") SignBoxDto dto) throws IOException {
		log.info("TempTreeController insertTempDoc.do POST 기안문 작성");
		log.info("{}\n {}", file, dto);
		
		if(dto.getSidt_temp_cd().equalsIgnoreCase("TC000001") || dto.getSidt_temp_cd().equalsIgnoreCase("TC000002")
				|| dto.getSidt_temp_cd().equalsIgnoreCase("TC000006")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("empl_id", dto.getEmpl_id());
			map.put("sidb_doc_be", dto.getSidb_doc_be());
			map.put("sidb_doc_end", dto.getSidb_doc_end());
			String cnt = bService.duplicateDate(map);
			if (!cnt.equalsIgnoreCase("0")) {
				return ResponseEntity.ok("중복된 날짜");
			}
		}
		
		if(file != null) {
			SignFileDto fileDto = new SignFileDto();
			fileDto.setSidf_file_origin(file.getOriginalFilename());
			fileDto.setSidf_file_size(String.valueOf(file.getSize()));
			fileDto.setSidf_file_content(FileCommonService.fileUpload(file));
			fileDto.setSidf_file_stored(UUID.randomUUID().toString());
			bService.insertTransaction(dto, fileDto);
		} else {
			bService.insertDoc(dto);
		}
		return ResponseEntity.ok("성공");
	}
	
	@PostMapping(value = "/insertTempDoc.do", produces = "text/html; charset=UTF-8")
	public ResponseEntity<?> insertTempDoc(@RequestPart("dto") SignTempBoxDto dto){
		log.info("TempTreeController insertTempDoc.do POST 기안문 임시저장");
		log.info("\n\n{}\n\n", dto.getSitb_doc_json());
		bService.insertTempDoc(dto);
		return ResponseEntity.ok("성공");
	}
	
	@PostMapping(value = "/tempLoadInsertDoc.do", produces = "text/html; charset=UTF-8")
	public ResponseEntity<?> tempLoadInsertDoc(@RequestPart(value = "file", required = false) MultipartFile file, 
									   @RequestPart("dto") SignBoxDto dto, 
									   @RequestPart("sitb_doc_num") Map<String, String> sitb_doc_num) throws IOException {
		log.info("TempTreeController tempLoadInsertDoc.do POST 임시보관함 불러와서 기안문 작성");
		log.info("{}\n {}\n {}", file, dto, sitb_doc_num);
		
		if(dto.getSidt_temp_cd().equalsIgnoreCase("TC000001") || dto.getSidt_temp_cd().equalsIgnoreCase("TC000002")
				|| dto.getSidt_temp_cd().equalsIgnoreCase("TC000006")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("empl_id", dto.getEmpl_id());
			map.put("sidb_doc_be", dto.getSidb_doc_be());
			map.put("sidb_doc_end", dto.getSidb_doc_end());
			String cnt = bService.duplicateDate(map);
			if (!cnt.equalsIgnoreCase("0")) {
				return ResponseEntity.ok("중복된 날짜");
			}
		}
		
		if(file != null) {
			SignFileDto fileDto = new SignFileDto();
			fileDto.setSidf_file_origin(file.getOriginalFilename());
			fileDto.setSidf_file_size(String.valueOf(file.getSize()));
			fileDto.setSidf_file_content(FileCommonService.fileUpload(file));
			fileDto.setSidf_file_stored(UUID.randomUUID().toString());
			int n = bService.insertTempTransaction(dto, fileDto, sitb_doc_num.get("sitb_doc_num"));
			if(n != 1) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("트랜잭션 오류");
			}
		} else {
			int n = bService.insertTempNoFileTransaction(dto, sitb_doc_num.get("sitb_doc_num"));
			if(n != 1) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("트랜잭션 오류");
			}
		}
		return ResponseEntity.ok("성공");
	}
		
	
	
	
}
