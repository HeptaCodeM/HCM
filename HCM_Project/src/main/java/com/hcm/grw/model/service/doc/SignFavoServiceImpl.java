package com.hcm.grw.model.service.doc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.grw.dto.doc.SignFavoDto;
import com.hcm.grw.model.mapper.doc.ISignFavoDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SignFavoServiceImpl implements ISignFavoService {

	@Autowired
	private ISignFavoDao dao;
	
	@Override
	public int insertFavAppr(SignFavoDto faDto) {
		log.info("SignFavoServiceImpl insertFavAppr Service 즐겨찾기 결재자 등록");
		return dao.insertFavAppr(faDto);
	}

	@Override
	public int insertFavApprLine(SignFavoDto faDto) {
		log.info("SignFavoServiceImpl insertFavApprLine Service 즐겨찾기 결재라인 등록");
		return dao.insertFavApprLine(faDto);
	}

	@Override
	public SignFavoDto getFavAppr(SignFavoDto faDto) {
		log.info("SignFavoServiceImpl getFavAppr Service 즐겨찾기 결재자 선택");
		return dao.getFavAppr(faDto);
	}

	@Override
	public SignFavoDto getFavApprLine(SignFavoDto faDto) {
		log.info("SignFavoServiceImpl getFavApprLine Service 즐겨찾기 결재라인 선택");
		return dao.getFavApprLine(faDto);
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

}
