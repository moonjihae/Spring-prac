<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 제이쿼리 -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<meta charset="UTF-8">
<title>board list</title>
</head>
<div id="nav">
	<%@ include file="../include/nav.jsp" %>
</div>
<body>
<div class="search">
	
		<select name="searchType">
				    <option value="title">제목</option>
			        <option value="content">내용</option>
				    <option value="title_content">제목+내용</option>
				    <option value="writer">작성자</option>
		</select>
 
 <input type="text" name="keyword" id="keyword" />

 <button type="button" id="searchBtn">검색</button>
 
<script>

 document.getElementById("searchBtn").onclick = function () {
    
  let searchType = document.getElementsByName("searchType")[0].value;
  let keyword =  document.getElementsByName("keyword")[0].value;
  
  location.href = "/board/listSearch?num=1" + "&searchType=" + searchType + "&keyword=" + keyword;
 };
</script>
</div>

<table>
	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성일</th>
			<th>작성자</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="list">
		 <tr>
		 <td>${list.bno}</td>
		  <td><a href="/board/view?bno=${list.bno}">${list.title}</a>
		  </td>
		  <td><fmt:formatDate value="${list.regDate}" pattern="yyyy-MM-dd" />	</td>
		  <td>&nbsp; ${list.writer}</td>
		  <td>${list.viewCnt}</td>
		 </tr>
		</c:forEach>
	</tbody>
</table>



<div>
	<c:if test="${pageMaker.prev}">
		<span>
		[<a href="/board/listSearch?num=${pageMaker.startPage-1}">이전</a>]
		</span>
	</c:if>
	
	<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="num">
		<span>
		<c:if test="${select!=num}">
			<a href="/board/listSearch?num=${num}">${num}</a>
		</c:if>
		
		<c:if test="${select==num}">
			<b>${num}</b>
		</c:if>
		</span>
	</c:forEach>
	
	<c:if test="${pageMaker.next}">
		<span>[<a href="/board/listSearch?num=${pageMaker.endPage+1}">다음</a>]</span>
	</c:if>
	
	<!--<c:forEach begin="1" end="${pageNum}" var="num">
		<span>
			<a href="/board/listPage?num=${num}">${num}</a>
		</span>
	</c:forEach> -->
	
</div>
</body>
</html>