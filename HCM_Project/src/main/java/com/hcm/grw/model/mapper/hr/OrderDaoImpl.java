package com.hcm.grw.model.mapper.hr;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.hr.OrderInfoDetailDto;
import com.hcm.grw.dto.hr.OrderInfoListDto;

@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private String NS = "com.hcm.grw.model.mapper.hr.OrderDaoImpl.";
	
	@Override
	public List<OrderInfoListDto> getOrderList(String empl_id) {
		return sqlSessionTemplate.selectList(NS+"getOrderList", empl_id);
	}

	@Override
	public List<OrderInfoListDto> getOrderAdminList() {
		return sqlSessionTemplate.selectList(NS+"getOrderAdminList");
	}

	@Override
	public int registOrderAdminMaster(String emor_create_id) {
		return sqlSessionTemplate.insert(NS+"registOrderAdminMaster", emor_create_id);
	}

	@Override
	public int registOrderAdminDetail(OrderInfoDetailDto dto) {
		return sqlSessionTemplate.insert(NS+"registOrderAdminDetail", dto);
	}

	@Override
	public int updateOrderAdminDetail(OrderInfoDetailDto dto) {
		return sqlSessionTemplate.update(NS+"updateOrderAdminDetail", dto);
	}

}
