<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user List</title>
</head>
<body>
	<h2>User List</h2>
<table>
	<thead>
		<tr>
			<th>User id</th>
			<th>User name</th>
			<th>email</th>
			<th>가입일</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="list">
			<tr>
				<th>${list.uid}</th>
				<th>${list.name }</th>
				<th>${list.email }</th>
				<th>${list.reg_dt }</th>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>