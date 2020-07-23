package com.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.ReplyVo;

@Repository
public class ReplyDaoImp implements ReplyDao {
	
	private static String namespace="com.board.mappers.reply";
	
	private SqlSession sql;
	
	@Inject
	public ReplyDaoImp(SqlSession sql) {
		this.sql=sql;
	}
	
	@Override
	public List<ReplyVo> list(int rno) throws Exception {
		
		return sql.selectList(namespace+".list",rno);
	}

	@Override
	public void create(ReplyVo replyVo) throws Exception {
		sql.insert(namespace+".create",replyVo);
		
	}

	@Override
	public void update(ReplyVo replyVo) throws Exception {
		sql.update(namespace+".update",replyVo);
		
	}

	@Override
	public void delete(ReplyVo replyVo) throws Exception {
		sql.delete(namespace+".delete",replyVo);
		
	}

}
