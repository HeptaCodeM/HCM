package com.hcm.grw.comm;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.hcm.grw.dto.hr.CompanyDto;
import com.hcm.grw.model.mapper.hr.CompanyDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService {

	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private JavaMailSender javaMailSender;

	/**
	* 메일발송 Method
	* @param subject : 제목(String)
	* @param content : 내용(String)
	* @param toEmail : 수신자 메일주소(String)
	* @param fromEmail : 발신자 메일주소(String)
	* @param htmlFlag : 본문 HTML 발송여부(boolean)
	* @return Boolean(메일발송 Flag값)
	* @author SDJ
	* @since 2024.03.12
	*/
	public Boolean sendMail(String subject, String content, String toEmail, String fromEmail, boolean htmlFlag) {
		try {
			if(fromEmail == "" || fromEmail == null) {
				Map<String, Object> companyMap = new HashMap<String, Object>();
				companyMap.put("comp_id", "ITCOM0A1");
				
				CompanyDto comDto = companyDao.showCompanyInfo(companyMap);
				fromEmail = comDto.getComp_email();
				log.info("companyMap : {}", companyMap);
				System.out.println("companyMap : {}"+ companyMap);
			}
			
			log.info("subject : {}, content : {}, toEmail : {}, fromEmail : {}", subject, content, toEmail, fromEmail);
			
			MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setSubject(subject);	// 메일제목은 생략이 가능하다			
            messageHelper.setText(content, htmlFlag);
            messageHelper.setTo(toEmail);
            messageHelper.setFrom(fromEmail);
			
            javaMailSender.send(message);
		}catch(Exception ex) {
			log.info("EMail Send Error : {}", ex.getMessage());
			return false;
		}
		
		return true;
	}	
}
