<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.solnamu.yb.dto.UserBean, java.util.*, java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>내 회원 정보</title>
<link href="/resources/css/info.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="./resources/css/banner.css">
<script type="text/javascript">
	function userInfoEdit(){
		document.location.href = '/user_info_update_form.do?id=${user.getId()}';
	}
</script>
</head>
<body>
	<jsp:include page="../Head.jsp" />
	<jsp:include page="../navigation.jsp" />
	<div id="banner" style="background-image: url('/resources/img/banner.png')">
		<div id="banner_label">
			<label>User Information</label>
		</div>
	</div>
	<form>
	<table id="userinfo" cellpadding="1" width="60%" align="center">
	<tbody>
		<tr>
			<th width="30%"><p align="center">I&nbsp;&nbsp;D</p></th>
			<td width="70%"><p align="center">${user.getId()}</p></td>
		</tr>
		<tr>
			<th><p align="center">Name</p></th>
			<td><p align="center">${user.getName()}</p></td>
		</tr>
		<tr>
			<th><p align="center">Class Name</p></th>
			<td><p align="center">${user.getCour_name()}</p></td>
		</tr>
		<tr>
			<th><p align="center">Birthday</p></th>
			<td><p align="center">${user.getBirthday()}</p></td>
		</tr>
		<tr>
			<th><p align="center">Grade</p></th>
			<td><p align="center">
				<c:if test="${user.getAdministrator() == 1}">
					<c:out value="관리자" />
				</c:if>
				<c:if test="${user.getAdministrator() != 1}">
					<c:out value="회원" />
				</c:if>
			</p></td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="2"><p align="center">
			<input id="btn" type="button" value="Revise Information" onclick="userInfoEdit()">
			</p></td>
		</tr>
	</tfoot>
	</table>
	</form>
</body>
</html>