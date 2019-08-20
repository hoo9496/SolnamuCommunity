<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript" src="./resources/js/jquery.js"></script>
<script type="text/javascript" src="./resources/js/course.js" charset="utf-8"></script>
<link href="/resources/css/write&update.css" rel="stylesheet" type="text/css">
<link href="/resources/css/banner.css" rel="stylesheet" type="text/css">
<title>강좌 수정</title>
</head>
<body>
	<jsp:include page="../Head.jsp" />
	<jsp:include page="../navigation.jsp" />
	<div id="banner" style="background-image: url('/resources/img/banner.png')">
		<div id="banner_label">
			<label>Class Management</label>
		</div>
	</div>
	<c:if test="${!empty sessionScope.admin && sessionScope.admin == '1'}">
	<c:url var="courupdateurl" value="/admin_course_update.do" />
	<sf:form name="courseUpdateForm" modelAttribute="updateCourseBean" method="POST" action="${courupdateurl}">
	<table id="contents_table" align="center" cellpadding="1" width="60%">
		<tr>
			<td width="40%"><p align="right">Class Code</p></td>
			<td width="60%"><p align="left"><sf:input id="txt" path="code" readonly="true"/></p></td>
		</tr>
		<tr>
			<td><p align="right">Class Name</p></td>
			<td><p align="left"><sf:input class="cour_name" id="txt" path="cour_name"/></p></td>
		</tr>
		<tr>
			<td><p align="right">Start Date</p></td>
			<td><p align="left"><sf:input class="start_date" id="txt" path="start_date"/></p></td>
		</tr>
		<tr>
			<td><p align="right">End Date</p></td>
			<td><p align="left"><sf:input class="end_date" id="txt" path="end_date"/></p></td>
		</tr>
		<tr>
			<td colspan="2"><p align="center">
				<input id="btn" type="button" value="Update Courses" onclick="courseUpdateCheck()">
			</p></td>
		</tr>
	</table>
	</sf:form>
	</c:if>
	<c:if test="${sessionScope.admin != '1' || empty sessionScope.admin}">
	<h1>권한이 없는 사용자의 잘못된 접근 입니다!</h1>
	</c:if>
</body>
</html>