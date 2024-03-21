package com.hcm.grw.model.service.hr;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.grw.dto.hr.CommonCodeDto;
import com.hcm.grw.model.mapper.hr.CommonCodeDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommonCodeServiceImpl implements CommonCodeService {

	@Autowired
	private CommonCodeDao dao;

	@Override
	public List<CommonCodeDto> selectAllRole(Map<String, Object> map) {
		log.info("CommonCodeServiceImpl selectAllRole // {}",map);
		return dao.selectAllRole(map);
	}

	@Override
	public int insertRoleOne(Map<String, Object> map) {
		log.info("CommonCodeServiceImpl insertRoleOne // {}",map);
		return dao.insertRoleOne(map);
	}

	@Override
	public int deleteRoleOne(Map<String, Object> map) {
		log.info("CommonCodeServiceImpl deleteRoleOne // {}",map);
		return dao.deleteRoleOne(map);
	}

	@Override
	public CommonCodeDto selectOneRole(Map<String, Object> map) {
		log.info("CommonCodeServiceImpl deleteRoleOne // {}",map);
		return dao.selectOneRole(map);
	}

	@Override
	public int correctionRole(Map<String, Object> map) {
		log.info("CommonCodeServiceImpl correctionRole // {}",map);
		return dao.correctionRole(map);
	}

	@Override
	public int roleCodeDuplicateChk(Map<String, Object> map) {
		log.info("CommonCodeServiceImpl roleCodeDuplicateChk // {}",map);
		return dao.roleCodeDuplicateChk(map);
	}

	@Override
	public int roleNameDuplicateChk(Map<String, Object> map) {
		log.info("CommonCodeServiceImpl roleNameDuplicateChk // {}",map);
		return dao.roleNameDuplicateChk(map);
	}
	
	@Override
	public int delCodeChk(Map<String, Object> map) {
		log.info("CommonCodeServiceImpl delCodeChk // {}",map);
		return dao.delCodeChk(map);
	}
	
}
