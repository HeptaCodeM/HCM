package com.hcm.grw.model.service.doc;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcm.grw.dto.doc.SignBoxDto;
import com.hcm.grw.dto.doc.SignFileDto;
import com.hcm.grw.model.mapper.doc.IDocBoxDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DocBoxServiceImpl implements IDocBoxService {

	@Autowired
	private IDocBoxDao dao;
	
	@Override
	public List<SignBoxDto> getAllDocs(SignBoxDto dto) {
		log.info("DocBoxServiceImpl  전체 문서 조회");
		return dao.getAllDocs(dto);
	}

	@Override
	public List<SignBoxDto> getMyGian(SignBoxDto dto) {
		log.info("DocBoxServiceImpl 기안중인 문서 조회");
		return dao.getMyGian(dto);
	}

	@Override
	public List<SignBoxDto> getIngDocs(SignBoxDto dto) {
		return dao.getIngDocs(dto);
	}

	@Override
	public List<SignBoxDto> getApprovedDocs(SignBoxDto dto) {
		return dao.getApprovedDocs(dto);
	}

	@Override
	public List<SignBoxDto> getDeniedDocs(SignBoxDto dto) {
		return dao.getDeniedDocs(dto);
	}

	@Override
	public List<SignBoxDto> getMyTurnDocs(SignBoxDto dto) {
		return dao.getMyTurnDocs(dto);
	}

	@Override
	public List<SignBoxDto> getChamjoDocs(Map<String, String> inMap) {
		return dao.getChamjoDocs(inMap);
	}

	//상세조회
	@Override
	public SignBoxDto getDetailDocs(SignBoxDto dto) {
		return dao.getDetailDocs(dto);
	}
	
	//상세조회 리스트 버전
	@Override
	public List<SignBoxDto>getDetailDocsList(SignBoxDto dto){
		return dao.getDetailDocsList(dto);
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public boolean approve(SignBoxDto dto){
		int n = dao.approveJson(dto);
		int m = dao.approveDoc(dto);
		return (n+m)>1?true:false;
	}

	@Override
	public int denyDoc(SignBoxDto dto) {
		return dao.denyDoc(dto);
	}

	@Transactional(readOnly = true)
	@Override
	public boolean finalApprove(SignBoxDto dto) {
		int n = dao.finalJsonApprove(dto);
		int m = dao.finalDocApprove(dto);
		return (n+m)>1?true:false;
	}
	
	
	@Override
	public List<SignBoxDto> getChamjoJson(Map<String, String> inMap) {
		return dao.getChamjoJson(inMap);
	}
	
	@Override
	public SignBoxDto getMyDepth(SignBoxDto dto) {
		return dao.getMyDepth(dto);
	}

	@Override
	public List<SignBoxDto> getMyTurnJson(SignBoxDto dto) {
		return dao.getMyTurnJson(dto);
	}
	
	@Override
	public List<SignBoxDto> getIDidDocs(SignBoxDto dto) {
		return dao.getIDidDocs(dto);
	}
	
	@Override
	public List<SignFileDto> getFileList(SignBoxDto dto) {
		return dao.getFileList(dto);
	}
	
	@Override
	public SignFileDto getDocsDetailFile(String sidf_file_num) {
		return dao.getDocsDetailFile(sidf_file_num);
	}
	
	@Override
	public List<SignBoxDto> getTempDocs(SignBoxDto dto) {
		return dao.getTempDocs(dto);
	}
	
	@Override
	public int deleteTempDocs(String sitb_doc_num) {
		return dao.deleteTempDocs(sitb_doc_num);	}
	
	@Override
	public String findEmployeeName(String id) {
		return dao.findEmployeeName(id);
	}
	
	@Override
	public String findDeptName(String dept) {
		return dao.findDeptName(dept);
	}
}	
