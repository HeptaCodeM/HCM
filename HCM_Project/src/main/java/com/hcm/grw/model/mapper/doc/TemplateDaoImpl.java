package com.hcm.grw.model.mapper.doc;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.doc.TemplateDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class TemplateDaoImpl implements ITemplateDao {
	
	@Autowired
	private SqlSessionTemplate session;

	private final String NS = "com.hcm.grw.model.mapper.doc.TemplateDaoImpl.";

	@Override
	public List<TemplateDto> getTempList() {
		log.info("TemplateDaoImpl getTempList DAO Access");
		return session.selectList(NS+"getTempList");
	}

	@Override
	public TemplateDto getDetailTemp(String sidt_temp_cd) {
		log.info("TemplateDaoImpl getDetailTemp DAO Access");
		return session.selectOne(NS+"getDetailTemp", sidt_temp_cd);
	}

	@Override
	public int updateTemp(Map<String, Object> map) {
		log.info("TemplateDaoImpl updateTemp DAO Access");
		return session.update(NS+"updateTemp", map);
	}

	@Override
	public int deleteTemp(String sidt_temp_cd) {
		log.info("TemplateDaoImpl deleteTemp DAO Access");
		return session.update(NS+"deleteTemp", sidt_temp_cd);
	}
	
	@Override
	public int insertTemp(TemplateDto dto) {
		log.info("TemplateDaoImpl insertTemp DAO Access");
		return session.insert(NS+"insertTemp", dto);
	}
	
	@Override
	public List<TemplateDto> getCategory() {
		log.info("TemplateDaoImpl getCategory DAO Access");
		return session.selectList(NS + "getCategory");
	}
	
	@Override
	public String getTemplate(String sidt_temp_cd) {
		log.info("TemplateDaoImpl getTemplate DAO Access");
		return session.selectOne(NS+"getTemplate");
	}

}
