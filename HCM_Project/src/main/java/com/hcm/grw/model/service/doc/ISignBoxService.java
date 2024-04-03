package com.hcm.grw.model.service.doc;

import java.util.List;
import java.util.Map;

import com.hcm.grw.dto.doc.SignBoxDto;
import com.hcm.grw.dto.doc.SignFileDto;
import com.hcm.grw.dto.doc.SignTempBoxDto;

public interface ISignBoxService {

	public int insertDoc(SignBoxDto dto);
	
	public int insertTempDoc(SignTempBoxDto dto);

	public int insertDocFile(SignFileDto dto);
	
	public int updateDoc(SignBoxDto dto);
	
	public SignTempBoxDto getTempDoc(String sitb_doc_num);
	   
	public int deleteDoc(String sidb_doc_num);
	
	public int insertTransaction(SignBoxDto bDto, SignFileDto fDto);
	
	public List<SignFileDto> getFile();
	
	public SignFileDto getDetailFile(String sidf_file_num);
	
	public int insertTempTransaction(SignBoxDto bDto, SignFileDto fDto, String sitb_doc_num);
	
	public int insertTempNoFileTransaction(SignBoxDto bDto, String sitb_doc_num);
	
	public List<SignBoxDto> selectNumber(String empl_id);
	
	public String duplicateDate(Map<String, Object> map);
	
}
