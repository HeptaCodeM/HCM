package com.hcm.grw.model.mapper.doc;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.doc.TempTreeDto;
import com.hcm.grw.dto.hr.CompanyDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class TempTreeDaoImpl implements ITempTreeDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS = "com.hcm.grw.model.mapper.doc.TempTreeDaoImpl.";

	@Override
	public List<TempTreeDto> getTempTree() {
		log.info("TempTreeDaoImpl getTempTree DAO Access");
		return session.selectList(NS+"getTempTree");
	}

	@Override
	public CompanyDto getCompInfo() {
		log.info("TempTreeDaoImpl getCompInfo DAO Access");
		return session.selectOne(NS + "getCompInfo");
	}

}
