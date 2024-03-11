package com.hcm.grw.model.mapper.doc;

import java.util.List;
import java.util.Map;

import com.hcm.grw.dto.doc.TemplateDto;


public interface ITemplateDao {
	
	public List<TemplateDto> getTempList();
	
	public TemplateDto getDetailTemp(String sidt_temp_cd);
	
	public int updateTemp(Map<String, Object> map);
	
	public int deleteTemp(String sidt_temp_cd);
	
	public int insertTemp(TemplateDto dto);

	public List<TemplateDto> getCategory();
	
	public String getTemplate(String sidt_temp_cd);
}
