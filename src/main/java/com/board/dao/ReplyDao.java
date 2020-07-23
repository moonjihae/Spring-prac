package com.board.dao;

import java.util.List;

import com.board.domain.ReplyVo;

public interface ReplyDao {
	
	List<ReplyVo> list(int rno) throws Exception;
	
	void create(ReplyVo replyVo) throws Exception;
	
	void update(ReplyVo replyVo) throws Exception;
	
	void delete(ReplyVo replyVo) throws Exception;
	

}
