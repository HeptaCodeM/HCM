package com.hcm.grw.model.service.doc;

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
	
}