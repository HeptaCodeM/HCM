package com.hcm.grw.model.service.hr;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.grw.dto.hr.EmpSignDto;
import com.hcm.grw.model.mapper.hr.IEmpSignDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmpSignServiceImpl implements IEmpSignService {

	@Autowired
	private IEmpSignDao dao;

	@Override
	public List<EmpSignDto> selectAllSign() {
		log.info("EmpSignServiceImpl selectAllSign");
		return dao.selectAllSign();
	}

	@Override
	public int insertSign(Map<String, Object> map) {
		log.info("EmpSignServiceImpl insertSign // {}",map);
		return dao.insertSign(map);
	}

	@Override
	public int deleteSign(Map<String, Object> map) {
		log.info("EmpSignServiceImpl deleteSign // {}",map);
		return dao.deleteSign(map);
	}
	
	
	
	
	
}
