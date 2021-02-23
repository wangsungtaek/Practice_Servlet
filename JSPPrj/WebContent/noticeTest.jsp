<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>    
<%@ page import="com.newlecture.web.entity.Notice" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판리스트</title>
<style>
	table {
		border-collapse: collapse;
	}
	table td,th{
		border: 1px solid black;
	}
	a {
		text-decoration: none;
	}
	.bold {
		font-weight: bold;
	}
	.orange {
		color: orange;
	}
</style>

</head>
<body>
	<h3>게시판리스트</h3>
	<form>
	<select name="f">
		<option ${(param.f=='title')?'selected':''} value="title">제목</option>
		<option ${(param.f=='writer_id')?'selected':''} value="writer_id">작성자</option>
	</select>
	<input type="text" name="r" value="${param.r}"/>
	<input type="submit" value="검색"/>
	<table>
		<tr><th>번호</th><th>제목</th><th>내용</th><th>작성자</th><th>작성일</th><th>조회수</th></tr>
		<c:forEach var="n" items="${list}">
			<tr><td>${n.id}</td>
				<td>${n.title}</td>
				<td>${n.content}</td>
				<td>${n.writer_id}</td>
				<td>${n.regdate}</td>
				<td>${n.hit}</td></tr>
		</c:forEach>
	</table>
	</form>
	
	<c:set var="page" value="${(empty param.p)?1:param.p}" />
	<c:set var="startNum" value="${page-(page-1)%5}" />
	<c:set var="lastNum" value="23" />
	<c:forEach var="i" begin="0" end="4">
		<a href="?p=${startNum+i}&f=${param.f}&r=${param.r}" class="bold ${(page==(startNum + i)?'orange':'' )}">
			${startNum + i}
		</a>
	</c:forEach>
	
	<c:if test="${startNum > 1}">
	<a href="?p=${startNum-1}">이전페이지이동</a>
	</c:if>
	<c:if test="${startNum <= 1}">
	<a onclick="alert('이전페이지가 없습니다.')">이전페이지이동</a>
	</c:if>
	<c:if test="${startNum+5 < lastNum}">
	<a href="?p=${startNum+5}">다음페이지이동</a>
	</c:if>
	<c:if test="${startNum+5 >= lastNum}">
	<a onclick="alert('다음페이지가 없습니다.')">다음페이지이동</a>
	</c:if>
</body>
</html>