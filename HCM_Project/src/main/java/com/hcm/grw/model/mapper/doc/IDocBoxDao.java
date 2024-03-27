package com.hcm.grw.model.mapper.doc;

import java.util.List;
import java.util.Map;

import com.hcm.grw.dto.doc.SignBoxDto;
import com.hcm.grw.dto.doc.SignFileDto;

public interface IDocBoxDao {

	//전체 문서함 조회
	public List<SignBoxDto> getAllDocs(SignBoxDto dto);
	
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
	
	//전체 문서함 테이블만 조회
	public List<SignBoxDto> getAllDocsTable(SignBoxDto dto);
	
	//전체 문서함 Json만 조회
	public List<SignBoxDto> getAllDocsJson(SignBoxDto dto);
	
	//참조 문서함 Json만 조회
	public List<SignBoxDto> getChamjoJson(Map<String, String> inMap);
	
	//상세 조회시 자신의 depth와 max_depth 조회
	public SignBoxDto getMyDepth(SignBoxDto dto);

	//나에게 결재 요청온 문서 Json만 조회
	public List<SignBoxDto> getMyTurnJson(SignBoxDto dto);

	//내가 결재 승인한 문서 조회
	public List<SignBoxDto> getIDidDocs(SignBoxDto dto);
	
	//상세조회문서에 첨부된 파일목록 조회
	public List<SignFileDto> getFileList(SignBoxDto dto);
	
	//상세조회문서에 첨부된 파일다운 조회
	public SignFileDto getDocsDetailFile(String sidf_file_num);

	//임시 문서함 조회
	public List<SignBoxDto> getTempDocs(SignBoxDto dto);
	
	//임시 문서함 삭제
	public int deleteTempDocs(String sitb_doc_num);

	//참조자 이름 조회
	public String findEmployeeName(String id);

	//참조부서명 조회
	public String findDeptName(String dept);
}