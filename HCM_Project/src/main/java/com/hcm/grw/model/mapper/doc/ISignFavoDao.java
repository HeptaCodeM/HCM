package com.hcm.grw.model.mapper.doc;

import java.util.List;

import com.hcm.grw.dto.doc.SignFavoDto;

public interface ISignFavoDao {
	
	public int insertFavAppr(SignFavoDto faDto);

	public int insertFavApprLine(SignFavoDto faDto);

	public SignFavoDto getFavAppr(SignFavoDto faDto);

	public SignFavoDto getFavApprLine(SignFavoDto faDto);

	public int updateFav(SignFavoDto faDto);

	public int deleteFav(String siaf_favo_cd);

	public List<SignFavoDto> getFavApprList(String empl_id);

	public List<SignFavoDto> getFavApprLineList(String empl_id);
	
}
