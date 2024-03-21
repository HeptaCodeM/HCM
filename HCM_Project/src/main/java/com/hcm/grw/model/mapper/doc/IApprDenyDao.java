package com.hcm.grw.model.mapper.doc;

import com.hcm.grw.dto.doc.SignBoxDto;

public interface IApprDenyDao {
	
		//결재승인
		public int approveJson(SignBoxDto dto);
		
		//승인시 문서 업데이트
		public int approveDoc(SignBoxDto dto);
		
		//반려
		public int denyJson(SignBoxDto dto);
		
		//반려시 문서 업데이트
		public int denyDoc(SignBoxDto dto);
		
		//결재 최종 승인
		public int finalJsonApprove(SignBoxDto dto);
		
		//결재 최종 승인시 문서 업데이트
		public int finalDocApprove(SignBoxDto dto);
		
		//상신 취소 
		public int gianCancel(SignBoxDto dto);
		
}
