package com.hcm.grw.model.service.doc;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hcm.grw.dto.doc.SignFavoDto;
import com.hcm.grw.dto.hr.EmployeeDto;

public interface ISignFavoService {

	public int insertFavAppr(Map<String, Object> map);

	public int insertFavApprLine(SignFavoDto faDto);

	public SignFavoDto getFavAppr(String siaf_favo_cd);

	public SignFavoDto getFavApprLine(String siaf_favo_cd);

	public int updateFav(SignFavoDto faDto);

	public int deleteFav(String siaf_favo_cd);

	public List<SignFavoDto> getFavApprList(String empl_id);

	public List<SignFavoDto> getFavApprLineList(String empl_id);
	
	public List<EmployeeDto> getFav(@Param("empl_id") List<String> empl_id);
	
	public SignFavoDto duplicateFav(Map<String, Object> map);
	
	public void insert(Map<String, Object> map);
	
}
