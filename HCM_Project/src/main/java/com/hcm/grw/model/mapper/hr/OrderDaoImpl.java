package com.hcm.grw.model.mapper.hr;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.hr.OrderInfoAdminDto;
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
	public List<OrderInfoListDto> getOrderAdminList(Map<String, Object> orderSearchMap) {
		return sqlSessionTemplate.selectList(NS+"getOrderAdminList", orderSearchMap);
	}

	public List<OrderInfoListDto> selectOrderAdminDetail(String emod_id){
		return sqlSessionTemplate.selectList(NS+"selectOrderAdminDetail", emod_id);
	}
	
	@Override
	public int registOrderAdminMaster(OrderInfoAdminDto dto) {
		return sqlSessionTemplate.insert(NS+"registOrderAdminMaster", dto);
	}

	@Override
	public int registOrderAdminDetail(OrderInfoDetailDto dto) {
		return sqlSessionTemplate.insert(NS+"registOrderAdminDetail", dto);
	}

	@Override
	public int updateOrderAdminDetail(OrderInfoDetailDto dto) {
		return sqlSessionTemplate.update(NS+"updateOrderAdminDetail", dto);
	}

	@Override
	public int deleteOrderAdmin(OrderInfoDetailDto dto) {
		return sqlSessionTemplate.update(NS+"deleteOrderAdmin", dto);
	}

	@Override
	public int deleteOrderAdminDetail(OrderInfoDetailDto dto) {
		return sqlSessionTemplate.update(NS+"deleteOrderAdminDetail", dto);
	}

	@Override
	public int confirmOrderAdmin(OrderInfoAdminDto dto) {
		return sqlSessionTemplate.update(NS+"confirmOrderAdmin", dto);
	}

}
