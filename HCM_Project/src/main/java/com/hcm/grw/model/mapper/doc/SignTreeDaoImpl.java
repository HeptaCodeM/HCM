package com.hcm.grw.model.mapper.doc;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.doc.SignTreeDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class SignTreeDaoImpl implements ISignTreeDao {

	@Autowired
	private SqlSessionTemplate sessionTemplate;
	private final String NS = "com.hcm.grw.model.mapper.SignTreeDaoImpl.";
	
	@Override
	public List<SignTreeDto> getSignTree() {
		log.info("SignTreeDaoImpl getSignTree DAO Access");
		return sessionTemplate.selectList(NS + "getSignTree");
	}

	@Override
	public int insertTree(SignTreeDto treeDto) {
		log.info("SignTreeDaoImpl insertTree DAO Access");
		return sessionTemplate.insert(NS + "insertTree", treeDto);
	}

	@Override
	public int updateTree(SignTreeDto treeDto) {
		log.info("SignTreeDaoImpl updateTree DAO Access");
		return sessionTemplate.update(NS + "updateTree", treeDto);
	}

}
