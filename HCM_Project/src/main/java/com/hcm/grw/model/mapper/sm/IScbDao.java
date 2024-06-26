package com.hcm.grw.model.mapper.sm;

import java.util.List;
import java.util.Map;

import com.hcm.grw.dto.sm.ScbDto;

public interface IScbDao {

	
	
	
	/* 일정조회 */
	public List<ScbDto> getAllCalendar(ScbDto dto);

	/* 일정상세조회 */
	public ScbDto detailScbo(Map<String, Object> map);
	
	/* 일정등록 */
	public int insertScbo(ScbDto dto);
	/* 일정 수정 */
	public int updateScbo(ScbDto dto);
	/* 일정 삭제 */
	public int updateScboDelFlag(ScbDto dto);
}
