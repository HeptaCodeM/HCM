package com.hcm.grw.model.service.doc;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hcm.grw.dto.doc.SignFavoDto;
import com.hcm.grw.dto.hr.EmployeeDto;
import com.hcm.grw.model.mapper.doc.ISignFavoDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SignFavoServiceImpl implements ISignFavoService {

	@Autowired
	private ISignFavoDao dao;
	
	@Override
	public int insertFavAppr(Map<String, Object> map) {
		log.info("SignFavoServiceImpl insertFavAppr Service 즐겨찾기 결재자 등록");
		return dao.insertFavAppr(map);
	}

	@Override
	public int insertFavApprLine(SignFavoDto faDto) {
		log.info("SignFavoServiceImpl insertFavApprLine Service 즐겨찾기 결재라인 등록");
		return dao.insertFavApprLine(faDto);
	}

	@Override
	public SignFavoDto getFavAppr(String siaf_favo_cd) {
		log.info("SignFavoServiceImpl getFavAppr Service 즐겨찾기 결재자 선택");
		return dao.getFavAppr(siaf_favo_cd);
	}

	@Override
	public SignFavoDto getFavApprLine(String siaf_favo_cd) {
		log.info("SignFavoServiceImpl getFavApprLine Service 즐겨찾기 결재라인 선택");
		return dao.getFavApprLine(siaf_favo_cd);
	}

	@Override
	public int updateFav(SignFavoDto faDto) {
		log.info("SignFavoServiceImpl updateFav Service 즐겨찾기 결재라인 수정");
		return dao.updateFav(faDto);
	}

	@Override
	public int deleteFav(String siaf_favo_cd) {
		log.info("SignFavoServiceImpl deleteFav Service 즐겨찾기 삭제");
		return dao.deleteFav(siaf_favo_cd);
	}

	@Override
	public List<SignFavoDto> getFavApprList(String empl_id) {
		log.info("SignFavoServiceImpl getFavApprList Service 즐겨찾기 결재자 목록 조회");
		return dao.getFavApprList(empl_id);
	}

	@Override
	public List<SignFavoDto> getFavApprLineList(String empl_id) {
		log.info("SignFavoServiceImpl getFavApprLineList Service 즐겨찾기 결재라인 목록 조회");
		return dao.getFavApprLineList(empl_id);
	}
	
	@Override
	public List<EmployeeDto> getFav(@Param("empl_id") List<String> empl_id) {
		log.info("SignFavoServiceImpl getFav Service 결재라인 즐겨찾기 결재자 정보 조회");
		return dao.getFav(empl_id);
	}
	
	@Override
	public SignFavoDto duplicateFav(Map<String, Object> map) {
		log.info("SignFavoServiceImpl duplicateFav Service 즐겨찾기 중복 검사");
		return dao.duplicateFav(map);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED , rollbackFor = Exception.class)
	public void insert(Map<String, Object> map) {
		log.info("SignFavoServiceImpl insert Service 즐겨찾기 등록 트랜잭션 동작");
		SignFavoDto dto = dao.duplicateFav(map);
		if(dto != null) {
			return;
		}
		dao.insertFavAppr(map);
	}

}
