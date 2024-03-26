package com.hcm.grw.ctrl.sm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.dto.sm.ScbDto;
import com.hcm.grw.model.service.sm.IScbService;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequestMapping("sm/")
public class CalendarController {

	
	
	@Autowired IScbService service;
	
	
	@GetMapping(value = "/calendar.do")
	public String calendarMove() {
		log.info("CalendarController 캘린더 화면 이동");
		return "sm/schedule/calendar";
	}
	
	
	
	@GetMapping("/getAllCalendar.do")
	@ResponseBody
	public JSONArray CalendarList(String daygridmonth, HttpSession session) {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@"+daygridmonth);
		String year = "";
        String month = "";
        String[] parts = daygridmonth.split("\\s+");
        for (String part : parts) {
            if (part.contains("년")) {
                // "년" 문자를 제거하고 연도 추출
                year = part.replace("년", "");
            } else if (part.contains("월")) {
                // "월" 문자를 제거하고 월 추출
                month = part.replace("월", "");
            }
        }
        if (month.length() == 1) {
            month = "0" + month;
        }
        String output = year + "-" + month;
		log.info("CalendarController getAllCalendar.do  캘린더 데이터 로드");
		ScbDto dto = new ScbDto();
		EmployeeDto empldto = (EmployeeDto)session.getAttribute("userInfoVo");
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		dto.setDaygridmonth(output);
		dto.setScbo_empno(empldto.getEmpl_id());
		dto.setType(list);
		List<ScbDto> lists = service.getAllCalendar(dto);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+lists);
		JSONArray arr = new JSONArray();
			for (ScbDto vo : lists) {
				JSONObject obj = new JSONObject();
				obj.put("scbo_no", vo.getScbo_no());
				obj.put("scbo_empno", vo.getScbo_empno());
				obj.put("scbo_cgory_no", vo.getScbo_cgory_no());
				obj.put("title", vo.getScbo_title());
				obj.put("start", vo.getScbo_start());
				obj.put("end", vo.getScbo_end());
				arr.add(obj);
			}
			return arr;
	}
	
	
	@PostMapping("/insertScbo.do")
	@ResponseBody
	public boolean calendarInsert(ScbDto dto, HttpSession session) {
		log.info("CalendarController insertScbo.do 일정 등록");
		EmployeeDto empldto = (EmployeeDto)session.getAttribute("userInfoVo");
	    dto.setScbo_writer(empldto.getEmpl_name());
	    dto.setScbo_empno(empldto.getEmpl_id());
	    dto.setScbo_modify_id(empldto.getEmpl_id());
			int n  = service.insertScbo(dto);
			return (n>0)?true:false;
	}	
	
	@PostMapping("/detailScbo.do")
	@ResponseBody
	public ScbDto detailCalendar(@RequestParam Map<String, Object> map, HttpSession session) {
		log.info("CalendarController detailScbo.do 일정 상세 조회");
		EmployeeDto empldto = (EmployeeDto)session.getAttribute("userInfoVo");
		map.put("scbo_empno",empldto.getEmpl_id());
		ScbDto dto = service.detailScbo(map);
		System.out.println(dto);
		return dto;
	}
	
	
	@PostMapping("/updateScbo.do")
	@ResponseBody
	public boolean updateScbo(ScbDto dto,HttpSession session) {
		log.info("CalendarController detailScbo.do 일정 수정");
		EmployeeDto empldto = (EmployeeDto)session.getAttribute("userInfoVo");
		dto.setScbo_modify_id(empldto.getEmpl_id());
		int n = service.updateScbo(dto);
		return (n>0)?true:false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
