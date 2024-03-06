package com.hcm.grw.model.mapper.hr;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.hr.CommuteDto;

@Repository
public class CommuteDaoImpl implements CommuteDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	private String NS = "com.hcm.grw.model.mapper.hr.CommuteDaoImpl.";
	
	@Override
	public int registCommute(CommuteDto dto) {
		return sqlSessionTemplate.insert(NS+"registCommute", dto);
	}

	@Override
	public int updateCommute(CommuteDto dto) {
		return sqlSessionTemplate.insert(NS+"updateCommute", dto);
	}

	@Override
	public List<CommuteDto> commuteList(Map<String, String> map) {
		return sqlSessionTemplate.selectList(NS+"commuteList", map);
	}

}
