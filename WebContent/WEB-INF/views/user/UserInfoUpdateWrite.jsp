<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.solnamu.yb.dto.UserBean, java.util.*, java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원 정보 수정</title>
<script type="text/javascript" src="/resources/js/userinfoUpdate.js" charset="utf-8"></script>
<link href="/resources/css/write&update.css" rel="stylesheet" type="text/css">
<link href="/resources/css/banner.css" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../Head.jsp" />
	<jsp:include page="../navigation.jsp" />
	<div id="banner" style="background-image: url('/resources/img/banner.png')">
		<div id="banner_label">
			<label>User Information</label>
		</div>
	</div>
	<c:url var="updateurl" value="/user_info_update.do" />
	<sf:form name="userInfoUpdateForm" modelAttribute="userInfoUpdateBean" method="POST" action="${updateurl}">
	<table id="contents_table" cellpadding="1" width="60%" align="center">
		<tbody>
		<tr>
			<th width="40%"><p align="right">I&nbsp;&nbsp;D</p></th>
			<td width="60%"><p align="left"><sf:input id="txt" path="id" readonly="true"/></p></td>
		</tr>
		<tr>
			<th><p align="right">Password</p></th>
			<td><p align="left"><sf:password class="pw" id="txt" path="pw" maxlength="12" /></p></td>
		</tr>
		<tr>
			<th><p align="right">Confirm password</p></th>
			<td><p align="left"><sf:password class="rePw" id="txt" path="rePw" maxlength="12" /></p></td>
		</tr>
		<tr>
			<th><p align="right">Name</p></th>
			<td><p align="left"><sf:input id="txt" path="name" readonly="true"/></p></td>
		</tr>
		<tr>
			<th><p align="right">Class Name</p></th>
			<td><p align="left"><sf:input id="txt" path="cour_name" readonly="true"/></p></td>
		</tr>
		<tr>
			<th><p align="right">Birthday</p></th>
			<td><p align="left"><sf:input class="birthday" id="txt" path="birthday"/></p></td>
		</tr>
		</tbody>
		<tfoot>
		<tr>
			<td colspan="2"><p align="center">
			<input id="btn" type="button" value="Update Information" onclick="userInfoUpdateCheck()">
			</p></td>
		</tr>
		</tfoot>
	</table>
	</sf:form>
</body>
</html>