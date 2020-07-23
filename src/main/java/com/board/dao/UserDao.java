package com.board.dao;

import java.util.List;

import com.board.domain.UserVo;

public interface UserDao {
	
	public List<UserVo> getUserList() throws Exception;
	
	public UserVo getUserInfo(String uid) throws Exception;
	//회원가입
	public void insertUser(UserVo userVo) throws Exception;
	
	public int updateUser(UserVo userVo) throws Exception;
	
	public int deleteUser(String uid)throws Exception;
	
	//로그인
	public UserVo login(UserVo userVo) throws Exception;
	
	//아이디 중복체크
	public int idChk(UserVo userVo) throws Exception;
	//이메일 중복체크
	public int emailChk(UserVo userVo) throws Exception;
	
	public int getKey(String uid,String user_key)throws Exception;
	
	public void alter_userKey(String uid)throws Exception;
	


}
