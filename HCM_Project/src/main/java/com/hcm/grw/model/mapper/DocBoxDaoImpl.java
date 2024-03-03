package com.hcm.grw.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.DocBoxDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class DocBoxDaoImpl implements IDocBoxDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private String NS = "com.hcm.grw.model.mapper.DocBoxDaoImpl.";
	
	
	//전체 문서함 조회
	@Override
	public List<DocBoxDto> getAllDocs(Map<String, String> inMap) {
		log.info("DocBoxDaoImpl 전체문서함 조회");
		return sqlSessionTemplate.selectList(NS+"getAllDocs",inMap);
	}


	//기안중인 문서 조
	@Override
	public List<DocBoxDto> getMyGian(DocBoxDto dto) {
		log.info("DocBoxDaoImpl 기안중인문서 조회");
		return sqlSessionTemplate.selectList(NS+"getMyGian", dto);
	}


	@Override
	public List<DocBoxDto> getIngDocs(DocBoxDto dto) {
		log.info("DocBoxDaoImpl 진행중인 문서 조회");
		return sqlSessionTemplate.selectList(NS+"getIngDocs", dto);
	}


	@Override
	public List<DocBoxDto> getApprovedDocs(DocBoxDto dto) {
		log.info("DocBoxDaoImpl 결재완료된 문서 조회");
		return sqlSessionTemplate.selectList(NS+"getApprovedDocs", dto);
	}


	@Override
	public List<DocBoxDto> getDeniedDocs(DocBoxDto dto) {
		log.info("DocBoxDaoImpl 반려 문서 조회");
		return sqlSessionTemplate.selectList(NS+"getDeniedDocs", dto);
	}


	@Override
	public List<DocBoxDto> getMyTurnDocs(DocBoxDto dto) {
		log.info("DocBoxDaoImpl 결재 요청받은 문서 조회");
		return sqlSessionTemplate.selectList(NS+"getMyTurnDocs", dto);
	}


	@Override
	public List<DocBoxDto> getChamjoDocs(Map<String, String> inMap) {
		log.info("DocBoxDaoImpl 참조 지정된 조회");
		return sqlSessionTemplate.selectList(NS+"getChamjoDocs", inMap);
	}


	@Override
	public DocBoxDto getDetailDocs(DocBoxDto dto) {
		log.info("DocBoxDaoImpl 상세 조회");
		return sqlSessionTemplate.selectOne(NS+"getDetailDocs", dto);
	}


	@Override
	public int approveJson(DocBoxDto dto) {
		return sqlSessionTemplate.update(NS+"approveJson", dto);
	}


	@Override
	public int approveDoc(DocBoxDto dto) {
		return sqlSessionTemplate.update(NS+"approveDoc", dto);
	}


	@Override
	public int denyDoc(DocBoxDto dto) {
		return sqlSessionTemplate.update(NS+"denyDoc", dto);
	}


	@Override
	public int finalJsonApprove(DocBoxDto dto) {
		return sqlSessionTemplate.update(NS+"finalJsonApprove", dto);
	}


	@Override
	public int finalDocApprove(DocBoxDto dto) {
		return sqlSessionTemplate.update(NS+"finalDocApprove", dto);
	}

}
