package com.hcm.grw.ctrl.hr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.hcm.grw.dto.hr.CommonCodeDto;
import com.hcm.grw.model.service.hr.CommonCodeService;

@Controller
public class CommonCodeController {

	@Autowired
	private CommonCodeService codeService;
	
	@GetMapping(value = "/hrDept.do")
	public String hrDept(Model model) {
		List<CommonCodeDto> deptList = codeService.selectAllDept();
		model.addAttribute("deptList",deptList);
		return "hr/CommonCode/hrDept";
	}
	
	@GetMapping(value = "/hrDeptDetail.do")
	public String hrDeptDetail(String coco_cd , Model model) {
		System.out.println(coco_cd);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coco_cd", coco_cd);
		CommonCodeDto codeDto = codeService.selectOneDept(map);
		System.out.println(codeDto);
		model.addAttribute("codeDto" , codeDto);
		return "hr/CommonCode/hrDeptDetail";
	}
	
	@PostMapping(value = "/correctionDept.do")
	public String correctionDept(HttpServletRequest request) {
		String coco_name = request.getParameter("coco_name");
		String coco_cd = request.getParameter("coco_cd");
		System.out.println(coco_name);
		System.out.println(coco_cd);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coco_name", coco_name);
		map.put("coco_cd", coco_cd);
		codeService.correctionDept(map);
		return "redirect:./hrDept.do";
	}
	
	@GetMapping(value = "/deleteDeptOne.do")
	public String deleteDeptOne(HttpServletRequest request) {
		String coco_cd = request.getParameter("coco_cd");
		System.out.println(coco_cd);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coco_cd", coco_cd);
		int cnt = codeService.deleteDeptOne(map);
		if(cnt == 1) {
			return "redirect:./hrDept.do";
		}else {
			return "redirect:./hrDept.do";
		}
	}
	
	@GetMapping(value = "/insertDept.do")
	public String insertDept() {
		return "hr/CommonCode/insertDept";
	}
	
	@PostMapping(value = "/insertDeptOne.do")
	public String insertDeptOne(HttpServletRequest request) {
		String coco_name = request.getParameter("coco_name");
		String coco_cd = request.getParameter("coco_cd");
		System.out.println(coco_name);
		System.out.println(coco_cd);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coco_name", coco_name);
		map.put("coco_cd", coco_cd);
		
		// TODO login세션 생기면 제서하고 테스트 1
		map.put("coco_create_id", "SYSTEM");
		
		int cnt = codeService.insertDeptOne(map);
		if(cnt == 1) {
			return "redirect:./hrDept.do";
		}else {
			return "redirect:./hrDept.do";
		}
	}
	
	
}
