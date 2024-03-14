package com.hcm.grw.comm;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileCommonService {
	
	public static byte[] fileUpload(MultipartFile file) throws IOException {
		if(file == null) {
			log.warn("파일 없음");
			Function.alertLocation("등록된 파일이 없습니다", null, null, "확인", null);
			return null;
		}
		log.info("FileCommonService 파일 업로드 실행 : {}", file.getOriginalFilename());
		final String[] extensionList = {".txt", ".pdf", ".hwp", ".docx", ".xslx", ".pptx", ".zip", ".jpg", ".jpeg", ".png"};
		boolean validExtension = false;
		for(String extension : extensionList) {
	        if(file.getOriginalFilename().toLowerCase().endsWith(extension)) {
	            validExtension = true;
	            break;
	        }
	    }
		log.info(validExtension ? "TRUE" : "FALSE");
		if(!validExtension) {
			log.warn("업로드 불가한 파일 유형");
			Function.alertLocation("업로드가 불가능한 파일입니다", null, null, "확인", null);
			return null;
		}
		if(file.getSize() >= 10*1024*1024) {
			log.warn("파일 사이즈 초과");
			Function.alertLocation("10Mb 미만의 파일만 등록 가능합니다", null, null, "확인", null);
			return null;
		}
		return file.getBytes();
		
	}
	
	public static void fileDownload(HttpServletResponse response, String fileName, byte[] fileContent) {
		log.info("FileCommonService 파일 다운로드 실행 : {}", fileName);
		String name = "";
		try {
			name = new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO8859_1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			name = fileName;
		}
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
		
		try(OutputStream out = response.getOutputStream()) {
			out.write(fileContent);
			out.flush();
		} catch (IOException e) {
			log.warn("파일 다운로드 오류");
			e.printStackTrace();
		}
		
	}

}
