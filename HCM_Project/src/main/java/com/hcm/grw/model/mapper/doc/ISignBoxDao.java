package com.hcm.grw.model.mapper.doc;

import com.hcm.grw.dto.doc.SignBoxDto;
import com.hcm.grw.dto.doc.SignFileDto;
import com.hcm.grw.dto.doc.SignTempBoxDto;

public interface ISignBoxDao {
	
	public int insertDoc(SignBoxDto dto);

	public int insertTempDoc(SignTempBoxDto dto);

	public int insertDocFile(SignFileDto dto);
	
	public int updateDoc(SignBoxDto dto);
	
	public SignTempBoxDto getTempDoc(String sidb_doc_num);

	public int deleteDoc(String sidb_doc_num);
}
