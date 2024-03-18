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
	public int registCommute(CommuteDto dto) {
		return dao.registCommute(dto);
	}

	@Override
	public int updateCommute(String empl_id) {
		return dao.updateCommute(empl_id);
	}

	@Override
	public List<CommuteDto> commuteList(Map<String, String> map) {
		return dao.commuteList(map);
	}

	@Override
	public CommuteDto selectCommuteDayInfo(String empl_id) {
		return dao.selectCommuteDayInfo(empl_id);
	}

}
