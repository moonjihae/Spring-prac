package com.board.service;

import java.util.List;

import com.board.domain.ReplyVo;

public interface ReplyService {
List<ReplyVo> list(int rno) throws Exception;
	
	void create(ReplyVo replyVo) throws Exception;
	
	void update(ReplyVo replyVo) throws Exception;
	
	void delete(ReplyVo replyVo) throws Exception;

}
