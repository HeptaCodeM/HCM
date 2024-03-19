package com.hcm.grw.model.service.doc;

import java.util.List;
import java.util.Map;

import com.hcm.grw.dto.doc.SignBoxDto;

public interface IDocBoxService {

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
		
		//상세조회 리스트
		public List<SignBoxDto>getDetailDocsList(SignBoxDto dto);
		
		//결재승인 트랜잭션 처리
		public boolean approve(SignBoxDto dto);
		
		//반려시 문서 업데이트
		public int denyDoc(SignBoxDto dto);
		
		
		//최종 결재 승인 트랜잭션 처리
		public boolean finalApprove(SignBoxDto dto);
		
		//문서 조회 테이블+json 동시 트랜잭션
		public boolean getDocs(SignBoxDto dto);
		
		//참조 문서함 Json만 조회
		public List<SignBoxDto> getChamjoJson(Map<String, String> inMap);
		
		//상세 조회시 자신의 depth와 max_depth 조회
		public SignBoxDto getMyDepth(SignBoxDto dto);
		
		//나에게 결재 요청온 문서 Json만 조회
		public List<SignBoxDto> getMyTurnJson(SignBoxDto dto);

		//내가 결재 승인한 문서 조회
		public List<SignBoxDto> getIDidDocs(SignBoxDto dto);
}