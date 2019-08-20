<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" 
    import="java.util.*, java.sql.*, javax.servlet.http.*, java.io.*, java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript" src="./resources/js/jquery.js"></script>
<script type="text/javascript" src="./resources/js/board.js" charset="utf-8"></script>
<link href="/resources/css/write&update.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="./resources/css/banner.css">
<title>공지게시판 글쓰기</title>
</head>
<body>
	<jsp:include page="../Head.jsp" />
	<jsp:include page="../navigation.jsp" />
	<div id="banner" style="background-image: url('/resources/img/notice_banner.png')">
		<div id="banner_label">
			<label>Notice</label>
		</div>
	</div>
	<c:if test="${!empty sessionScope.id && sessionScope.admin == 1}" >
	<c:url var="insertUrl" value="/notice_board_do_write.do" />
	<sf:form name="writeForm" modelAttribute="boardBeanObjToWrite" method="post" action="${insertUrl}">
		<table id="contents_table" align="center" width="60%" cellpadding="1">
			<tr>
				<th width="40%" align="right">Writer</th>
				<td width="60%" align="left"><sf:input id="txt" path="name" size="50" maxlength="50" readonly="true"/>
			</tr>
			<tr>
				<th align="right">Subject</th>
				<td align="left"><sf:input id="txt" path="subject" size="50" maxlength="50"/>
			</tr>
			<tr>
				<th align="right">Contents</th>
				<td align="left"><sf:textarea id="txt_contents" path="contents" size="200" maxlength="600"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input id="btn" type="button" value="Upload" onclick="writeCheck()">
				</td>
			</tr>
		</table>
	</sf:form>
	</c:if>
</body>
</html>