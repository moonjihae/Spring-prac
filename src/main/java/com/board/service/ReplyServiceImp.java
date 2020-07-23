package com.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.dao.ReplyDao;
import com.board.domain.ReplyVo;

@Service
public class ReplyServiceImp implements ReplyService {
  
    private ReplyDao dao;
    
    @Inject
    public ReplyServiceImp(ReplyDao dao) {
    	this.dao=dao;
    }
	@Override
	public List<ReplyVo> list(int rno) throws Exception {
		
		return dao.list(rno);
	}

	@Override
	public void create(ReplyVo replyVo) throws Exception {
		dao.create(replyVo);

	}

	@Override
	public void update(ReplyVo replyVo) throws Exception {
		dao.update(replyVo);
	}

	@Override
	public void delete(ReplyVo replyVo) throws Exception {
		dao.delete(replyVo);

	}

}
