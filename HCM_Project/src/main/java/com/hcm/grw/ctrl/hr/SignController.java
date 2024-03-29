package com.hcm.grw.ctrl.hr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.hcm.grw.dto.hr.EmpSignDto;
import com.hcm.grw.model.service.hr.EmpSignService;

@Controller
public class SignController {

	@Autowired
	private EmpSignService empSignService;
	
	@GetMapping(value = "/hr/sign/signListForm.do")
	public String signListForm(Model model) {
		Map<String, Object> signMap = new HashMap<String, Object>();
		signMap.put("empl_id", "20230102");
		List<EmpSignDto> signList = empSignService.selectAllSign(signMap);
		System.out.println(signList);
		model.addAttribute("signList",signList);
		return "hr/sign/signListForm";
	}
	
	
	@GetMapping(value = "/hr/sign/insertSignForm.do")
	public String insertSignForm() {
		return "hr/sign/insertSignForm";
	}
	
	@PostMapping(value = "/hr/sign/insertSign.do")
	@ResponseBody
	public void insertSign(@RequestBody String signJson) {
		Gson signGson = new Gson();
		Map<String, String> map = signGson.fromJson(signJson, Map.class);
		String emsi_title = map.get("emsi_title");
		String emsi_sign_img = map.get("emsi_sign_img");
		String empl_id = map.get("empl_id");
		String emsi_create_id = map.get("emsi_create_id");
		Map<String, Object> signMap = new HashMap<String, Object>();
		signMap.put("emsi_title", emsi_title);
		signMap.put("emsi_sign_img", emsi_sign_img);
		signMap.put("empl_id", empl_id);
		signMap.put("emsi_create_id", emsi_create_id);
		int cnt = empSignService.insertSign(signMap);
		System.out.println(cnt);
	}
	
	@GetMapping(value = "/hr/sign/delThisSign.do")
	public String delThisSign(String emsi_seq) {
		System.out.println(emsi_seq);
		Map<String, Object> signMap = new HashMap<String, Object>();
		signMap.put("empl_id", "20230102");
		signMap.put("emsi_seq", emsi_seq);
		int cnt = empSignService.deleteSign(signMap);
		if(cnt == 1) {
			return "redirect:/hr/sign/signListForm.do";
		}else {
			return "redirect:/hr/sign/signListForm.do";
		}
	}
	
	@GetMapping(value = "/hr/sign/setDefaultSign.do")
	public String setDefaultSign(String emsi_seq) {
		System.out.println(emsi_seq);
		Map<String, Object> signMap = new HashMap<String, Object>();
		signMap.put("empl_id", "20230102");
		
		List<EmpSignDto> defSign = empSignService.defaultChk(signMap);
		List<EmpSignDto> signList = empSignService.selectAllSign(signMap);
		System.out.println(defSign.size());
		if(defSign.size() < signList.size()) {
			System.out.println("디폴트 제거해라");
			empSignService.setAllDefaultSign(signMap);
		}
		
		Map<String, Object> signDefMap = new HashMap<String, Object>();
		signDefMap.put("empl_id", "20230102");
		signDefMap.put("emsi_seq", emsi_seq);
		int cnt = empSignService.setDefaultSign(signDefMap);
		if(cnt > 0) {
			return "redirect:/hr/sign/signListForm.do";
		}else {
			return "redirect:/hr/sign/signListForm.do";
		}
		
	}
	
}
