package com.board.service;

import java.util.List;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.dao.UserDao;
import com.board.domain.UserVo;

@Service
@Transactional
public class UserServiceImp implements UserService {
	@Inject
	private UserDao dao;
	@Inject
	private JavaMailSender mailSender;


	//내 정보 보기
	@Override
	public List<UserVo> getUserList() throws Exception {
		return dao.getUserList();
	}
	//회원정보 조회
	@Override
	public UserVo getUserInfo(String uid) throws Exception {
		return dao.getUserInfo(uid);
	}
	//회원가입
	@Override
	public void insertUser(UserVo userVo) throws Exception {
		dao.insertUser(userVo);
		TempKey tempKey=new TempKey();
		String user_key=tempKey.getKey(false, 50);//인증키 생성
		dao.getKey(userVo.getUid(),user_key);
		UserMailSendService sendMail=new UserMailSendService(mailSender);
		sendMail.setSubject("[회원가입 이메일 인증]");
		sendMail.setText(
				new StringBuffer().append("<h1>[이메일 인증]</h1>")
                .append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
				.append("<a href=localhost:8080/user/emailConfirm?uid=")		
				.append(userVo.getUid())
				.append("&key=")
				.append(user_key)
				.append("'target='_blenk'> 이메일 인증 확인 </a>")
				.toString());
		sendMail.setFrom("dhfptwlql@gmail.com", "니포내포");//보낸이
		sendMail.setTo(userVo.getEmail());//받는이
		sendMail.send();
	

	}
	//회원 정보 수정
	@Override
	public void updateUser(UserVo userVo) throws Exception {
		dao.updateUser(userVo);
	}
	//회원 탈퇴
	@Override
	public void deleteUser(String uid) throws Exception {
		dao.deleteUser(uid);

	}
	//로그인
	@Override
	public UserVo login(UserVo userVo) throws Exception {
		return dao.login(userVo);
	}
	//아이디중복체크
	@Override
	public int idChk(UserVo userVo) throws Exception {
		int result=dao.idChk(userVo); 
		return result;
	}
	//이메일 중복체크
	@Override
	public int emailChk(UserVo userVo) throws Exception {
		int result=dao.emailChk(userVo);
		return result;
	}
	@Override
	public void alter_userKey(String uid) throws Exception {
	
		dao.alter_userKey(uid);
		
	}
	

}
