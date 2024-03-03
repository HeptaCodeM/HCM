package com.hcm.grw.model.service.hr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcm.grw.dto.hr.OrderInfoDetailDto;
import com.hcm.grw.dto.hr.OrderInfoListDto;
import com.hcm.grw.model.mapper.hr.OrderDao;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao dao;
	
	@Override
	public List<OrderInfoListDto> getOrderList(String empl_id) {
		return dao.getOrderList(empl_id);
	}

	@Override
	public List<OrderInfoListDto> getOrderAdminList() {
		return dao.getOrderAdminList();
	}

	
	@Override
	@Transactional
	public boolean registOrderAdmin(String emor_create_id, OrderInfoDetailDto dto) {
		int n = dao.registOrderAdminMaster(emor_create_id);
		int m = dao.registOrderAdminDetail(dto);
		
		return (n>0 && m>0)?true:false;
	}


	@Override
	public int updateOrderAdminDetail(OrderInfoDetailDto dto) {
		return dao.updateOrderAdminDetail(dto);
	}

}
