package com.hcm.grw.model.mapper.doc;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcm.grw.dto.doc.SignFavoDto;
import com.hcm.grw.dto.hr.EmployeeDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class SignFavoDaoImpl implements ISignFavoDao {
	
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	private final String NS = "com.hcm.grw.model.mapper.SignFavoDaoImpl.";

	@Override
	public int insertFavAppr(Map<String, Object> map) {
		log.info("SignFavoDaoImpl insertFavAppr DAO Access");
		return sessionTemplate.insert(NS + "insertFavAppr", map);
	}

	@Override
	public int insertFavApprLine(SignFavoDto faDto) {
		log.info("SignFavoDaoImpl insertFavApprLine DAO Access");
		return sessionTemplate.insert(NS + "insertFavApprLine", faDto);
	}

	@Override
	public SignFavoDto getFavAppr(String siaf_favo_cd) {
		log.info("SignFavoDaoImpl getFavAppr DAO Access");
		return sessionTemplate.selectOne(NS + "getFavAppr", siaf_favo_cd);
	}

	@Override
	public SignFavoDto getFavApprLine(String siaf_favo_cd) {
		log.info("SignFavoDaoImpl getFavApprLine DAO Access");
		return sessionTemplate.selectOne(NS + "getFavApprLine", siaf_favo_cd);
	}

	@Override
	public int updateFav(SignFavoDto faDto) {
		log.info("SignFavoDaoImpl updateFav DAO Access");
		return sessionTemplate.update(NS + "updateFav", faDto);
	}

	@Override
	public int deleteFav(String siaf_favo_cd) {
		log.info("SignFavoDaoImpl deleteFav DAO Access");
		return sessionTemplate.delete(NS + "deleteFav", siaf_favo_cd);
	}

	@Override
	public List<SignFavoDto> getFavApprList(String empl_id) {
		log.info("SignFavoDaoImpl getFavApprList DAO Access");
		return sessionTemplate.selectList(NS + "getFavApprList", empl_id);
	}

	@Override
	public List<SignFavoDto> getFavApprLineList(String empl_id) {
		log.info("SignFavoDaoImpl getFavApprLineList DAO Access");
		return sessionTemplate.selectList(NS + "getFavApprLineList", empl_id);
	}
	
	@Override
	public List<EmployeeDto> getFav(@Param("empl_id") List<String> empl_id) {
		log.info("SignFavoDaoImpl getFav DAO Access");
		return sessionTemplate.selectList(NS + "getFav", empl_id);
	}
	
	@Override
	public SignFavoDto duplicateFav(Map<String, Object> map) {
		log.info("SignFavoDaoImpl duplicateFav DAO Access");
		return sessionTemplate.selectOne(NS + "duplicateFav", map);
	}

}
