<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>sign up</title>
<script type="text/javascript">
	$(document).ready(function(){
		//취소
		$(".cancle").on("click",function(){
			location.href="/";
		})
		$("#submit").on("click",function(){
			
			if($("#uid").val()==""){
				alert("아이디 입력해주세요");
				$("#uid").focus();
				return false;
			}
			if($("#pwd").val()==""){
				alert("비밀번호 입력해주세요.");
				$("#pwd").focus();
				return false;
			}
			if($("#re_pwd").val()==""){
				alert("비밀번호 재입력해주세요.");
				$("#re_pwd").focus();
				return false;
			}
			if($("#name").val()==""){
				alert("이름 입력해주세요.");
				$("#name").focus();
				
				return false;
			}
			if($("#email").val()==""){
				alert("이메일을 입력해주세요");
				$("#email").focus();
				return false;
				}
			var idChkVal=$("#idChk").val();
			if(idChkVal=="N"){
				alert("중복확인 버튼 눌러주세요");
			}else if(idChkVal=="Y"){
				alert("회원가입이 완료 되었습니다. 이메일 본인 인증을 해주세요")
				$("#regForm").submit();
			}
			});
			
		})
		
		function fn_idChk(){
			$.ajax({
				url:"/user/idChk",
				type: "post",
				dataType : "json",
				data :{"uid" :$("#uid").val()},
				success:function(data){
					if(data==1){
						alert("중복된 아이디입니다.");
					}
					else if(data==0){
						$("#idChk").attr("value","Y");
						alert("사용 가능한 아이디입니다.");
						
					}
				}
				
			})
		}
	function fn_emailChk(){
		$.ajax({
			url:"/user/emailChk",
			type:"post",
			dateType:"json",
			data :{"email" :$("#email").val()},
			success:function(date){
			<%--	if(date==1){
					alert("중복된 이메일 입니다.");
					
				}
			
				else if(date==0){
					$("#email").attr("value","Y");
					alert("사용가능한 이메일 입니다.");
				} --%>
				$("#email").attr("value","Y");
				alert("사용가능한 이메일 입니다.");
			}
		})
	}
	
	
</script>
</head>
<body>
<section id="container">
<div class="signUpForm">
	<form action="/user/signUpForm" method="post" id="regForm"> 
	<label for="uid">아이디</label>
	<input type="text" name="uid" id="uid"/>
	<button class="idChk" type="button" id="idChk" onclick="fn_idChk();" value="N">중복확인</button>
	<br/>
	<label>이름</label>
	<input type="text" name="name"><br/>
	
	<label>비밀번호</label>
	<input type="password" name="pwd" id="pwd"><br/>
	
	<label>비밀번호 확인</label>
	<input type="password" name="re_pwd" id="re_pwd"><br/>

	
	<label>이메일</label>
	<input type="text" name="email" id="email">
	<button class="emailChk" type="button" id="emailChk" onclick="fn_emailChk();" value="N">중복확인</button>
	<br/>
	<button type="submit" id="submit">작성</button>
	
	</form>
</div>


</body>
</html>