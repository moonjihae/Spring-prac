package com.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.UserVo;

@Repository
public class UserDaoImp implements UserDao {
	
	@Inject
	private SqlSession sql;
	
	private static String namespace="com.board.mappers.user"
			+ "";
	@Override
	public List<UserVo> getUserList() throws Exception {
		
		return sql.selectList(namespace+".getUserList");
	}

	@Override
	public UserVo getUserInfo(String uid) throws Exception {
		
		return sql.selectOne(namespace+".getUserInfo");
	}
	//회원가입
	@Override
	public void insertUser(UserVo userVo) throws Exception {
		 sql.insert(namespace+".insert",userVo);
	}

	@Override
	public int updateUser(UserVo userVo) throws Exception {
		return sql.update(namespace+".update",userVo);
	}

	@Override
	public int deleteUser(String uid) throws Exception {
		return sql.delete(namespace+".delete",uid);
	}

	//로그인
	@Override
	public UserVo login(UserVo userVo) throws Exception {
		return sql.selectOne(namespace+".login",userVo);
	}
	//아이디 중복체크
	@Override
	public int idChk(UserVo userVo) throws Exception {
		int result=sql.selectOne(namespace+".idChk",userVo);
		return result;
	}

	@Override
	public int getKey(String uid, String user_key) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("uid",uid);
		map.put("user_key", user_key);
		int result=sql.update(namespace+".getKey",map);
		return result;
	}

	@Override
	public void alter_userKey(String uid) {
		
		sql.update(namespace+".alter_userKey",uid);
		
	}

	@Override
	public int emailChk(UserVo userVo) throws Exception {
		int result=sql.selectOne(namespace+".emailChk",userVo);
		return result;
	}


	
	

}
