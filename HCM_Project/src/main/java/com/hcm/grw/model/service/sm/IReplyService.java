package com.hcm.grw.model.service.sm;

import java.util.List;

import com.hcm.grw.dto.sm.ReplyDto;

public interface IReplyService {

	
	/* 댓글 전체조회 */
	public List<ReplyDto> getAllReply(String no);
	/* 댓글등록 */
	public int insertReply(ReplyDto dto);
	/*대댓글등록*/
	public int insertReplyTwo(ReplyDto dto);
	/* 댓글수정 */
	public int updateReply(ReplyDto dto);
	/* 댓글 삭제 */
	public int updateReplyDelFlag(String no);
}
