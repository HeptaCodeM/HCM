package com.hcm.grw.ctrl.doc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.hcm.grw.dto.doc.TemplateDto;
import com.hcm.grw.model.service.doc.ITemplateService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value = "doc")
public class TemplateController {

	@Autowired
	private ITemplateService service;

	@GetMapping("/templateAdmin.do")
	public String template(Model model) {
		log.info("TemplateController 템플릿 전체 값을 저장하여 이동하는 template");
		List<TemplateDto> lst = service.getTempList();
		model.addAttribute("lst", lst);
		return "doc/template/templateAdmin";
	}

	@GetMapping("/detailTemplateAdmin.do")
	public String detailTempate(String sidt_temp_cd, Model model) {
		log.info("TemplateController 템플릿 상세 내용 조회하는 detailTemplate");
		TemplateDto temDto = service.getDetailTemp(sidt_temp_cd);
		model.addAttribute("temDto", temDto);
		return "doc/template/detailTemplateAdmin";
	}

	@GetMapping("/writeTemplateAdmin.do")
	public String writeTemplate() {
		log.info("TemplateController 템플릿 등록화면으로 이동하는 writeTemplate");
		return "doc/template/writeTemplateAdmin";
	}

	@GetMapping("/selectCategoryAdmin.do")
	@ResponseBody
	public ResponseEntity<?> selectCategory() {
		log.info("TemplateController 템플릿 카테고리 조회하는 selectCategory");
		List<TemplateDto> list = service.getCategory();
		return ResponseEntity.ok(list);
	}

	@PostMapping("/insertTemplateAdmin.do")
	public String insertTemplate(@ModelAttribute TemplateDto dto) {
		log.info("TemplateController 템플릿 등록하는 insertTemplate");
		int n = service.insertTemp(dto);
		return (n == 0) ? "" : "redirect:templateAdmin.do";
	}
	
//  @PostMapping("/insertTemplate.do")
//  public String insertTemplate(@RequestParam Map<String, Object> map) {
//     log.info("TemplateController 템플릿 등록하는 insertTemplate");
//     TemplateDto dto = new TemplateDto();
//     dto.setSica_cd((String)map.get("sica_cd"));
//     dto.setSidt_temp_name((String)map.get("sidt_temp_name"));
//     dto.setSidt_temp_content((String)map.get("sidt_temp_content"));
//     int n = service.insertTemp(dto);
//     return (n==0)? "" : "redirect:template.do";
//  }


	@GetMapping("/modifyTemplateAdmin.do")
	public String modifyTemplate(String sidt_temp_cd, Model model) {
		log.info("TemplateController 템플릿 수정화면으로 이동하는 modifyTemplate");
		TemplateDto temDto = service.getDetailTemp(sidt_temp_cd);
		model.addAttribute("temDto", temDto);
		return "doc/template/modifyTemplateAdmin";
	}
	
	@PostMapping("updateTemplateAdmin.do")
	public String updateTemplate(@ModelAttribute TemplateDto dto, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sica_cd", dto.getSica_cd());
		map.put("sidt_temp_name", dto.getSidt_temp_name());
		map.put("sidt_temp_content", dto.getSidt_temp_content());
		map.put("sidt_temp_cd", dto.getSidt_temp_cd());
		service.updateTemp(map);
//		model.addAttribute("lst", dto);
		return "redirect:detailTemplateAdmin.do?sidt_temp_cd=" + map.get("sidt_temp_cd");
	}

//	@PostMapping(value = "/updateTemplate.do")
//	public String updateTemplate(@RequestBody Map<String, Object> map) {
//		log.info("TemplateController 템플릿 수정하는 updateTemplate");
////		Map<String, Object> paramMap = new HashMap<String, Object>();
////        paramMap.put("sica_cd", sicaCd);
////        paramMap.put("sidt_temp_name", tempName);
////        paramMap.put("sidt_temp_content", tempContent);        
//		service.updateTemp(map);
//		return "template";
////		return "redirect:/detailTemplate.do?sidt_temp_cd=" + map.get("sidt_temp_cd");
//	}

	@GetMapping("/deleteTemplateAdmin.do")
	public String deleteTemplate(@RequestParam(name = "sidt_temp_cd") String sidt_temp_cd) {
		log.info("TemplateController 템플릿 삭제하는 deleteTemplate");
		service.deleteTemp(sidt_temp_cd);
		return "redirect:templateAdmin.do";
	}

	@GetMapping(value = "/getTemplateAdmin.do", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String getTemplate(String sidt_temp_cd, Model model) {
		log.info("TemplateController 선택한 템플릿 에디터로 가져오는 getTemplate: {}", sidt_temp_cd);
		String getTemp = service.getTemplate(sidt_temp_cd);
		log.info(getTemp);
		return getTemp;

	}

	@PostMapping(value = "/uploadImageAdmin.do")
	@ResponseBody
	public Map<String, String> uploadImage(MultipartFile upload, HttpServletRequest request) {
		log.info("TemplateController 이미지 업로드 uploadImage {}", upload);

		String originFileName = upload.getOriginalFilename();
		String saveName = UUID.randomUUID().toString() + originFileName.substring(originFileName.lastIndexOf("."));

		InputStream inputStream = null;
		OutputStream outputStream = null;
		String path = "";

		try {
			inputStream = upload.getInputStream();

			path = WebUtils.getRealPath(request.getSession().getServletContext(), "/ckupload");
			System.out.println("저장경로: " + path);

			File storage = new File(path);
			if (!storage.exists()) {
				storage.mkdir();
			}
			File newFile = new File(path + "/" + saveName);
			if (!newFile.exists()) {
				newFile.createNewFile();
			}

			outputStream = new FileOutputStream(newFile);

			int read = 0;
			byte[] b = new byte[(int) upload.getSize()];
			while ((read = inputStream.read(b)) != -1) {
				outputStream.write(b, 0, read);
			}

		} catch (IOException e) {
			log.error("uploadImage read Error : \n" + e.getMessage());
		} finally {
			try {
				inputStream.close();
				outputStream.close();
			} catch (IOException e) {
				log.error("uploadImage close Error : \n" + e.getMessage());
				e.printStackTrace();
			}
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "/ckupload/" + saveName);

		return map;
	}

	@PostMapping(value = "/removeImage.do")
	@ResponseBody
	public void removeImage(String saveName, HttpServletRequest req) {
		log.info("TemplateController 이미지 삭제: {}", saveName);
		String path = "";
		try {
			path = WebUtils.getRealPath(req.getSession().getServletContext(), "/ckupload");
			File oldFile = new File(path + "/" + saveName);
			if (oldFile.exists()) {
				oldFile.delete();
			}
		} catch (FileNotFoundException e) {
			log.error("TemplateController removeImage Error : \n" + e.getMessage());
		}
	}
	
	
	
	
	

}