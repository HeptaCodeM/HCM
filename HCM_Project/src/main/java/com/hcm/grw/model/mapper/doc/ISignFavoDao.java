package com.hcm.grw.model.mapper.doc;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hcm.grw.dto.doc.SignFavoDto;
import com.hcm.grw.dto.hr.EmployeeDto;

public interface ISignFavoDao {
	
	public int insertFavAppr(SignFavoDto faDto);

	public int insertFavApprLine(SignFavoDto faDto);

	public SignFavoDto getFavAppr(SignFavoDto faDto);

	public SignFavoDto getFavApprLine(SignFavoDto faDto);

	public int updateFav(SignFavoDto faDto);

	public int deleteFav(String siaf_favo_cd);

	public List<SignFavoDto> getFavApprList(String empl_id);

	public List<SignFavoDto> getFavApprLineList(String empl_id);
	
	public List<EmployeeDto> getFav(@Param("empl_id") List<String> empl_id);
	
}
