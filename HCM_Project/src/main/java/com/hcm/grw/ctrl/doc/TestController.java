package com.hcm.grw.ctrl.doc;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hcm.grw.comm.FileCommonService;
import com.hcm.grw.dto.doc.SignFileDto;
import com.hcm.grw.model.service.doc.ISignBoxService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("doc/")
public class TestController {
	
	@Autowired
	private ISignBoxService service;

	@PostMapping(value = "fileUpTest.do", produces = "text/html; charset=UTF-8")
	public ResponseEntity<?> fileUpTest(MultipartFile file, @RequestParam String sidb_doc_num) {
		log.info("파일업로드 테스트중...");	
		if(file != null) {
			log.info("전달받은 파일\n{}", file.getOriginalFilename());
			
			SignFileDto dto = new SignFileDto();
			dto.setSidb_doc_num(sidb_doc_num);
			dto.setSidf_file_origin(file.getOriginalFilename());
			dto.setSidf_file_stored(file.getOriginalFilename());
			dto.setSidf_file_size(String.valueOf(file.getSize()));
			try {
				dto.setSidf_file_content(FileCommonService.fileUpload(file));
				if(dto.getSidf_file_content() != null) {
					service.insertDocFile(dto);
				} else {
					return ResponseEntity.badRequest().body("전달받은 파일이 없습니다");
				}
			} catch (IOException e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 처리 중 오류 발생");
			}
			
		} else {
			log.warn("전달받은 파일이 없음");
	        return ResponseEntity.badRequest().body("전달받은 파일이 없습니다");
		}
		
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("getFileList.do")
	public ResponseEntity<?> getFileList() {
		List<SignFileDto> list = service.getFile();
//		for (SignFileDto dto : list) {
//			byte[] b = dto.getSidf_file_content();
//			b = Base64Utils.encode(b);
//			dto.setSidf_file_content(b);
//		}
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("fileDown.do")
	public void fileDown(@RequestParam String sidf_file_num) throws IOException, SerialException, SQLException {
		SignFileDto dto = service.getDetailFile(sidf_file_num);
		
		FileCommonService.fileDownload(dto.getSidf_file_origin(), dto.getSidf_file_content());	
	}
	
	@GetMapping("getFileView.do")
	public ResponseEntity<?> getFileView(@RequestParam String sidf_file_num) {
		SignFileDto dto = service.getDetailFile(sidf_file_num);
		return ResponseEntity.ok(Base64Utils.encodeToString(dto.getSidf_file_content()));
	}
	
}
