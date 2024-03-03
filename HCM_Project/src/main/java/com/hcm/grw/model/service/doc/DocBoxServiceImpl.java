package com.hcm.grw.model.service.doc;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcm.grw.dto.doc.DocBoxDto;
import com.hcm.grw.model.mapper.doc.IDocBoxDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DocBoxServiceImpl implements IDocBoxService {

	@Autowired
	private IDocBoxDao dao;
	
	@Override
	public List<DocBoxDto> getAllDocs(Map<String, String> inMap) {
		log.info("DocBoxServiceImpl  전체 문서 조회");
		return dao.getAllDocs(inMap);
	}

	@Override
	public List<DocBoxDto> getMyGian(DocBoxDto dto) {
		log.info("DocBoxServiceImpl 기안중인 문서 조회");
		return dao.getMyGian(dto);
	}

	@Override
	public List<DocBoxDto> getIngDocs(DocBoxDto dto) {
		return dao.getIngDocs(dto);
	}

	@Override
	public List<DocBoxDto> getApprovedDocs(DocBoxDto dto) {
		return dao.getApprovedDocs(dto);
	}

	@Override
	public List<DocBoxDto> getDeniedDocs(DocBoxDto dto) {
		return dao.getDeniedDocs(dto);
	}

	@Override
	public List<DocBoxDto> getMyTurnDocs(DocBoxDto dto) {
		return dao.getMyTurnDocs(dto);
	}

	@Override
	public List<DocBoxDto> getChamjoDocs(Map<String, String> inMap) {
		return dao.getChamjoDocs(inMap);
	}

	@Override
	public DocBoxDto getDetailDocs(DocBoxDto dto) {
		return dao.getDetailDocs(dto);
	}
	
	@Transactional(readOnly = true)
	@Override
	public boolean approve(DocBoxDto dto){
		int n = dao.approveJson(dto);
		int m = dao.approveDoc(dto);
		return (n+m)>1?true:false;
	}

	@Override
	public int denyDoc(DocBoxDto dto) {
		return dao.denyDoc(dto);
	}

	@Transactional(readOnly = true)
	@Override
	public boolean finalApprove(DocBoxDto dto) {
		int n = dao.finalJsonApprove(dto);
		int m = dao.finalDocApprove(dto);
		return (n+m)>1?true:false;
	}
}	
