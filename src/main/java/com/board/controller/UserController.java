package com.board.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.BoardVo;
import com.board.domain.UserVo;
import com.board.service.UserMailSendService;
import com.board.service.UserService;

@Controller
//@RequestMapping(value="/user/*")
public class UserController {
	@Inject
	private UserService service;
	@Autowired
	private UserMailSendService mailsender;
	
	@Inject
	BCryptPasswordEncoder pwdEncoder;
	//회원조회
	@RequestMapping(value="/user/userList",method=RequestMethod.GET)
	public void getUserList(Model model) throws Exception{
		List<UserVo> list=null;
		list=service.getUserList();
	
		model.addAttribute("list",list);

	}
	//회원가입
	@RequestMapping(value="/user/signUpForm",method=RequestMethod.GET)
	public void signUp() throws Exception{
		
	}
	
	@RequestMapping(value="/user/signUpForm",method=RequestMethod.POST)
	public String insertUser(UserVo userVo,RedirectAttributes rttr) throws Exception{
//		  
		 int result=service.idChk(userVo);
		if(result==1) {
			return "/user/signUpForm";
		}else if(result==0) {
			 String inputPass=userVo.getPwd();
		     String pwd=pwdEncoder.encode(inputPass);
			 userVo.setPwd(pwd);
			 service.insertUser(userVo);
			     
			 rttr.addFlashAttribute("msg","regSueccess");
		}
		
	
		return "redirect:/";
	}
	
	//이메일 인증 코드 검증
	@RequestMapping(value="user/emailConfirm", method=RequestMethod.GET)
	public String emailConfirm(String uid,Model model,RedirectAttributes rttr)throws Exception{
		
		service.alter_userKey(uid);
		model.addAttribute("uid",uid);
		return "/user/emailConfirm";
		
		
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(UserVo userVo, HttpServletRequest req,RedirectAttributes rttr) throws Exception{
		HttpSession session=req.getSession();

		UserVo login=service.login(userVo);
		boolean pwdMatch;
	
		if(login!=null) {
			pwdMatch=pwdEncoder.matches(userVo.getPwd(), login.getPwd());
			
		}else {
			pwdMatch=false;
		}
		if(login!=null&&pwdMatch==true) {
			
			session.setAttribute("user", login);
			
		}else {
			session.setAttribute("user", null);
			rttr.addFlashAttribute("msg",false);
		}
		return "redirect:/";
		
	}
	//회원 정보 수정
//	@RequestMapping(value="user/update",method=RequestMethod.POST)

	
	
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		session.invalidate();
		
		return "redirect:/";
	}
	
	//아이디 중복 체크
	@ResponseBody
	@RequestMapping(value="/user/idChk",method=RequestMethod.POST)
	public int idChk(UserVo userVo)throws Exception{
		int result=service.idChk(userVo);
		return result;
	}
	
	//이메일 중복체크
	@ResponseBody
	@RequestMapping(value="/user/emailChk",method=RequestMethod.POST)
	public int emailChk(UserVo userVo) throws Exception{
		int result=service.emailChk(userVo);
		return result;
	}
	

}
