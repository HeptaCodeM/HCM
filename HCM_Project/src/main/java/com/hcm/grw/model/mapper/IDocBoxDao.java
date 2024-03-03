package com.hcm.grw.model.mapper;

import java.util.List;
import java.util.Map;

import com.hcm.grw.dto.DocBoxDto;

public interface IDocBoxDao {

	//전체 문서함 조회
	public List<DocBoxDto> getAllDocs(Map<String, String> inMap);
	
	//기안중인 문서 조회
	public List<DocBoxDto> getMyGian(DocBoxDto dto);
	
	//진행중인 문서 조회
	public List<DocBoxDto>  getIngDocs(DocBoxDto dto);
	
	//승인된 문서 조회
	public List<DocBoxDto>  getApprovedDocs(DocBoxDto dto);
	
	//반려된 문서 조회
	public List<DocBoxDto>  getDeniedDocs(DocBoxDto dto);
	
	//나에게 결재 요청된 문서 조회
	public List<DocBoxDto>  getMyTurnDocs(DocBoxDto dto);
	
	//참조자로 지정된 문서 조회
	public List<DocBoxDto>  getChamjoDocs(Map<String, String> inMap);
	
	//결재문서 상세 조회
	public DocBoxDto getDetailDocs(DocBoxDto dto);
	
	//결재승인
	public int approveJson(DocBoxDto dto);
	
	//승인시 문서 업데이트
	public int approveDoc(DocBoxDto dto);
	
	//반려시 문서 업데이트
	public int denyDoc(DocBoxDto dto);
	
	//결재 최종 승인
	public int finalJsonApprove(DocBoxDto dto);
	
	//결재 최종 승인시 문서 업데이트
	public int finalDocApprove(DocBoxDto dto);
	
}
