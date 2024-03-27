package com.hcm.grw.model.mapper.doc;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.doc.SignBoxDto;
import com.hcm.grw.dto.doc.SignFileDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class DocBoxDaoImpl implements IDocBoxDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private String NS = "com.hcm.grw.model.mapper.doc.DocBoxDaoImpl.";
	
	
	//전체 문서함 조회
	@Override
	public List<SignBoxDto> getAllDocs(SignBoxDto dto) {
		log.info("DocBoxDaoImpl 전체문서함 조회");
		return sqlSessionTemplate.selectList(NS+"getAllDocs",dto);
	}


	//기안중인 문서 조
	@Override
	public List<SignBoxDto> getMyGian(SignBoxDto dto) {
		log.info("DocBoxDaoImpl 기안중인문서 조회");
		return sqlSessionTemplate.selectList(NS+"getMyGian", dto);
	}


	@Override
	public List<SignBoxDto> getIngDocs(SignBoxDto dto) {
		log.info("DocBoxDaoImpl 진행중인 문서 조회");
		return sqlSessionTemplate.selectList(NS+"getIngDocs", dto);
	}


	@Override
	public List<SignBoxDto> getApprovedDocs(SignBoxDto dto) {
		log.info("DocBoxDaoImpl 결재완료된 문서 조회");
		return sqlSessionTemplate.selectList(NS+"getApprovedDocs", dto);
	}


	@Override
	public List<SignBoxDto> getDeniedDocs(SignBoxDto dto) {
		log.info("DocBoxDaoImpl 반려 문서 조회");
		return sqlSessionTemplate.selectList(NS+"getDeniedDocs", dto);
	}


	@Override
	public List<SignBoxDto> getMyTurnDocs(SignBoxDto dto) {
		log.info("DocBoxDaoImpl 결재 요청받은 문서 조회");
		return sqlSessionTemplate.selectList(NS+"getMyTurnDocs", dto);
	}


	@Override
	public List<SignBoxDto> getChamjoDocs(Map<String, String> inMap) {
		log.info("DocBoxDaoImpl 참조 지정된 조회");
		return sqlSessionTemplate.selectList(NS+"getChamjoDocs", inMap);
	}


	@Override
	public SignBoxDto getDetailDocs(SignBoxDto dto) {
		log.info("DocBoxDaoImpl 상세 조회");
		return sqlSessionTemplate.selectOne(NS+"getDetailDocs", dto);
	}
	
	@Override
	public List<SignBoxDto> getDetailDocsList(SignBoxDto dto) {
		log.info("DocBoxDaoImpl 상세 조회 리스트 버전");
		return sqlSessionTemplate.selectList(NS+"getDetailDocsList", dto);
	}


	@Override
	public int approveJson(SignBoxDto dto) {
		return sqlSessionTemplate.update(NS+"approveJson", dto);
	}


	@Override
	public int approveDoc(SignBoxDto dto) {
		return sqlSessionTemplate.update(NS+"approveDoc", dto);
	}


	@Override
	public int denyDoc(SignBoxDto dto) {
		return sqlSessionTemplate.update(NS+"denyDoc", dto);
	}


	@Override
	public int finalJsonApprove(SignBoxDto dto) {
		return sqlSessionTemplate.update(NS+"finalJsonApprove", dto);
	}


	@Override
	public int finalDocApprove(SignBoxDto dto) {
		return sqlSessionTemplate.update(NS+"finalDocApprove", dto);
	}


	@Override
	public List<SignBoxDto> getAllDocsTable(SignBoxDto dto) {
		return sqlSessionTemplate.selectList(NS+"getAllDocsTable",dto);
	}


	@Override
	public List<SignBoxDto> getAllDocsJson(SignBoxDto dto) {
		return sqlSessionTemplate.selectList(NS+"getAllDocsJson",dto);

	}


	@Override
	public List<SignBoxDto> getChamjoJson(Map<String, String> inMap) {
		return sqlSessionTemplate.selectList(NS+"getChamjoJson",inMap);
	}

	@Override
	public SignBoxDto getMyDepth(SignBoxDto dto) {
		return sqlSessionTemplate.selectOne(NS+"getMyDepth",dto);
	}


	@Override
	public List<SignBoxDto> getMyTurnJson(SignBoxDto dto) {
		return sqlSessionTemplate.selectList(NS+"getMyTurnJson",dto);

	}
	
	@Override
	public List<SignBoxDto> getIDidDocs(SignBoxDto dto) {
		return sqlSessionTemplate.selectList(NS+"getIDidDocs",dto);
	}
	
	@Override
	public List<SignFileDto> getFileList(SignBoxDto dto) {
		return sqlSessionTemplate.selectList(NS+"getFileList",dto);
	}
	
	@Override
	public SignFileDto getDocsDetailFile(String sidf_file_num) {
		return sqlSessionTemplate.selectOne(NS + "getDocsDetailFile", sidf_file_num);
	}
	
	@Override
	public List<SignBoxDto> getTempDocs(SignBoxDto dto) {
		return sqlSessionTemplate.selectList(NS+"getTempDocs",dto);
	}
	
	@Override
	public int deleteTempDocs(String sitb_doc_num) {
		return sqlSessionTemplate.delete(NS+"deleteTempDocs",sitb_doc_num);
	}

	@Override
	public String findEmployeeName(String id) {
		return sqlSessionTemplate.selectOne(NS+"findEmployeeName",id);
	}
	
	@Override
	public String findDeptName(String dept) {
		return sqlSessionTemplate.selectOne(NS+"findDeptName",dept);
	}
}
