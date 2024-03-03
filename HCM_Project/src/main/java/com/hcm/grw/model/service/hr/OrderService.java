package com.hcm.grw.model.service.hr;

import java.util.List;

import com.hcm.grw.dto.hr.OrderInfoDetailDto;
import com.hcm.grw.dto.hr.OrderInfoListDto;

public interface OrderService {

	/*리스트 조회(임직원)*/
	public List<OrderInfoListDto> getOrderList(String empl_id);
	
	/*리스트 조회(관리자)*/
	public List<OrderInfoListDto> getOrderAdminList();
	
	/*발령정보 등록_마스터_상세*/
	public boolean registOrderAdmin(String emor_create_id, OrderInfoDetailDto dto);
	/*정보수정 처리*/
	public int updateOrderAdminDetail(OrderInfoDetailDto dto);
}
