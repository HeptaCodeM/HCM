package com.hcm.grw.model.mapper.doc;

import java.util.List;
import java.util.Map;

import com.hcm.grw.dto.doc.DocBoxDto;
import com.hcm.grw.dto.doc.SignBoxDto;

public interface IDocBoxDao {

	//전체 문서함 조회
	public List<SignBoxDto> getAllDocs(Map<String, String> inMap);
	
	//기안중인 문서 조회
	public List<SignBoxDto> getMyGian(SignBoxDto dto);
	
	//진행중인 문서 조회
	public List<SignBoxDto>  getIngDocs(SignBoxDto dto);
	
	//승인된 문서 조회
	public List<SignBoxDto>  getApprovedDocs(SignBoxDto dto);
	
	//반려된 문서 조회
	public List<SignBoxDto>  getDeniedDocs(SignBoxDto dto);
	
	//나에게 결재 요청된 문서 조회
	public List<SignBoxDto>  getMyTurnDocs(SignBoxDto dto);
	
	//참조자로 지정된 문서 조회
	public List<SignBoxDto>  getChamjoDocs(Map<String, String> inMap);
	
	//결재문서 상세 조회
	public SignBoxDto getDetailDocs(SignBoxDto dto);
	
	
	//상세조회 리스트버전
	
	public List<SignBoxDto>getDetailDocsList(SignBoxDto dto);
	
	//결재승인
	public int approveJson(SignBoxDto dto);
	
	//승인시 문서 업데이트
	public int approveDoc(SignBoxDto dto);
	
	//반려시 문서 업데이트
	public int denyDoc(SignBoxDto dto);
	
	//결재 최종 승인
	public int finalJsonApprove(SignBoxDto dto);
	
	//결재 최종 승인시 문서 업데이트
	public int finalDocApprove(SignBoxDto dto);
	
}
