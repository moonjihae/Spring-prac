package com.board.service;

import java.util.List;

import com.board.domain.UserVo;

public interface UserService {
	public List<UserVo> getUserList() throws Exception;
	
	public UserVo getUserInfo(String uid)throws Exception;
	//회원가입
	public void insertUser(UserVo userVo) throws Exception;
	//회원 정보수정
	public void updateUser(UserVo userVo) throws Exception;
	//탈퇴
	public void deleteUser(String uid) throws Exception;
	//로그인
	public UserVo login(UserVo userVo)throws Exception;
	//아이디 중복체크
	public int idChk(UserVo userVo)throws Exception;
	
	//이메일 중복체크
	public int emailChk(UserVo userVo) throws Exception;
	
	public void alter_userKey(String uid)throws Exception;
}
