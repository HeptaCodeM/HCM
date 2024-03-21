package com.hcm.grw.model.service.doc;

import com.hcm.grw.dto.doc.SignBoxDto;

public interface IApprDenyService {
	
	//결재승인 트랜잭션 처리
			public boolean approve(SignBoxDto dto);
			
			//반려시 문서 업데이트
			public boolean deny(SignBoxDto dto);
			
			
			//최종 결재 승인 트랜잭션 처리
			public boolean finalApprove(SignBoxDto dto);
			
			//상신 취소 
			public int gianCancel(SignBoxDto dto);

}
