package com.hcm.grw.model.service.hr;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.grw.dto.hr.CommuteDto;
import com.hcm.grw.model.mapper.hr.CommuteDao;

@Service
public class CommuteServiceImpl implements CommuteService {

	@Autowired
	private CommuteDao dao;
	
	@Override
	public int registCommute(String empl_id) {
		return dao.registCommute(empl_id);
	}

	@Override
	public int updateCommute(String emco_seq) {
		return dao.updateCommute(emco_seq);
	}

	@Override
	public List<CommuteDto> commuteList(Map<String, String> map) {
		return dao.commuteList(map);
	}

}
