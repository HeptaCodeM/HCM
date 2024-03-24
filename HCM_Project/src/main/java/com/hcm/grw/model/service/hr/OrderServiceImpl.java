package com.hcm.grw.model.service.hr;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcm.grw.dto.hr.OrderInfoAdminDto;
import com.hcm.grw.dto.hr.OrderInfoDetailDto;
import com.hcm.grw.dto.hr.OrderInfoListDto;
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
	public List<OrderInfoListDto> selectOrderAdminDetail(String emod_id){
		return dao.selectOrderAdminDetail(emod_id);
	}
	
	@Override
	@Transactional
	public boolean registOrderAdmin(OrderInfoAdminDto orderInfoDto) {
		try {
			int n = dao.registOrderAdminMaster(orderInfoDto);
			String emor_id = orderInfoDto.getEmor_id();
			String emor_create_id = orderInfoDto.getEmor_create_id();
			log.info("발령키값 : {}", emor_id);

			List<OrderInfoDetailDto> detailListDto = orderInfoDto.getListDetailDto();
			int m = 0;
			for(OrderInfoDetailDto detailDto : detailListDto) {
				detailDto.setEmor_id(emor_id);
				detailDto.setEmod_create_id(emor_create_id);
				log.info("상세값 : {}", detailDto);
				m += dao.registOrderAdminDetail(detailDto);
			}

			if (n > 0 && m > 0) {
				return true;
			} else {
				throw new RuntimeException("등록에 실패하였습니다.");
			}
		} catch (Exception e) {
			// 등록 중 오류 발생 시 롤백 처리
			throw new RuntimeException("등록에 실패하였습니다.", e);
		}
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

	@Override
	public int deleteOrderAdmin(OrderInfoDetailDto dto) {
		int n = dao.deleteOrderAdminDetail(dto);
		int m = dao.deleteOrderAdmin(dto);
		return (n>0 && m>0)?1:0;
	}

	@Override
	public int deleteOrderAdminDetail(OrderInfoDetailDto dto) {
		return dao.deleteOrderAdminDetail(dto);
	}

	@Override
	public int confirmOrderAdmin(OrderInfoAdminDto dto) {
		return dao.confirmOrderAdmin(dto);
	}

}
