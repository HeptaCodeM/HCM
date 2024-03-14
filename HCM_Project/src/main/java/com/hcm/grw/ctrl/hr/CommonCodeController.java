package com.hcm.grw.ctrl.hr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcm.grw.dto.hr.CommonCodeDto;
import com.hcm.grw.model.service.hr.CommonCodeService;

@Controller
public class CommonCodeController {

	@Autowired
	private CommonCodeService codeService;
	
	
	
	@GetMapping(value = "/hr/commonCode/roleList.do")
	public String roleList(Model model, String role) {
		System.out.println(role);
		
		Map<String, Object> roleMap = new HashMap<String, Object>();
		roleMap.put("role", role);
		
		List<CommonCodeDto> codeList = codeService.selectAllRole(roleMap);
		model.addAttribute("codeList", codeList);
		model.addAttribute("role", role);
		
		return "hr/commonCode/roleList";
	}	
	
	
	@GetMapping(value = "/hr/commonCode/roleDetail.do")
	public String hrRoleDetail(String coco_cd , String role , Model model) {
		System.out.println(coco_cd);
		System.out.println(role);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coco_cd", coco_cd);
		map.put("role", role);
		CommonCodeDto roleDto = codeService.selectOneRole(map);
		System.out.println(roleDto);
		model.addAttribute("roleDto" , roleDto);
		model.addAttribute("role", role);
		return "hr/commonCode/roleDetail";
	}
	
	@GetMapping(value = "/hr/commonCode/deleteRoleOne.do")
	public String deleteRoleOne(HttpServletRequest request) {
		String coco_cd = request.getParameter("coco_cd");
		System.out.println(coco_cd);
		String role = request.getParameter("role");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coco_cd", coco_cd);
		int cnt = codeService.deleteRoleOne(map);
		if(cnt == 1) {
			return "redirect:/hr/commonCode/roleList.do?role="+role;
		}else {
			return "redirect:/hr/commonCode/roleList.do?role="+role;
		}
	}	

	@PostMapping(value = "/hr/commonCode/correctionRole.do")
	public String correctionRole(HttpServletRequest request) {
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
		return "redirect:/hr/commonCode/roleList.do?role="+role;
	}
	

	@GetMapping(value = "/hr/commonCode/insertRole.do")
	public String insertRole(String role , Model model) {
		model.addAttribute("role", role);
		return "hr/commonCode/insertRole";
	}
	
	@PostMapping(value = "/hr/commonCode/insertRoleOne.do")
	public String insertRoleOne(HttpServletRequest request) {
		String coco_name = request.getParameter("coco_name");
		String coco_cd = request.getParameter("coco_cd");
		String role = request.getParameter("role");
		System.out.println(coco_name);
		System.out.println(coco_cd);
		System.out.println(role);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coco_name", coco_name);
		map.put("coco_cd", coco_cd);
		map.put("role", role);
		
		// TODO login세션 생기면 제거하고 테스트 [재원]
		map.put("coco_create_id", "SYSTEM");
		
		int cnt = codeService.insertRoleOne(map);
		if(cnt == 1) {
			return "redirect:/hr/commonCode/roleList.do?role="+role;
		}else {
			return "redirect:/hr/commonCode/roleList.do?role="+role;
		}
	}
	
	
	
	@PostMapping(value = "/hr/commonCode/roleNameDuplicateChk.do")
	@ResponseBody
	public Map<String, Object> roleDuplicateChk(@RequestParam("coco_name") String coco_name ,
												@RequestParam("coco_cd") String coco_cd ,
												@RequestParam("role") String role) {
		System.out.println(coco_name);
		System.out.println(coco_cd);
		System.out.println(role);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("role", role);
		map.put("coco_cd", coco_cd);
		map.put("coco_name", coco_name);
		int nameCnt = codeService.roleNameDuplicateChk(map);
		int codeCnt = codeService.roleCodeDuplicateChk(map);
		
		if(nameCnt > 0) {
			map.put("nameFlag", "false");
		}else {
			map.put("nameFlag", "true");
		}
		
		if(codeCnt > 0) {
			map.put("codeFlag", "false");
		}else {
			map.put("codeFlag", "true");
		}
		
		return map;
	}
}
