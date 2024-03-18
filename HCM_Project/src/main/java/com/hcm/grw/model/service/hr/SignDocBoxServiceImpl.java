package com.hcm.grw.model.service.hr;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.grw.dto.hr.SignDocBoxDto;
import com.hcm.grw.model.mapper.hr.SignDocBoxDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SignDocBoxServiceImpl implements SignDocBoxService {

	@Autowired
	private SignDocBoxDao dao;
	
	@Override
	public List<SignDocBoxDto> selectAllDocList(Map<String, Object> map) {
		log.info("SignDocBoxServiceImpl selectAllDocList // {}",map);
		return dao.selectAllDocList(map);
	}

	@Override
	public SignDocBoxDto selectOneDocList(Map<String, Object> map) {
		log.info("SignDocBoxServiceImpl selectOneDocList // {}",map);
		return dao.selectOneDocList(map);
	}

	@Override
	public int downloadOneDoc(Map<String, Object> map) {
		log.info("SignDocBoxServiceImpl downloadOneDoc // {}",map);
		return dao.downloadOneDoc(map);
	}

	@Override
	public List<SignDocBoxDto> selectAllDownloadDocList(Map<String, Object> map) {
		log.info("SignDocBoxServiceImpl selectAllDownloadDocList // {}",map);
		return dao.selectAllDownloadDocList(map);
	}
	
}
