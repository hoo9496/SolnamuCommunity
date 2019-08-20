<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>강좌 관리</title>
<link type="text/css" rel="stylesheet"
	href="./resources/css/courses.css">
<link type="text/css" rel="stylesheet"
	href="./resources/css/banner.css">
<script type="text/javascript">
	function insert_cour(){
		document.location.href='/admin_course_write_form.do';
	}
	function update_cour(code){
		document.location.href='/admin_course_update_form.do?code='+code;
	}
	function delete_cour(code){
		document.location.href='/admin_course_delete.do?code='+code;
	}
</script>
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
		<table id="cour_tab" align='center' width='60%'>
			<tr>
				<th width="175"><p align="center">Class Code</p></th>
				<th width="300"><p align="center">Class Name</p></th>
				<th width="175"><p align="center">Start Date</p></th>
				<th width="175"><p align="center">End Date</p></th>
				<th colspan="2" width="175"><p align="center">Update/Delete</p></th>
			</tr>

			<c:forEach var="course" items="${CourseList}">
				<tr>
					<td width="100"><p align="center">${course.getCode()}</p></td>
					<td width="300"><p align="center">${course.getCour_name()}</p></td>
					<td width="175"><p align="center">${course.getStart_date()}</p></td>
					<td width="175"><p align="center">${course.getEnd_date()}</p></td>
					<td><input id="redel" type="button" value="Update"
						onclick="update_cour(${course.getCode()})"></td>
					<td><input id="redel" type="button" value="Delete"
						onclick="delete_cour(${course.getCode()})"></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="6" id="btntd" align="right">
				<input id="btn" type="button" onclick="insert_cour()"/></td>
			</tr>
		</table>
	</c:if>
	<c:if test="${sessionScope.admin != '1' || empty sessionScope.admin}">
		<h1>권한이 없는 사용자의 잘못된 접근 입니다!</h1>
	</c:if>
</body>
</html>
