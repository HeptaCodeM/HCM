package com.hcm.grw.model.service.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcm.grw.dto.doc.SignBoxDto;
import com.hcm.grw.dto.doc.SignFileDto;
import com.hcm.grw.dto.doc.SignTempBoxDto;
import com.hcm.grw.model.mapper.doc.ISignBoxDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SignBoxServiceImpl implements ISignBoxService {

	@Autowired
	private ISignBoxDao dao;
	
	@Override
	public int insertDoc(SignBoxDto dto) {
		log.info("SignBoxServiceImpl insertDoc Service 결재문서 작성");
		return dao.insertDoc(dto);
	}

	@Override
	public int insertTempDoc(SignTempBoxDto dto) {
		log.info("SignBoxServiceImpl insertTempDoc Service 결재문서 임시보관함 저장");
		return dao.insertTempDoc(dto);
	}
	
	@Override
	public int insertDocFile(SignFileDto dto) {
		log.info("SignBoxServiceImpl insertDocFile Service 결재문서 파일 업로드");
		return dao.insertDocFile(dto);
	}
	
	@Override
	public int updateDoc(SignBoxDto dto) {
		log.info("SignBoxServiceImpl updateDoc Service 결재문서 수정");
		return dao.updateDoc(dto);
	}
	
	@Override
	public SignTempBoxDto getTempDoc(String sitb_doc_num) {
		log.info("SignBoxServiceImpl getTempDoc Service 보관함 불러오기");
		return dao.getTempDoc(sitb_doc_num);
	}

	@Override
	public int deleteDoc(String sidb_doc_num) {
		log.info("SignBoxServiceImpl deleteDoc Service 결재문서 삭제");
		return dao.deleteDoc(sidb_doc_num);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insertTransaction(SignBoxDto bDto, SignFileDto fDto) {
		log.info("SignBoxServiceImpl insertTransaction Service 결재문서 작성 파일 트랜잭션");
		int n1 = dao.insertDoc(bDto);
		int n2 = dao.insertDocFile(fDto);
		if(n1 + n2 != 2) {
			log.error("문서 작성 실패");
			return 0;
		}
		return 1;
	}
}