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
	
	
	
	@GetMapping(value = "/roleList.do")
	public String roleList(Model model, String role) {
		System.out.println(role);
		
		Map<String, Object> roleMap = new HashMap<String, Object>();
		roleMap.put("role", role);
		
		List<CommonCodeDto> codeList = codeService.selectAllRole(roleMap);
		model.addAttribute("codeList", codeList);
		model.addAttribute("role", role);
		
		return "hr/CommonCode/roleList";
	}	
	
	
	@GetMapping(value = "/roleDetail.do")
	public String hrDeptDetail(String coco_cd , String role , Model model) {
		System.out.println(coco_cd);
		System.out.println(role);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coco_cd", coco_cd);
		map.put("role", role);
		CommonCodeDto roleDto = codeService.selectOneRole(map);
		System.out.println(roleDto);
		model.addAttribute("roleDto" , roleDto);
		model.addAttribute("role", role);
		return "hr/CommonCode/roleDetail";
	}
	
	@GetMapping(value = "/deleteRoleOne.do")
	public String deleteDeptOne(HttpServletRequest request) {
		String coco_cd = request.getParameter("coco_cd");
		System.out.println(coco_cd);
		String role = request.getParameter("role");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coco_cd", coco_cd);
		int cnt = codeService.deleteRoleOne(map);
		if(cnt == 1) {
			return "redirect:./roleList.do?role="+role;
		}else {
			return "redirect:./roleList.do?role="+role;
		}
	}	

	@PostMapping(value = "/correctionRole.do")
	public String correctionDept(HttpServletRequest request) {
		String coco_name = request.getParameter("coco_name");
		String coco_cd = request.getParameter("coco_cd");
		String role = request.getParameter("role");
		System.out.println(coco_name);
		System.out.println(coco_cd);
		System.out.println(role);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coco_name", coco_name);
		map.put("coco_cd", coco_cd);
		codeService.correctionRole(map);
		return "redirect:./roleList.do?role="+role;
	}
	

	@GetMapping(value = "/insertRole.do")
	public String insertRole(String role , Model model) {
		model.addAttribute("role", role);
		return "hr/CommonCode/insertRole";
	}
	
	@PostMapping(value = "/insertRoleOne.do")
	public String insertDeptOne(HttpServletRequest request) {
		String coco_name = request.getParameter("coco_name");
		String coco_cd = request.getParameter("coco_cd");
		String role = request.getParameter("role");
		System.out.println(coco_name);
		System.out.println(coco_cd);
		System.out.println(role);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coco_name", coco_name);
		map.put("coco_cd", coco_cd);
		
		// TODO login세션 생기면 제거하고 테스트 1
		map.put("coco_create_id", "SYSTEM");
		
		int cnt = codeService.insertRoleOne(map);
		if(cnt == 1) {
			return "redirect:./roleList.do?role="+role;
		}else {
			return "redirect:./roleList.do?role="+role;
		}
	}
	
	
}
