package com.hcm.grw.model.service.doc;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.grw.dto.doc.TemplateDto;
import com.hcm.grw.model.mapper.doc.ITemplateDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TemplateServiceImpl implements ITemplateService{
	
	@Autowired
	private ITemplateDao dao;

	@Override
	public List<TemplateDto> getTempList() {
		log.info("TemplateServiceImpl getTempList 템플릿 리스트 전체조회");
		return dao.getTempList();
	}

	@Override
	public TemplateDto getDetailTemp(String sidt_temp_cd) {
		log.info("TemplateServiceImpl 템플릿 리스트 상세조회 템플릿 리스트 상세조회: {} 템플릿", sidt_temp_cd);
		return dao.getDetailTemp(sidt_temp_cd);
	}

	@Override
	public int updateTemp(Map<String, Object> map) {
		log.info("TemplateServiceImpl updateTemp 템플릿 수정: {}", map);
		return dao.updateTemp(map);
	}

	@Override
	public int deleteTemp(String sidt_temp_cd) {
		log.info("TemplateServiceImpl deleteTemp 템플릿 삭제: {} 템플릿", sidt_temp_cd);
		return dao.deleteTemp(sidt_temp_cd);
	}
	
	@Override
	public int insertTemp(TemplateDto dto) {
		log.info("TemplateServiceImpl insertTemp 템플릿 입력: {}", dto);
		return dao.insertTemp(dto);
	}

	@Override
	public List<TemplateDto> getCategory() {
		log.info("TemplateServiceImpl getCategory 템플릿 카테고리 조회");
		return dao.getCategory();
	}
	
	@Override
	public String getTemplate(String sidt_temp_cd) {
		log.info("TemplateServiceImpl getTemplate 선택한 템플릿 가져오기");
		return dao.getTemplate(sidt_temp_cd);
	}
}
