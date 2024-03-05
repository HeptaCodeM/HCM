package com.hcm.grw.model.mapper.sm;

import java.util.List;

import com.hcm.grw.dto.sm.GoboDto;

public interface IGoboDao {

	/* 공지사항 전체조회 */
	public List<GoboDto> getAllGobo();
	/* 공지사항 상세보기 */
	public GoboDto getDetailGobo(String no);
	/* 조회수 업데이트 */
	public int updateGoboView(String no);
	/* 공지사항 글 등록 */
	public int insertGobo(GoboDto dto); 
	/* 게시글 삭제 */
	public int updateGoboDelFlag(String no);
	/* 게시글 수정 */
	public int updateGobo(GoboDto dto);
	
}
