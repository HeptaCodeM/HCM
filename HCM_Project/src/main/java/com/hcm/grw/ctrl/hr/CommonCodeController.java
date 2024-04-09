package com.hcm.grw.ctrl.hr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcm.grw.comm.Function;
import com.hcm.grw.dto.hr.CommonCodeDto;
import com.hcm.grw.model.service.hr.CommonCodeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CommonCodeController {

	@Autowired
	private CommonCodeService codeService;	
	
	
	@GetMapping(value = "/hr/commonCode/roleListAdmin.do")
	public String roleList(Model model, String role){
		log.info("{} 직위/직책/부서 리스트 화면진입", Function.getMethodName());
		System.out.println(role);
		Map<String, Object> roleMap = new HashMap<String, Object>();
		roleMap.put("role", role);
		
		List<CommonCodeDto> codeList = codeService.selectAllRole(roleMap);
		model.addAttribute("codeList", codeList);
		model.addAttribute("role", role);
		
		return "hr/commonCode/roleListAdmin";
	}	
	
	
	@GetMapping(value = "/hr/commonCode/roleDetailAdmin.do")
	public String hrRoleDetail(String coco_cd , String role , Model model) {
		log.info("{} 직위/직책/부서 상세 화면진입", Function.getMethodName());
		System.out.println(coco_cd);
		System.out.println(role);
		System.out.println(role.equals("DT"));
		Map<String, Object> delChkMap = new HashMap<String, Object>();
		delChkMap.put("role", role);
		if(role.equals("DT")) {
			delChkMap.put("empl_dept_cd", coco_cd);
		}else if(role.equals("RK")) {
			delChkMap.put("empl_rank_cd", coco_cd);
		}else if(role.equals("PN")) {
			delChkMap.put("empl_position_cd", coco_cd);
		}
		int cnt =  codeService.delCodeChk(delChkMap);
		if(cnt > 0) {
			model.addAttribute("delFLAG","N");
		}else {
			model.addAttribute("delFLAG","Y");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coco_cd", coco_cd);
		map.put("role", role);
		CommonCodeDto roleDto = codeService.selectOneRole(map);
		System.out.println(roleDto);
		model.addAttribute("roleDto" , roleDto);
		model.addAttribute("role", role);
		return "hr/commonCode/roleDetailAdmin";
	}
	
	@GetMapping(value = "/hr/commonCode/deleteRoleOneAdmin.do")
	public String deleteRoleOne(HttpServletRequest request) {
		log.info("{} 직위/직책/부서 삭제", Function.getMethodName());
		String coco_cd = request.getParameter("coco_cd");
		System.out.println(coco_cd);
		String role = request.getParameter("role");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coco_cd", coco_cd);
		int cnt = codeService.deleteRoleOne(map);
		if(cnt == 1) {
			return "redirect:/hr/commonCode/roleListAdmin.do?role="+role;
		}else {
			return "redirect:/hr/commonCode/roleListAdmin.do?role="+role;
		}
	}	

	@PostMapping(value = "/hr/commonCode/correctionRoleAdmin.do")
	public @ResponseBody void correctionRole(HttpServletRequest request , Authentication authentication ,HttpServletResponse resp) {
		log.info("{} 직위/직책/부서 수정", Function.getMethodName());

		String coco_name = request.getParameter("coco_name");
		String coco_cd = request.getParameter("coco_cd");
		String role = request.getParameter("role");
		System.out.println(coco_name);
		System.out.println(coco_cd);
		System.out.println(role);
		String coco_modify_id = authentication.getName();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coco_name", coco_name);
		map.put("coco_cd", coco_cd);
		map.put("coco_modify_id", coco_modify_id);
		int cnt =  codeService.correctionRole(map);
		String msg;
		if(cnt == 1) {
			Function.alertLocation("입력 되었습니다.", "/hr/commonCode/roleListAdmin.do?role="+role, "","","");
			return;
		}else {
			Function.alertHistoryBack("입력 시 오류가 발생하였습니다.", "", "");
			return;
		}		
//		return "redirect:/hr/commonCode/roleList.do?role="+role;
	}
	

	@GetMapping(value = "/hr/commonCode/insertRoleAdmin.do")
	public String insertRole(String role , Model model) {
		log.info("{} 직위/직책/부서 입력 화면진입", Function.getMethodName());
		model.addAttribute("role", role);
		return "hr/commonCode/insertRoleAdmin";
	}
	
	@PostMapping(value = "/hr/commonCode/insertRoleOneAdmin.do")
	public @ResponseBody void insertRoleOne(HttpServletRequest request , Authentication authentication ,HttpServletResponse resp) {
		log.info("{} 직위/직책/부서 입력", Function.getMethodName());

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
		
		String coco_create_id = authentication.getName();
		map.put("coco_create_id", coco_create_id);
		
		int cnt = codeService.insertRoleOne(map);
		String msg;
		if(cnt == 1) {
			Function.alertLocation("입력 되었습니다.", "/hr/commonCode/roleListAdmin.do?role="+role, "","","");
			return;
//			return "redirect:/hr/commonCode/roleList.do?role="+role;
		}else {
			Function.alertHistoryBack("입력 시 오류가 발생하였습니다.", "", "");
			return;
//			return "redirect:/hr/commonCode/roleList.do?role="+role;
		}
	}
	
	
	
	@PostMapping(value = "/hr/commonCode/roleNameDuplicateChkAdmin.do")
	@ResponseBody
	public Map<String, Object> roleDuplicateChk(@RequestParam("coco_name") String coco_name ,
												@RequestParam("coco_cd") String coco_cd ,
												@RequestParam("role") String role) {
		log.info("{} 직위/직책/부서 중복확인", Function.getMethodName());
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
