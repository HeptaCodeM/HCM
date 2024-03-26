package com.hcm.grw.model.service.doc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.grw.dto.doc.TempTreeDto;
import com.hcm.grw.dto.hr.CompanyDto;
import com.hcm.grw.model.mapper.doc.ITempTreeDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TempTreeServiceImpl implements ITempTreeService {
	
	@Autowired
	private ITempTreeDao dao;

	@Override
	public List<TempTreeDto> getTempTree() {
		log.info("TempTreeServiceImpl getTempTree 템플릿 전체 트리 목록 불러오기");
		return dao.getTempTree();
	}
	
	@Override
	public CompanyDto getCompInfo() {
		log.info("TempTreeServiceImpl getCompInfo 회사 정보 불러오기");
		return dao.getCompInfo();
	}

}
