package com.hcm.grw.schedule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcm.grw.model.service.hr.EmployeeService;
import com.hcm.grw.model.service.hr.HolidayService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SpecialDaySchedule {

	@Autowired
	private HolidayService holidayService;
	
	@Value("${dataspc.endpoint}")
	private String endPoint;
	
	@Value("${dataspc.enckey}")
	private String enckey;

	@Value("${dataspc.deckey}")
	private String deckey;
	
	@Scheduled(cron="0 0 0 1 1/1 ? *")
	@Transactional
	public void registSpecialDay() throws IOException {
        log.info("SpecialDaySchedule registSpecialDay 특일정보 입력/수정");
		
		// 현재 날짜 가져오기
        LocalDate currentDate = LocalDate.now();
        // 현재 년도 가져오기
        String currentYear = String.valueOf(currentDate.getYear());
        
		enckey = "OlJwJ0%2BOpRU5Ypr7djRvzpeqfrFHathK%2FslkQQOuWV7iyv9GLBfraQXIMSsGc9nXfrjj1Jbp9SvO3Xbj2GJK6g%3D%3D";
		endPoint = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo?solYear="+currentYear;
		endPoint += "&numOfRows=30&_type=json&ServiceKey=";
		endPoint += enckey;
		//log.info("endPoint : {}", endPoint);

        URL url = new URL(endPoint);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        
        
        // JSON 데이터를 자바 객체로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(sb.toString());

        // JSON에서 resultCode 값 가져오기
        String resultCode = rootNode.path("response").path("header").path("resultCode").asText();
        int totalCount = rootNode.path("response").path("body").path("totalCount").asInt();
        JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");

        log.info("resultCode : {}", resultCode);
        //log.info("items : {}", itemsNode);

        Map<String, String> specialMap;
        int n = 0;
        if(resultCode.equals("00") && totalCount>0) {
        	for (JsonNode itemNode : itemsNode) {
            	specialMap = new HashMap<String, String>();
                String locdate = itemNode.path("locdate").asText();
                String dateName = itemNode.path("dateName").asText();
                String isHoliday = itemNode.path("isHoliday").asText();

                // Do something with the values
                log.info("locdate: {}, dateName: {}, isHoliday: {}", locdate, dateName, isHoliday);
                
                specialMap.put("holi_date", locdate);
                specialMap.put("holi_name", dateName);
                specialMap.put("holi_flag", isHoliday);
                n += holidayService.registSpecialDay(specialMap);
            }
        }
        
        log.info("최종 수행건 수 : {}", n);
        
	}
}
