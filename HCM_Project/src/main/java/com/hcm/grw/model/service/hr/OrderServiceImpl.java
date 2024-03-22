package com.hcm.grw.model.service.hr;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcm.grw.dto.hr.OrderInfoDetailDto;
import com.hcm.grw.dto.hr.OrderInfoListDto;
import com.hcm.grw.dto.hr.OrderInfoMasterDto;
import com.hcm.grw.model.mapper.hr.OrderDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao dao;
	
	@Override
	public List<OrderInfoListDto> getOrderList(String empl_id) {
		return dao.getOrderList(empl_id);
	}

	@Override
	public List<OrderInfoListDto> getOrderAdminList(Map<String, Object> orderSearchMap) {
		return dao.getOrderAdminList(orderSearchMap);
	}

	
	@Override
	@Transactional
	public boolean registOrderAdmin(OrderInfoMasterDto dtoMaster, List<OrderInfoDetailDto> dtoDetails) {
		int n = dao.registOrderAdminMaster(dtoMaster);
		String emor_id = dtoMaster.getEmor_id();
		String emor_create_id = dtoMaster.getEmor_create_id();
		log.info("발령키값 : {}", emor_id);
		int m = 0;
		for(OrderInfoDetailDto dtoDetail : dtoDetails) {
			dtoDetail.setEmor_id(emor_id);
			dtoDetail.setEmod_create_id(emor_create_id);
			log.info("상세값 : {}", dtoDetail);
			m += dao.registOrderAdminDetail(dtoDetail);
		}
		
		return (n>0 && m>0)?true:false;
	}


	@Override
	public boolean updateOrderAdminDetail(List<OrderInfoDetailDto> dtoDetails) {
		int m = 0;
		for(OrderInfoDetailDto dtoDetail : dtoDetails) {
			log.info("상세값 : {}", dtoDetail);
			m += dao.updateOrderAdminDetail(dtoDetail);
		}
		
		return (m>0)?true:false;
	}

}
