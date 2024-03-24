package com.hcm.grw.model.service.hr;

import java.util.List;
import java.util.Map;

import com.hcm.grw.dto.hr.OrderInfoAdminDto;
import com.hcm.grw.dto.hr.OrderInfoDetailDto;
import com.hcm.grw.dto.hr.OrderInfoListDto;

public interface OrderService {

	/*리스트 조회(임직원)*/
	public List<OrderInfoListDto> getOrderList(String empl_id);
	
	/*리스트 조회(관리자)*/
	public List<OrderInfoListDto> getOrderAdminList(Map<String, Object> orderSearchMap);

	/*발령 상세조회(관리자)*/
	public List<OrderInfoListDto> selectOrderAdminDetail(String emod_id);
	
	/*발령정보 등록_마스터_상세*/
	public boolean registOrderAdmin(OrderInfoAdminDto orderInfoDto);
	/*정보수정 처리*/
	public boolean updateOrderAdminDetail(List<OrderInfoDetailDto> dtoDetails);

	/*발령정보 삭제*/
	public int deleteOrderAdmin(OrderInfoDetailDto dto);
	/*발령정보 상세삭제*/
	public int deleteOrderAdminDetail(OrderInfoDetailDto dto);

	/*발령정보 확정*/
	public int confirmOrderAdmin(OrderInfoAdminDto dto);
}
