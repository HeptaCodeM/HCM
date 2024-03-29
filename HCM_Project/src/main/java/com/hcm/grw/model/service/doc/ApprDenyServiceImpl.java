package com.hcm.grw.model.service.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcm.grw.dto.doc.SignBoxDto;
import com.hcm.grw.model.mapper.doc.IApprDenyDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApprDenyServiceImpl implements IApprDenyService {

	@Autowired
	private IApprDenyDao dao;
	
	@Transactional(readOnly = true)
	@Override
	public boolean approve(SignBoxDto dto){
		int n = dao.approveJson(dto);
		int m = dao.approveDoc(dto);
		return (n+m)>1?true:false;
	}

	@Transactional
	@Override
	public boolean deny(SignBoxDto dto) {
		int a = dao.denyJson(dto);
		int b = dao.denyDoc(dto); 
		return (a+b)>1?true:false;
	}


	@Transactional(readOnly = true)
	@Override
	public boolean finalApprove(SignBoxDto dto) {
		int n = dao.approveJson(dto);
		int m = dao.finalDocApprove(dto);
		return (n+m)>1?true:false;
	}

	@Override
	public int gianCancel(SignBoxDto dto) {
		int n = dao.gianCancel(dto);
		return n;
	}
}
