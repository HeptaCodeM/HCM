package com.hcm.grw.model.mapper.doc;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.doc.SignBoxDto;
import com.hcm.grw.dto.doc.SignFileDto;
import com.hcm.grw.dto.doc.SignTempBoxDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class SIgnBoxDaoImpl implements ISignBoxDao {

	@Autowired
	private SqlSessionTemplate template;
	private final String NS = "com.hcm.grw.model.mapper.SignBoxDaoImpl.";
	
	@Override
	public int insertDoc(SignBoxDto dto) {
		log.info("SIgnBoxDaoImpl insertDoc DAO Access");
		return template.insert(NS + "insertDoc", dto);
	}
	
	@Override
	public int insertTempDoc(SignTempBoxDto dto) {
		log.info("SIgnBoxDaoImpl insertTempDoc DAO Access");
		return template.insert(NS + "insertTempDoc", dto);
	}
	
	@Override
	public int insertDocFile(SignFileDto dto) {
		log.info("SIgnBoxDaoImpl insertDocFile DAO Access");
		return template.insert(NS + "insertDocFile", dto);
	}
	
	@Override
	public int updateDoc(SignBoxDto dto) {
		log.info("SIgnBoxDaoImpl updateDoc DAO Access");
		return template.update(NS + "updateDoc", dto);
	}
	@Override
	public SignTempBoxDto getTempDoc(String sidb_doc_num) {
		log.info("SignBoxDaoImpl getTempDoc DAO Access");
		return template.selectOne(NS + "getTempDoc", sidb_doc_num);
	}

	@Override
	public int deleteDoc(String sidb_doc_num) {
		log.info("SignBoxDaoImpl deleteDoc DAO Access");
		return template.delete(NS + "deleteDoc", sidb_doc_num);
	}
	
	@Override
	public List<SignFileDto> getFile() {
		log.info("SignBoxDaoImpl getFile DAO Access");
		return template.selectList(NS + "getFile");
	}
	
	@Override
	public SignFileDto getDetailFile(String sidf_file_num) {
		log.info("SignBoxDaoImpl getDetailFile DAO Access");
		return template.selectOne(NS + "getDetailFile", sidf_file_num);
	}
	
	@Override
	public List<SignBoxDto> selectNumber(String empl_id) {
		log.info("SignBoxDaoImpl selectNumber DAO Access");
		return template.selectList(NS + "selectNumber", empl_id);
	}
	
	@Override
	public String duplicateDate(Map<String, Object> map) {
		log.info("SignBoxDaoImpl duplicateDate DAO Access");
		return template.selectOne(NS + "duplicateDate", map);
	}
}
