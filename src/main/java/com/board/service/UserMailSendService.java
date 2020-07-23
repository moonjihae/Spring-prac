package com.board.service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.board.dao.UserDaoImp;

@Service
public class UserMailSendService {
	
	private JavaMailSender mailSender;
	private MimeMessage message;
	private MimeMessageHelper messageHelper;


	
	public UserMailSendService(JavaMailSender mailSender) throws MessagingException {
		this.mailSender = mailSender;
		message = this.mailSender.createMimeMessage();
		messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	}

	
	public void setSubject(String subject)throws MessagingException{
		messageHelper.setSubject(subject);
	}
	
	public void setText(String htmlContent)throws MessagingException{
		messageHelper.setText(htmlContent,true);
	}
	
	public void setFrom(String email,String name)throws UnsupportedEncodingException, MessagingException{
		messageHelper.setFrom(email,name);
	}
	
	public void setTo(String email) throws MessagingException{
		messageHelper.setTo(email);
	}
	
	public void addInline(String contentId, DataSource dataSource)throws MessagingException{
		messageHelper.addInline(contentId, dataSource);
	}
	
	public void send() {
		mailSender.send(message);
	}



}

