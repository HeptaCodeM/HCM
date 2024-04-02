package com.hcm.grw.model.service.doc;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcm.grw.dto.doc.SignBoxDto;
import com.hcm.grw.dto.doc.SignFileDto;
import com.hcm.grw.dto.doc.SignTempBoxDto;
import com.hcm.grw.model.mapper.doc.IDocBoxDao;
import com.hcm.grw.model.mapper.doc.ISignBoxDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SignBoxServiceImpl implements ISignBoxService {

	@Autowired
	private ISignBoxDao dao;
	@Autowired
	private IDocBoxDao bDao;
	
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
	
	@Override
	public List<SignFileDto> getFile() {
		log.info("SignBoxServiceImpl getFile Service 파일목록 조회");
		return dao.getFile();
	}
	
	@Override
	public SignFileDto getDetailFile(String sidf_file_num) {
		log.info("SignBoxServiceImpl getdetailFile Service 파일 조회");
		return dao.getDetailFile(sidf_file_num);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insertTempTransaction(SignBoxDto bDto, SignFileDto fDto, String sitb_doc_num) {
		log.info("SignBoxServiceImpl insertTempTransaction Service 임시보관문서 불러와서 작성하는 트랜잭션");
		int n1 = dao.insertDoc(bDto);
		int n2 = dao.insertDocFile(fDto);
		int n3 = bDao.deleteTempDocs(sitb_doc_num);
		return n1 + n2 + n3 == 3 ? 1 : 0;
	}
	
	@Override
	public int insertTempNoFileTransaction(SignBoxDto bDto, String sitb_doc_num) {
		log.info("SignBoxServiceImpl insertTempNoFileTransaction Service 임시보관문서 파일없이 기안 작성하는 트랜잭션");
		int n1 = dao.insertDoc(bDto);
		int n2 = bDao.deleteTempDocs(sitb_doc_num);
		return n1 + n2 == 2 ? 1 : 0;
	}
	
	@Override
	public List<SignBoxDto> selectNumber(String empl_id) {
		log.info("SignBoxServiceImpl selectNumber Service 기안한 전체문서 번호 조회");
		return dao.selectNumber(empl_id);
	}
	
	@Override
	public String duplicateDate(Map<String, Object> map) {
		log.info("SignBoxServiceImpl duplicateDate Service 시작일 종료일 중복 검사");
		return dao.duplicateDate(map);
	}
	
}