package com.hcm.grw.model.mapper.hr;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.hr.CommonCodeDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class CommonCodeDaoImpl implements ICommonCodeDao {

	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	private final String NS = "com.hcm.grw.model.mapper.CommonCodeDaoImpl.";
	
	
	@Override
	public List<CommonCodeDto> selectAllDept() {
		log.info("CommonCodeImpl selectAllDept 진입");
		return sessionTemplate.selectList(NS+"selectAllDept");
	}
	
	@Override
	public int insertDeptOne(Map<String, Object> map) {
		log.info("CommonCodeImpl insertDeptOne 진입 // {}",map);
		return sessionTemplate.insert(NS+"insertDeptOne",map);
	}
	
	@Override
	public int deleteDeptOne(Map<String, Object> map) {
		log.info("CommonCodeImpl deleteDeptOne 진입 // {}",map);
		return sessionTemplate.update(NS+"deleteDeptOne",map);
	}
	
	@Override
	public CommonCodeDto selectOneDept(Map<String, Object> map) {
		log.info("CommonCodeImpl selectOneDept 진입 // {}",map);
		return sessionTemplate.selectOne(NS+"selectOneDept",map);
	}
	
	@Override
	public int correctionDept(Map<String, Object> map) {
		log.info("CommonCodeImpl correctionDept 진입 // {}",map);
		return sessionTemplate.update(NS+"correctionDept",map);
	}
	
	
	@Override
	public List<CommonCodeDto> selectAllRank() {
		log.info("CommonCodeImpl selectAllRank");
		return sessionTemplate.selectList(NS+"selectAllRank");
	}

	@Override
	public int insertRankOne(Map<String, Object> map) {
		log.info("CommonCodeImpl insertRankOne // {}",map);
		return sessionTemplate.update(NS+"insertRankOne", map);
	}

	@Override
	public int deleteRankOne(Map<String, Object> map) {
		log.info("CommonCodeImpl deleteRankOne // {}",map);
		return sessionTemplate.update(NS+"deleteRankOne",map);
	}

	@Override
	public CommonCodeDto selectOneRank(Map<String, Object> map) {
		log.info("CommonCodeImpl selectOneRank // {}",map);
		return sessionTemplate.selectOne(NS+"selectOneRank",map);
	}

	@Override
	public int correctionRank(Map<String, Object> map) {
		log.info("CommonCodeImpl correctionRank // {}",map);
		return sessionTemplate.update(NS+"correctionRank",map);
	}

	@Override
	public List<CommonCodeDto> selectAllPosition() {
		log.info("CommonCodeImpl selectAllPosition");
		return sessionTemplate.selectList(NS+"selectAllPosition");
	}

	@Override
	public int insertPositionOne(Map<String, Object> map) {
		log.info("CommonCodeImpl insertPositionOne // {}",map);
		return sessionTemplate.insert(NS+"insertPositionOne",map);
	}

	@Override
	public int deletePositionOne(Map<String, Object> map) {
		log.info("CommonCodeImpl deletePositionOne // {}",map);
		return sessionTemplate.update(NS+"deletePositionOne",map);
	}

	@Override
	public CommonCodeDto selectOnePosition(Map<String, Object> map) {
		log.info("CommonCodeImpl selectOnePosition // {}",map);
		return sessionTemplate.selectOne(NS+"selectOnePosition",map);
	}

	@Override
	public int correctionPosition(Map<String, Object> map) {
		log.info("CommonCodeImpl correctionPosition // {}",map);
		return sessionTemplate.update(NS+"correctionPosition",map);
	}
	

}
