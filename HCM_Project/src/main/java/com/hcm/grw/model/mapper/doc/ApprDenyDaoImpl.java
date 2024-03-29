package com.hcm.grw.model.mapper.doc;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.doc.SignBoxDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ApprDenyDaoImpl implements IApprDenyDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private String NS = "com.hcm.grw.model.mapper.doc.ApprDenyDaoImpl.";
	
	@Override
	public int approveJson(SignBoxDto dto) {
		return sqlSessionTemplate.update(NS+"approveJson", dto);
	}


	@Override
	public int approveDoc(SignBoxDto dto) {
		return sqlSessionTemplate.update(NS+"approveDoc", dto);
	}

	@Override
	public int denyJson(SignBoxDto dto) {
		return sqlSessionTemplate.update(NS+"denyJson", dto);
	}

	@Override
	public int denyDoc(SignBoxDto dto) {
		return sqlSessionTemplate.update(NS+"denyDoc", dto);
	}


	@Override
	public int finalJsonApprove(SignBoxDto dto) {
		return sqlSessionTemplate.update(NS+"finalJsonApprove", dto);
	}


	@Override
	public int finalDocApprove(SignBoxDto dto) {
		return sqlSessionTemplate.update(NS+"finalDocApprove", dto);
	}

	@Override
	public int gianCancel(SignBoxDto dto) {
		return sqlSessionTemplate.update(NS+"gianCancel", dto);
	}

}
