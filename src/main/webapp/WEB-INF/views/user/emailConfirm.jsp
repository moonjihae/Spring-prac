<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Email confirm success</title>
</head>
<body>
<script type="text/javascript">
	var id='${uid}';
	alert(id+"님 회원가입을 축하드립니다. 이제 로그인이 가능합니다.");
	window.open("","_self","");//브라우저 창 닫기
	window.close();

</script>
</body>
</html>