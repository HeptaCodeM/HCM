package com.hcm.grw.model.service.hr;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hcm.grw.dto.hr.EmpSignDto;
import com.hcm.grw.model.mapper.hr.EmpSignDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmpSignServiceImpl implements EmpSignService {

	@Autowired
	private EmpSignDao dao;

	@Override
	public List<EmpSignDto> selectAllSign(Map<String, Object> map) {
		log.info("EmpSignServiceImpl selectAllSign // {}",map);
		return dao.selectAllSign(map);
	}

	@Override
	public int insertSign(Map<String, Object> map) {
		log.info("EmpSignServiceImpl insertSign // {}",map);
		return dao.insertSign(map);
	}
	
	@Override
	public int setDefaultSign(Map<String, Object> map) {
		log.info("EmpSignServiceImpl insertSign // {}",map);
		return dao.setDefaultSign(map);
	}

	@Override
	public int deleteSign(Map<String, Object> map) {
		log.info("EmpSignServiceImpl deleteSign // {}",map);
		return dao.deleteSign(map);
	}

	@Override
	public List<EmpSignDto> defaultChk(Map<String, Object> map) {
		log.info("EmpSignServiceImpl defaultChk // {}",map);
		return dao.defaultChk(map);
	}

	@Override
	public int setUnDefaultSign(Map<String, Object> map) {
		log.info("EmpSignServiceImpl setUnDefaultSign // {}",map);
		return dao.setUnDefaultSign(map);
	}
	
	@Override
	public int setAllDefaultSign(Map<String, Object> map) {
		log.info("EmpSignServiceImpl setAllDefaultSign // {}",map);
		return dao.setAllDefaultSign(map);
	}
	
	@Override
	public int unSetDefaultSign(Map<String, Object> map) {
		log.info("EmpSignServiceImpl unSetDefaultSign Service 기본서명 해제");
		return dao.unSetDefaultSign(map);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED ,rollbackFor = Exception.class)
	public int defaultSignTransaction(Map<String, Object> map) {
		log.info("EmpSignServiceImpl defaultSignTransaction Service 트랜잭션 처리");
		int n1 = dao.unSetDefaultSign(map);
		int n2 = dao.setDefaultSign(map);
		return n1 + n2 > 1 ? 0 : 2;
	}
}
