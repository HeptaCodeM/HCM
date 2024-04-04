package com.hcm.grw.ctrl.doc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.hcm.grw.dto.doc.SignTempBoxDto;
import com.hcm.grw.dto.hr.CompanyDto;
import com.hcm.grw.dto.hr.EmpSignDto;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.model.service.doc.ISignBoxService;
import com.hcm.grw.model.service.doc.ISignFavoService;
import com.hcm.grw.model.service.doc.ITempTreeService;
import com.hcm.grw.model.service.hr.EmpSignService;
import com.hcm.grw.model.service.hr.HolidayService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("doc/")
public class SignPageController {
	
	@Autowired
	private ISignFavoService service;
	
	@Autowired
	private EmpSignService signService;
	
	@Autowired
	private ISignBoxService bService;
	
	
	@Autowired
	private ITempTreeService tService;
	
	@Autowired
	private HolidayService holiService;
	
	@GetMapping("signFavo.do")
	public String signFavo(Model model, HttpSession session) {
		log.info("SignTreeController signFavo.do GET 결재선 관리 페이지");
		return "doc/approverSet/signFavo";
	}
	
	@GetMapping("writeDoc/signRefer.do")
	public String signRefer() {
		log.info("SignTreeController signRefer.do GET 참조 팝업 페이지");
		return "doc/writeDoc/signSet/signRefer";
	}
	
	@GetMapping("writeDoc/signLine.do")
	public String signLine() {
		log.info("SignTreeController signLine.do GET 결재선 팝업 페이지");
		return "doc/writeDoc/signSet/signLine";
	}
	
	@GetMapping("writeDoc/selectSign.do")
	public String selectSign(Model model, HttpSession session) {
		log.info("SignTreeController selectSign.do GET 서명 팝업 페이지");
		EmployeeDto dto = (EmployeeDto)session.getAttribute("userInfoVo");
		if(dto == null) {
			return "doc/writeDoc/signSet/selectSign";
		}
		Map<String, Object> signMap = new HashMap<String, Object>();
		signMap.put("empl_id", dto.getEmpl_id());
		List<EmpSignDto> signList = signService.selectAllSign(signMap);
		model.addAttribute("signList",signList);
		return "doc/writeDoc/signSet/selectSign";
	}
	
	@SuppressWarnings("deprecation")
	@GetMapping("writeDoc.do")
	public String writeDoc(Authentication auth, HttpSession session, Model model) {
		log.info("SignPageController writeDoc.do 기안문 작성 페이지로 로그인 정보 전달");
		if(auth != null) {
			String id = auth.getName();
			List<EmployeeDto> dto = service.getFav(List.of(id));
			session.setAttribute("loginInfo", dto.get(0));
			model.addAttribute("loginInfo", dto.get(0));
			Map<String, Object> map = holiService.selectEmpTotalHoliDayInfo(dto.get(0).getEmpl_id());
			model.addAttribute("holiMap", map);
		}
		CompanyDto cDto = tService.getCompInfo();
		if(cDto.getComp_seal() != null) {
			cDto.setComp_seal_str(Base64Utils.encodeToString(cDto.getComp_seal()));
			cDto.setComp_seal(null);
		}
		model.addAttribute("com", cDto);
		return "doc/writeDoc/writeDoc";
	}
	
	@SuppressWarnings("deprecation")
	@GetMapping(value = "getTempDoc.do")
	public String getTempDoc(@RequestParam String sitb_doc_num, Model model) throws JsonMappingException, JsonProcessingException {
		log.info("SignPageController getTempDoc.do 임시보관함 문서 불러오기");
		SignTempBoxDto dto = bService.getTempDoc(sitb_doc_num);
		model.addAttribute("dto", dto);
		Map<String, Object> map = holiService.selectEmpTotalHoliDayInfo(dto.getEmpl_id());
		model.addAttribute("holiMap",map);

		return "/doc/writeDoc/getTempDoc";
	}
	
	@GetMapping("signManagement.do")
	public String signManagement(Model model, HttpSession session) {
		log.info("SignTreeController signManagement.do GET 서명 관리 페이지");
		EmployeeDto dto = (EmployeeDto)session.getAttribute("userInfoVo");
		if(dto == null) {
			return "doc/signManagement/signListForm";
		}
		Map<String, Object> signMap = new HashMap<String, Object>();
		signMap.put("empl_id", dto.getEmpl_id());
		List<EmpSignDto> signList = signService.selectAllSign(signMap);
		model.addAttribute("signList",signList);
		
//		Map<String, List<AlarmDto>> alarmMap = alarmService.alarmTransaction(dto.getEmpl_id());
//		List<AlarmDto> noticeList = alarmMap.get("noticeList");
//		List<AlarmDto> approvalList = alarmMap.get("approvalList");
//		List<AlarmDto> rejectList = alarmMap.get("rejectList");
//		List<AlarmDto> requestList = alarmMap.get("requestList");
//		model.addAttribute("noticeList", noticeList);
//		model.addAttribute("approvalList", approvalList);
//		model.addAttribute("rejectList", rejectList);
//		model.addAttribute("requestList", requestList);
		
		return "doc/signManagement/signListForm";
	}
	
}