package com.hcm.grw.model.service.doc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.grw.dto.doc.SignTreeDto;
import com.hcm.grw.model.mapper.doc.ISignTreeDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SignTreeServiceImpl implements ISignTreeService {

	@Autowired
	private ISignTreeDao dao;
	
	@Override
	public List<SignTreeDto> getSignTree() {
		log.info("SignTreeServiceImpl getSignTree Service 전체 트리 목록 불러오기");
		return dao.getSignTree();
	}

	@Override
	public int insertTree(SignTreeDto treeDto) {
		log.info("SignTreeServiceImpl insertTree Service 사원 추가 트랜잭션 동작");
		return dao.insertTree(treeDto);
	}

	@Override
	public int updateTree(SignTreeDto treeDto) {
		log.info("SignTreeServiceImpl updateTree Service 사원 정보 수정 트랜잭션 동작");
		return dao.updateTree(treeDto);
	}

}
