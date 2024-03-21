package com.hcm.grw.model.mapper.hr;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.hr.SignDocBoxDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class SignDocBoxDaoImpl implements SignDocBoxDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private String NS = "com.hcm.grw.model.mapper.hr.SignDocBoxDaoImpl.";
	
	
	@Override
	public List<SignDocBoxDto> selectAllDocList(Map<String, Object> map) {
		log.info("SignDocBoxDaoImpl selectAllDocList // {}",map);
		return sqlSessionTemplate.selectList(NS+"selectAllDocList",map);
	}
	
	@Override
	public SignDocBoxDto selectOneDocList(Map<String, Object> map) {
		log.info("SignDocBoxDaoImpl selectOneDocList // {}",map);
		return sqlSessionTemplate.selectOne(NS+"selectOneDocList",map);
	}
	
	@Override
	public int downloadOneDoc(Map<String, Object> map) {
		log.info("SignDocBoxDaoImpl downloadOneDoc // {}",map);
		return sqlSessionTemplate.insert(NS+"downloadOneDoc",map);
	}
	
	@Override
	public List<SignDocBoxDto> selectAllDownloadDocList(Map<String, Object> map) {
		log.info("SignDocBoxDaoImpl selectAllDownloadDocList // {}",map);
		return sqlSessionTemplate.selectList(NS+"selectAllDownloadDocList",map);
	}

}
