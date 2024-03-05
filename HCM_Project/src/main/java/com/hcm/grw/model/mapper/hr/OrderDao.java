package com.hcm.grw.model.mapper.hr;

import java.util.List;

import com.hcm.grw.dto.hr.OrderInfoDetailDto;
import com.hcm.grw.dto.hr.OrderInfoListDto;
import com.hcm.grw.dto.hr.OrderInfoMasterDto;

public interface OrderDao {

	/*리스트 조회(임직원)*/
	public List<OrderInfoListDto> getOrderList(String empl_id);
	
	/*리스트 조회(관리자)*/
	public List<OrderInfoListDto> getOrderAdminList();
	
	/*발령정보 등록_마스터*/
	public int registOrderAdminMaster(OrderInfoMasterDto dto);
	/*발령정보 등록_상세*/
	public int registOrderAdminDetail(OrderInfoDetailDto dto);
	/*정보수정 처리*/
	public int updateOrderAdminDetail(OrderInfoDetailDto dto);
}
