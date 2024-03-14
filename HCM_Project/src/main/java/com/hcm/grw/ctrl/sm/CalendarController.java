package com.hcm.grw.ctrl.sm;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	
	
	@GetMapping(value = "/getAllCalendar.do")
	@ResponseBody
	public JSONArray CalendarList(Model model,ScbDto dto) {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		dto.setType(list);
		dto.setScbo_empno("20220101");
		dto.setDaygridmonth("2024-03");
		List<ScbDto> lists = service.getAllCalendar(dto);
		
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
	
}
