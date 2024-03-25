package com.hcm.grw.model.mapper.hr;

import java.util.List;
import java.util.Map;

import com.hcm.grw.dto.hr.OrderInfoAdminDto;
import com.hcm.grw.dto.hr.OrderInfoDetailDto;
import com.hcm.grw.dto.hr.OrderInfoListDto;

public interface OrderDao {

	/*발령리스트 조회(임직원)*/
	public List<OrderInfoListDto> getOrderList(String empl_id);
	
	/*발령리스트 조회(관리자)*/
	public List<OrderInfoListDto> getOrderAdminList(Map<String, Object> orderSearchMap);
	
	/*발령 상세조회(관리자)*/
	public List<OrderInfoListDto> selectOrderAdminDetail(String emod_id);
	
	/*발령정보 등록_마스터*/
	public int registOrderAdminMaster(OrderInfoAdminDto dto);
	/*발령정보 등록_상세*/
	public int registOrderAdminDetail(OrderInfoDetailDto dto);
	/*정보수정 처리*/
	public int updateOrderAdminDetail(OrderInfoDetailDto dto);

	/*발령정보 삭제*/
	public int deleteOrderAdmin(OrderInfoDetailDto dto);
	/*발령정보 상세삭제*/
	public int deleteOrderAdminDetail(OrderInfoDetailDto dto);
	
	/*발령정보 확정*/
	public int confirmOrderAdmin(OrderInfoAdminDto dto);
}
