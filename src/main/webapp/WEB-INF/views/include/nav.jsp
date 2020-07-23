<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<ul>
	<li>
		<a href="/board/listSearch?num=1">검색</a>
	</li>
	
	<li>
		<a href="/board/listPage?num=1">글 목록(페이징)</a>
	</li>
	<li>
		<a href="/board/list">글 목록</a>
	</li>
	
	<li>
		<a href="/board/write">글 작성</a>
	</li>
	<li>	
		<c:if test="${user!=null}"><a href="/logout">로그아웃</a>
		</c:if>
	</li>
	<li>
		<c:if test="${user!=null}">
			<p>${user.uid}>님 안녕하세요.</p>
		</c:if>
	</li>
	
</ul>
</html>