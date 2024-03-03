package com.hcm.grw.model.service.hr;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.grw.dto.hr.CommonCodeDto;
import com.hcm.grw.model.mapper.hr.ICommonCodeDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommonCodeServiceImpl implements ICommonCodeService {

	@Autowired
	private ICommonCodeDao dao;
	
	@Override
	public List<CommonCodeDto> selectAllDept() {
		log.info("CommonCodeServiceImpl selectAllDept ");
		return dao.selectAllDept();
	}
	
	@Override
	public int insertDeptOne(Map<String, Object> map) {
		log.info("CommonCodeServiceImpl selectAllDept // {}",map);
		return dao.insertDeptOne(map);
	}
	
	@Override
	public int deleteDeptOne(Map<String, Object> map) {
		log.info("CommonCodeServiceImpl deleteDeptOne // {}",map);
		return dao.deleteDeptOne(map);
	}
	
	@Override
	public CommonCodeDto selectOneDept(Map<String, Object> map) {
		log.info("CommonCodeServiceImpl selectOneDept // {}",map);
		return dao.selectOneDept(map);
	}
	
	@Override
	public int correctionDept(Map<String, Object> map) {
		log.info("CommonCodeServiceImpl correctionDept // {}",map);
		return dao.correctionDept(map);
	}
	
	
	@Override
	public List<CommonCodeDto> selectAllRank() {
		log.info("CommonCodeServiceImpl selectAllRank");
		return dao.selectAllRank();
	}

	@Override
	public int insertRankOne(Map<String, Object> map) {
		log.info("CommonCodeServiceImpl insertRankOne // {}",map);
		return dao.insertRankOne(map);
	}

	@Override
	public int deleteRankOne(Map<String, Object> map) {
		log.info("CommonCodeServiceImpl deleteRankOne // {}",map);
		return dao.deleteRankOne(map);
	}

	@Override
	public CommonCodeDto selectOneRank(Map<String, Object> map) {
		log.info("CommonCodeServiceImpl selectOneRank // {}",map);
		return dao.selectOneRank(map);
	}

	@Override
	public int correctionRank(Map<String, Object> map) {
		log.info("CommonCodeServiceImpl correctionRank // {}",map);
		return dao.correctionRank(map);
	}

	@Override
	public List<CommonCodeDto> selectAllPosition() {
		log.info("CommonCodeServiceImpl selectAllPosition");
		return dao.selectAllPosition();
	}

	@Override
	public int insertPositionOne(Map<String, Object> map) {
		log.info("CommonCodeServiceImpl insertPositionOne // {}",map);
		return dao.insertPositionOne(map);
	}

	@Override
	public int deletePositionOne(Map<String, Object> map) {
		log.info("CommonCodeServiceImpl deletePositionOne // {}",map);
		return dao.deletePositionOne(map);
	}

	@Override
	public CommonCodeDto selectOnePosition(Map<String, Object> map) {
		log.info("CommonCodeServiceImpl selectOnePosition // {}",map);
		return dao.selectOnePosition(map);
	}

	@Override
	public int correctionPosition(Map<String, Object> map) {
		log.info("CommonCodeServiceImpl correctionPosition // {}",map);
		return dao.correctionPosition(map);
	}
	
	
}
