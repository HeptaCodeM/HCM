package com.hcm.grw.model.service.sm;

import com.hcm.grw.dto.sm.ScbDto;

public interface IScbService {

	
	/* 일정조회 */
	
	
	/* 일정등록 */
	public int insertScbo(ScbDto dto);
	/* 일정 수정 */
	public int updateScbo(ScbDto dto);
	/* 일정 삭제 */
	public int updateScboDelFlag(ScbDto dto);
}
