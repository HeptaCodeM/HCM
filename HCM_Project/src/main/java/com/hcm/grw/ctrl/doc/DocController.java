package com.hcm.grw.ctrl.doc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.hcm.grw.dto.hr.EmpSignDto;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.model.mapper.doc.IDocBoxDao;
import com.hcm.grw.model.service.doc.IApprDenyService;
import com.hcm.grw.model.service.doc.IDocBoxService;
import com.hcm.grw.model.service.hr.EmpSignService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DocController {

	@Autowired
	private IDocBoxService docService;

	@Autowired
	private IDocBoxDao dao;	
	
	@Autowired
	private EmpSignService signService;
	
	

	@GetMapping("/doc/docBox/getDetail.do")
	public String getDetailBoard(Model model, SignBoxDto dto, String docNum, HttpSession session, HttpServletResponse response) throws IOException {
		
		log.info("getDetailBoard getDetail.do GET 글상세 조회 문서번호 : {}", docNum);
		dto.setSidb_doc_num(docNum);

		List<SignBoxDto> docDto = docService.getDetailDocsList(dto);

		EmployeeDto sessionDto = (EmployeeDto) session.getAttribute("userInfoVo");

		// 세션정보와 비교해서 문서조회권한 확인
		String empl_Id = docDto.get(0).getEmpl_id();
		String empl_Ref = docDto.get(0).getEmpl_ref();
		String empl_DeptCd = docDto.get(0).getEmpl_dept_cd();
		String appr_Id1 = docDto.get(0).getAppr_id();
		String appr_Id2 = null;
		String appr_Id3 = null;
		String doc_Stat = docDto.get(0).getSidb_doc_stat();

		int n = 0;

		if (docDto.size() > 1) {
			appr_Id2 = docDto.get(1).getAppr_id();
		}

		if (docDto.size() > 2) {
			appr_Id3 = docDto.get(2).getAppr_id();
		}

		if ((empl_Ref != null && empl_Ref.contains(sessionDto.getEmpl_id()) && (doc_Stat.equals("3") || doc_Stat.equals("4")))
			|| (empl_DeptCd != null && empl_DeptCd.contains(sessionDto.getEmpl_dept_cd()) && (doc_Stat.equals("3") || doc_Stat.equals("4")))
			|| (appr_Id1 != null && appr_Id1.contains(sessionDto.getEmpl_id()))
			|| (appr_Id2 != null && appr_Id2.contains(sessionDto.getEmpl_id()))
			|| (appr_Id3 != null && appr_Id3.contains(sessionDto.getEmpl_id()))
			|| (empl_Id != null && empl_Id.contains(sessionDto.getEmpl_id()))) {

			n += 1;
		}

		// EMPL_REF에서 가져온 모든 사원 번호를 저장할 ArrayList를 선언
		List<String> allEmployeeIds = new ArrayList<>();

		String emplRef = docDto.get(0).getEmpl_ref();
		if (emplRef != null && !emplRef.isEmpty()) {
			String[] emplIds = emplRef.split(",");
			allEmployeeIds = Arrays.asList(emplIds);
		}
		
		String trimId = "";
		StringBuilder employeeNamesBuilder = new StringBuilder();
		for (String id : allEmployeeIds) {
			trimId = id.trim();
			String employeeName = docService.findEmployeeName(trimId);
			employeeNamesBuilder.append(employeeName).append(", ");
		}

		if (employeeNamesBuilder.length() > 0) {
			employeeNamesBuilder.setLength(employeeNamesBuilder.length() - 2);
		}

		String concatenatedNames = employeeNamesBuilder.toString();
		docDto.get(0).setEmpl_ref(concatenatedNames);

		// 참조 부서명 가져오기
		List<String> allDeptIds = new ArrayList<>();

		String deptRef = docDto.get(0).getEmpl_dept_cd();
		if (deptRef != null && !deptRef.isEmpty()) {
			String[] deptIds = deptRef.split(",");
			allDeptIds = Arrays.asList(deptIds);
		}

		String trimDeptId = "";
		StringBuilder deptNamesBuilder = new StringBuilder();
		for (String id : allDeptIds) {
			trimDeptId = id.trim();
			String deptName = docService.findDeptName(trimDeptId);
			deptNamesBuilder.append(deptName).append(", ");
		}

		if (deptNamesBuilder.length() > 0) {
			deptNamesBuilder.setLength(deptNamesBuilder.length() - 2);
		}

		String concatDeptNames = deptNamesBuilder.toString();
		docDto.get(0).setEmpl_dept_cd(concatDeptNames);

		Map<String, Object> signMap = new HashMap<String, Object>();
		signMap.put("empl_id", sessionDto.getEmpl_id());
		List<EmpSignDto> signList = signService.selectAllSign(signMap);
		model.addAttribute("signList", signList);

		model.addAttribute("docDto", docDto);

		if (n != 1) {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print("<script>alert('접근권한이 없는 결재 문서입니다'); location.href='/doc/docBox.do'</script>");
		} else {
			return "/doc/docBox/boardDetail/boardDetail";
		}

		return null;
	}

	@GetMapping(value = "/doc/docBox.do")
	public String allDocs(Model model, HttpSession session) {
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
			table.get(i).setEmpl_pictureStr(Function.blobImageToString(table.get(i).getEmpl_picture()));
            table.get(i).setEmpl_picture(null);
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
		model.addAttribute("lists", table);
		return "/doc/docBox/docBox";
	}

	@GetMapping(value = "/doc/allDocs.do", produces = "application/json;")
	@ResponseBody
	public String allDocsAjax(HttpSession session) {
		SignBoxDto dto = new SignBoxDto();
		EmployeeDto Edto = (EmployeeDto) session.getAttribute("userInfoVo");

		dto.setEmpl_id(Edto.getEmpl_id());

		List<SignBoxDto> table = dao.getAllDocsTable(dto);
		List<SignBoxDto> json = dao.getAllDocsJson(dto);

		// 본테이블과 json 데이터 합치기
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
			table.get(i).setEmpl_pictureStr(Function.blobImageToString(table.get(i).getEmpl_picture()));
			table.get(i).setEmpl_picture(null);
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
		SignBoxDto dto = new SignBoxDto();
		EmployeeDto Edto = (EmployeeDto) session.getAttribute("userInfoVo");

		dto.setEmpl_id(Edto.getEmpl_id());

		List<SignBoxDto> table = dao.getMyGian(dto);
		List<SignBoxDto> json = dao.getAllDocsJson(dto);

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
			table.get(i).setEmpl_pictureStr(Function.blobImageToString(table.get(i).getEmpl_picture()));
			table.get(i).setEmpl_picture(null);
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
		SignBoxDto dto = new SignBoxDto();
		EmployeeDto Edto = (EmployeeDto) session.getAttribute("userInfoVo");

		dto.setEmpl_id(Edto.getEmpl_id());

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
			table.get(i).setEmpl_pictureStr(Function.blobImageToString(table.get(i).getEmpl_picture()));
			table.get(i).setEmpl_picture(null);
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
		SignBoxDto dto = new SignBoxDto();
		EmployeeDto Edto = (EmployeeDto) session.getAttribute("userInfoVo");

		dto.setEmpl_id(Edto.getEmpl_id());

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
			table.get(i).setEmpl_pictureStr(Function.blobImageToString(table.get(i).getEmpl_picture()));
			table.get(i).setEmpl_picture(null);
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

	@GetMapping(value = "/doc/denyBox.do", produces = "application/json;")
	@ResponseBody
	public String denyDocsAjax(HttpSession session) {
		SignBoxDto dto = new SignBoxDto();
		EmployeeDto Edto = (EmployeeDto) session.getAttribute("userInfoVo");

		dto.setEmpl_id(Edto.getEmpl_id());

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
			table.get(i).setEmpl_pictureStr(Function.blobImageToString(table.get(i).getEmpl_picture()));
			table.get(i).setEmpl_picture(null);
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

		EmployeeDto Edto = (EmployeeDto) session.getAttribute("userInfoVo");

		Map<String, String> inMap = new HashMap<>();
		inMap.put("empl_id", Edto.getEmpl_id());
		inMap.put("empl_dept_cd", Edto.getEmpl_dept_cd());

		List<SignBoxDto> table = dao.getChamjoDocs(inMap);
		List<SignBoxDto> json = dao.getChamjoJson(inMap);


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
			table.get(i).setEmpl_pictureStr(Function.blobImageToString(table.get(i).getEmpl_picture()));
			table.get(i).setEmpl_picture(null);
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
		SignBoxDto dto = new SignBoxDto();

		EmployeeDto Edto = (EmployeeDto) session.getAttribute("userInfoVo");

		dto.setEmpl_id(Edto.getEmpl_id());

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
			table.get(i).setEmpl_pictureStr(Function.blobImageToString(table.get(i).getEmpl_picture()));
			table.get(i).setEmpl_picture(null);
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

	//내가 결재한 문서함
	@GetMapping(value = "/doc/iDidBox.do", produces = "application/json;")
	@ResponseBody
	public String iDidBox(HttpSession session) {
		SignBoxDto dto = new SignBoxDto();

		EmployeeDto Edto = (EmployeeDto) session.getAttribute("userInfoVo");

		dto.setEmpl_id(Edto.getEmpl_id());

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
			table.get(i).setEmpl_pictureStr(Function.blobImageToString(table.get(i).getEmpl_picture()));
			table.get(i).setEmpl_picture(null);
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
		dto.setSidb_doc_num(docNum);
		List<SignFileDto> list = docService.getFileList(dto);
		return ResponseEntity.ok(list);
	}

	//상세조회 문서에 첨부된 파일 다운로드
	@GetMapping("/doc/docBox/fileDown.do")
	@ResponseBody
	public void fileDown(@RequestParam String sidf_file_num)
			throws IOException, SerialException, SQLException {
		SignFileDto dto = docService.getDocsDetailFile(sidf_file_num);

		FileCommonService.fileDownload(dto.getSidf_file_origin(), dto.getSidf_file_content());
	}
	
 
	 //임시보관함
	@GetMapping(value = "/doc/tempDocs.do")
	public String TempDocs(Model model, HttpSession session) {
		SignBoxDto dto = new SignBoxDto();
		EmployeeDto Edto = (EmployeeDto) session.getAttribute("userInfoVo");

		dto.setEmpl_id(Edto.getEmpl_id());
		model.addAttribute("Edto", Edto);
		List<SignBoxDto> table = dao.getTempDocs(dto);
		
		for (int i = 0; i < table.size(); i++) {
		table.get(i).setEmpl_pictureStr(Function.blobImageToString(table.get(i).getEmpl_picture()));
		table.get(i).setEmpl_picture(null);
		}
		model.addAttribute("lists", table);
		return "/doc/tempDocs/tempDocs";
	}
	
	
	@PostMapping(value = "/doc/deleteTempDocs.do", produces = "application/json;")
	@ResponseBody
	public String deleteAjax(HttpSession session, String docNum) {
		SignBoxDto dto = new SignBoxDto();
       
		dto.setSidb_doc_num(docNum);
		
		//문서 삭제하는 메소드 
		int n=docService.deleteTempDocs(docNum);
		
		EmployeeDto Edto = (EmployeeDto) session.getAttribute("userInfoVo");

		dto.setEmpl_id(Edto.getEmpl_id());
		List<SignBoxDto> table = dao.getTempDocs(dto);
		
		for (int i = 0; i < table.size(); i++) {
			table.get(i).setEmpl_pictureStr(Function.blobImageToString(table.get(i).getEmpl_picture()));
			table.get(i).setEmpl_picture(null);
			}
		
		if (n==1) {
		Gson data = new GsonBuilder().create();
		return data.toJson(table);
		}	
		return null;
	}
}