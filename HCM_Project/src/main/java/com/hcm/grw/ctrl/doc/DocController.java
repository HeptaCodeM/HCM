package com.hcm.grw.ctrl.doc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hcm.grw.comm.FileCommonService;
import com.hcm.grw.comm.Function;
import com.hcm.grw.dto.doc.SignBoxDto;
import com.hcm.grw.dto.doc.SignFileDto;
import com.hcm.grw.dto.hr.EmployeeDto;
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
	public String getDetailBoard(Model model, SignBoxDto dto, String docNum, HttpSession session) {

		dto.setSidb_doc_num(docNum);

		List<SignBoxDto> docDto = docService.getDetailDocsList(dto);
		model.addAttribute("docDto", docDto);
		log.info("상세조회  데이터 리스트 결과{}", docDto);
		return "/doc/docBox/boardDetail/boardDetail";
	}

	@GetMapping(value = "/doc/docBox.do")
	public String allDocs(Model model, HttpSession session) {
		log.info("결재함 진입");
		SignBoxDto dto = new SignBoxDto();
		EmployeeDto Edto = (EmployeeDto) session.getAttribute("userInfoVo");

		dto.setEmpl_id(Edto.getEmpl_id());

		model.addAttribute("Edto", Edto);

		List<SignBoxDto> table = dao.getAllDocsTable(dto);
		List<SignBoxDto> json = dao.getAllDocsJson(dto);

		// 합치기
		List<SignBoxDto> fusion = new ArrayList<>();

		for (int i = 0; i < table.size(); i++) {
			String apprName = "";
			String apprDepth = "";
			String apprFlag = "";
			for (int j = 0; j < json.size(); j++) {
				if (table.get(i).getSidb_doc_num().equals(json.get(j).getSidb_doc_num())) {
					apprName += json.get(j).getAppr_name() + ",";
					apprFlag += json.get(j).getAppr_flag() + ",";
					apprDepth += json.get(j).getAppr_depth() + ",";
				}
			}

			byte[] emplImg = table.get(i).getEmpl_picture();
			table.get(i).setEmpl_pictureStr(Base64Utils.encodeToString(emplImg));

			table.get(i).setAppr_name(apprName);
			table.get(i).setAppr_depth(apprDepth);
			table.get(i).setAppr_flag(apprFlag);
			fusion.add(table.get(i));
		}

		for (int i = 0; i < table.size(); i++) {
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
		return "/doc/docBox/docBox";
	}

	@GetMapping(value = "/doc/allDocs.do", produces = "application/json;")
	@ResponseBody
	public String allDocsAjax(HttpSession session) {
		log.info("HomeController createAjax 아작스처리 시작");
		// List<JobsVo> lists = dao.ajax();
		SignBoxDto dto = new SignBoxDto();
		EmployeeDto Edto = (EmployeeDto) session.getAttribute("userInfoVo");

		dto.setEmpl_id(Edto.getEmpl_id());
		// dto.setEmpl_id("20220101");

		List<SignBoxDto> table = dao.getAllDocsTable(dto);
		List<SignBoxDto> json = dao.getAllDocsJson(dto);

		// 합치기
		List<SignBoxDto> fusion = new ArrayList<>();

		for (int i = 0; i < table.size(); i++) {
			String apprName = "";
			String apprDepth = "";
			String apprFlag = "";
			for (int j = 0; j < json.size(); j++) {
				if (table.get(i).getSidb_doc_num().equals(json.get(j).getSidb_doc_num())) {
					apprName += json.get(j).getAppr_name() + ",";
					apprFlag += json.get(j).getAppr_flag() + ",";
					apprDepth += json.get(j).getAppr_depth() + ",";
				}
			}
			byte[] emplImg = table.get(i).getEmpl_picture();
			table.get(i).setEmpl_pictureStr(Base64Utils.encodeToString(emplImg));
			table.get(i).setAppr_name(apprName);
			table.get(i).setAppr_depth(apprDepth);
			table.get(i).setAppr_flag(apprFlag);
			fusion.add(table.get(i));
		}

		for (int i = 0; i < table.size(); i++) {
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

	@GetMapping(value = "/doc/gianBox.do", produces = "application/json;")
	@ResponseBody
	public String gianDocsAjax(HttpSession session) {
		log.info("HomeController 기안함 아작스처리 시작");
		SignBoxDto dto = new SignBoxDto();
		EmployeeDto Edto = (EmployeeDto) session.getAttribute("userInfoVo");

		dto.setEmpl_id(Edto.getEmpl_id());
		// dto.setEmpl_id("20220101");

		List<SignBoxDto> table = dao.getMyGian(dto);
		List<SignBoxDto> json = dao.getAllDocsJson(dto);

		// 합치기
		List<SignBoxDto> fusion = new ArrayList<>();

		for (int i = 0; i < table.size(); i++) {
			String apprName = "";
			String apprDepth = "";
			String apprFlag = "";
			for (int j = 0; j < json.size(); j++) {
				if (table.get(i).getSidb_doc_num().equals(json.get(j).getSidb_doc_num())) {
					apprName += json.get(j).getAppr_name() + ",";
					apprFlag += json.get(j).getAppr_flag() + ",";
					apprDepth += json.get(j).getAppr_depth() + ",";
				}
			}
			byte[] emplImg = table.get(i).getEmpl_picture();
			table.get(i).setEmpl_pictureStr(Base64Utils.encodeToString(emplImg));
			table.get(i).setAppr_name(apprName);
			table.get(i).setAppr_depth(apprDepth);
			table.get(i).setAppr_flag(apprFlag);
			fusion.add(table.get(i));
		}

		for (int i = 0; i < table.size(); i++) {
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

	@GetMapping(value = "/doc/ingBox.do", produces = "application/json;")
	@ResponseBody
	public String ingDocsAjax(HttpSession session) {
		log.info("HomeController 진행함 아작스처리 시작");
		SignBoxDto dto = new SignBoxDto();
		EmployeeDto Edto = (EmployeeDto) session.getAttribute("userInfoVo");

		dto.setEmpl_id(Edto.getEmpl_id());
		// dto.setEmpl_id("20220101");

		List<SignBoxDto> table = dao.getIngDocs(dto);
		List<SignBoxDto> json = dao.getAllDocsJson(dto);

		// 합치기
		List<SignBoxDto> fusion = new ArrayList<>();

		for (int i = 0; i < table.size(); i++) {
			String apprName = "";
			String apprDepth = "";
			String apprFlag = "";
			for (int j = 0; j < json.size(); j++) {
				if (table.get(i).getSidb_doc_num().equals(json.get(j).getSidb_doc_num())) {
					apprName += json.get(j).getAppr_name() + ",";
					apprFlag += json.get(j).getAppr_flag() + ",";
					apprDepth += json.get(j).getAppr_depth() + ",";
				}
			}
			byte[] emplImg = table.get(i).getEmpl_picture();
			table.get(i).setEmpl_pictureStr(Base64Utils.encodeToString(emplImg));
			table.get(i).setAppr_name(apprName);
			table.get(i).setAppr_depth(apprDepth);
			table.get(i).setAppr_flag(apprFlag);
			fusion.add(table.get(i));
		}

		for (int i = 0; i < table.size(); i++) {
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

	@GetMapping(value = "/doc/approveBox.do", produces = "application/json;")
	@ResponseBody
	public String apprDocsAjax(HttpSession session) {
		log.info(" 결재완료함 아작스처리 시작");
		SignBoxDto dto = new SignBoxDto();
		EmployeeDto Edto = (EmployeeDto) session.getAttribute("userInfoVo");

		dto.setEmpl_id(Edto.getEmpl_id());
		// dto.setEmpl_id("20220101");

		List<SignBoxDto> table = dao.getApprovedDocs(dto);
		List<SignBoxDto> json = dao.getAllDocsJson(dto);

		// 합치기
		List<SignBoxDto> fusion = new ArrayList<>();

		for (int i = 0; i < table.size(); i++) {
			String apprName = "";
			String apprDepth = "";
			String apprFlag = "";
			for (int j = 0; j < json.size(); j++) {
				if (table.get(i).getSidb_doc_num().equals(json.get(j).getSidb_doc_num())) {
					apprName += json.get(j).getAppr_name() + ",";
					apprFlag += json.get(j).getAppr_flag() + ",";
					apprDepth += json.get(j).getAppr_depth() + ",";
				}
			}
			byte[] emplImg = table.get(i).getEmpl_picture();
			table.get(i).setEmpl_pictureStr(Base64Utils.encodeToString(emplImg));
			table.get(i).setAppr_name(apprName);
			table.get(i).setAppr_depth(apprDepth);
			table.get(i).setAppr_flag(apprFlag);
			fusion.add(table.get(i));
		}

		for (int i = 0; i < table.size(); i++) {
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
		System.out.println("@@@@@@뭐들었니" + table);
		return data.toJson(table);

	}

	@GetMapping(value = "/doc/denyBox.do", produces = "application/json;")
	@ResponseBody
	public String denyDocsAjax(HttpSession session) {
		log.info("반려함 아작스처리 시작");
		SignBoxDto dto = new SignBoxDto();
		EmployeeDto Edto = (EmployeeDto) session.getAttribute("userInfoVo");

		dto.setEmpl_id(Edto.getEmpl_id());
		// dto.setEmpl_id("20220101");

		List<SignBoxDto> table = dao.getDeniedDocs(dto);
		List<SignBoxDto> json = dao.getAllDocsJson(dto);

		// 합치기
		List<SignBoxDto> fusion = new ArrayList<>();

		for (int i = 0; i < table.size(); i++) {
			String apprName = "";
			String apprDepth = "";
			String apprFlag = "";
			for (int j = 0; j < json.size(); j++) {
				if (table.get(i).getSidb_doc_num().equals(json.get(j).getSidb_doc_num())) {
					apprName += json.get(j).getAppr_name() + ",";
					apprFlag += json.get(j).getAppr_flag() + ",";
					apprDepth += json.get(j).getAppr_depth() + ",";
				}
			}
			byte[] emplImg = table.get(i).getEmpl_picture();
			table.get(i).setEmpl_pictureStr(Base64Utils.encodeToString(emplImg));

			table.get(i).setAppr_name(apprName);
			table.get(i).setAppr_depth(apprDepth);
			table.get(i).setAppr_flag(apprFlag);
			fusion.add(table.get(i));
		}

		for (int i = 0; i < table.size(); i++) {
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

	@GetMapping(value = "/doc/chamjoBox.do", produces = "application/json;")
	@ResponseBody
	public String chamjoBox(HttpSession session) {
		log.info("참조함 아작스처리 시작");

		EmployeeDto Edto = (EmployeeDto) session.getAttribute("userInfoVo");

		// String empl_id = "20220101";
		// String empl_dept_cd = "DT000002";
		Map<String, String> inMap = new HashMap<>();
		inMap.put("empl_id", Edto.getEmpl_id());
		inMap.put("empl_dept_cd", Edto.getEmpl_dept_cd());

		List<SignBoxDto> table = dao.getChamjoDocs(inMap);
		List<SignBoxDto> json = dao.getChamjoJson(inMap);

		SignBoxDto dto = new SignBoxDto();

		dto.setEmpl_id("20220101");

		// 합치기
		List<SignBoxDto> fusion = new ArrayList<>();

		for (int i = 0; i < table.size(); i++) {
			String apprName = "";
			String apprDepth = "";
			String apprFlag = "";
			for (int j = 0; j < json.size(); j++) {
				if (table.get(i).getSidb_doc_num().equals(json.get(j).getSidb_doc_num())) {
					apprName += json.get(j).getAppr_name() + ",";
					apprFlag += json.get(j).getAppr_flag() + ",";
					apprDepth += json.get(j).getAppr_depth() + ",";
				}
			}
			byte[] emplImg = table.get(i).getEmpl_picture();
			table.get(i).setEmpl_pictureStr(Base64Utils.encodeToString(emplImg));
			table.get(i).setAppr_name(apprName);
			table.get(i).setAppr_depth(apprDepth);
			table.get(i).setAppr_flag(apprFlag);
			fusion.add(table.get(i));
		}

		for (int i = 0; i < table.size(); i++) {
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

	@GetMapping(value = "/doc/myTurnBox.do", produces = "application/json;")
	@ResponseBody
	public String myTurnBox(HttpSession session) {
		log.info("나에게 온 결재 요청함 아작스처리 시작");
		SignBoxDto dto = new SignBoxDto();

		EmployeeDto Edto = (EmployeeDto) session.getAttribute("userInfoVo");

		dto.setEmpl_id(Edto.getEmpl_id());
		// dto.setEmpl_id("20230107"); //결재자 사원번호

		List<SignBoxDto> table = dao.getMyTurnDocs(dto);

		List<SignBoxDto> json = dao.getMyTurnJson(dto);

		// 합치기
		List<SignBoxDto> fusion = new ArrayList<>();

		for (int i = 0; i < table.size(); i++) {
			String apprName = "";
			String apprDepth = "";
			String apprFlag = "";
			for (int j = 0; j < json.size(); j++) {
				if (table.get(i).getSidb_doc_num().equals(json.get(j).getSidb_doc_num())) {
					apprName += json.get(j).getAppr_name() + ",";
					apprFlag += json.get(j).getAppr_flag() + ",";
					apprDepth += json.get(j).getAppr_depth() + ",";
				}
			}
			byte[] emplImg = table.get(i).getEmpl_picture();
			table.get(i).setEmpl_pictureStr(Base64Utils.encodeToString(emplImg));
			table.get(i).setAppr_name(apprName);
			table.get(i).setAppr_depth(apprDepth);
			table.get(i).setAppr_flag(apprFlag);
			fusion.add(table.get(i));
		}

		for (int i = 0; i < table.size(); i++) {
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

	@GetMapping(value = "/doc/iDidBox.do", produces = "application/json;")
	@ResponseBody
	public String iDidBox(HttpSession session) {
		log.info("내가 결재한 문서 조회 아작스처리 시작");
		SignBoxDto dto = new SignBoxDto();

		EmployeeDto Edto = (EmployeeDto) session.getAttribute("userInfoVo");

		dto.setEmpl_id(Edto.getEmpl_id());
		// dto.setEmpl_id("20230107"); //결재자 사원번호

		List<SignBoxDto> table = dao.getIDidDocs(dto);

		List<SignBoxDto> json = dao.getMyTurnJson(dto);

		// 합치기
		List<SignBoxDto> fusion = new ArrayList<>();

		for (int i = 0; i < table.size(); i++) {
			String apprName = "";
			String apprDepth = "";
			String apprFlag = "";
			for (int j = 0; j < json.size(); j++) {
				if (table.get(i).getSidb_doc_num().equals(json.get(j).getSidb_doc_num())) {
					apprName += json.get(j).getAppr_name() + ",";
					apprFlag += json.get(j).getAppr_flag() + ",";
					apprDepth += json.get(j).getAppr_depth() + ",";
				}
			}
			byte[] emplImg = table.get(i).getEmpl_picture();
			table.get(i).setEmpl_pictureStr(Base64Utils.encodeToString(emplImg));
			table.get(i).setAppr_name(apprName);
			table.get(i).setAppr_depth(apprDepth);
			table.get(i).setAppr_flag(apprFlag);
			fusion.add(table.get(i));
		}

		for (int i = 0; i < table.size(); i++) {
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

	//상세조회 문서에 첨부된 파일리스트 조회
	@GetMapping("/doc/docBox/getDocsFileList.do")
	public ResponseEntity<?> getFileList(String docNum, SignBoxDto dto) {
		log.info("상세조회 파일 리스트 조회 메소드 진입");
		dto.setSidb_doc_num(docNum);
		System.out.println(dto);
		List<SignFileDto> list = docService.getFileList(dto);
		return ResponseEntity.ok(list);
	}

	//상세조회 문서에 첨부된 파일 다운로드
	@GetMapping("/doc/docBox/fileDown.do")
	public void fileDown(HttpServletResponse response, @RequestParam String sidf_file_num)
			throws IOException, SerialException, SQLException {
		SignFileDto dto = docService.getDocsDetailFile(sidf_file_num);

		FileCommonService.fileDownload(response, dto.getSidf_file_origin(), dto.getSidf_file_content());
	}
	
	
	
	
	
	@GetMapping(value = "/doc/tempDocs.do")
	public String TempDocs(Model model, HttpSession session) {
		log.info("임시 결재문서 보관함 진입");
		SignBoxDto dto = new SignBoxDto();
		EmployeeDto Edto = (EmployeeDto) session.getAttribute("userInfoVo");

		dto.setEmpl_id(Edto.getEmpl_id());

		model.addAttribute("Edto", Edto);

		List<SignBoxDto> table = dao.getTempDocs(dto);
		
		for (int i = 0; i < table.size(); i++) {
		byte[] emplImg = table.get(i).getEmpl_picture();
		table.get(i).setEmpl_pictureStr(Base64Utils.encodeToString(emplImg));
		}

		model.addAttribute("lists", table);
		return "/doc/tempDocs/tempDocs";
	}
	
	
}