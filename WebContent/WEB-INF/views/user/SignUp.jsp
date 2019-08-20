<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"
	import="java.util.*, java.sql.*, javax.servlet.http.*, java.io.*, java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원 가입</title>

<script type="text/javascript" src="./resources/js/jquery.js"></script>
<script type="text/javascript" src="./resources/js/signUp.js"
	charset="utf-8"></script>
<link href="/resources/css/SignUp.css" rel="stylesheet" type="text/css">
</head>

<body id="loginNo">
	<div id="container">
		<c:url var="insertUrl" value="/user_signUp_insert.do" />
		<sf:form name="signUpForm" modelAttribute="signUpWrite" method="post"
			action="${insertUrl}">
			<table align="center" id="logo">
				<tr>
						<td colspan="2" align="center"><a href="/main.do">
						<img src="http://hoo9496.cafe24.com/resources/img/SOLNAMU_white.png" /></a> <br /> <br /></td>
				</tr>
				<tr>
					<td colspan="2"><label><b>I&nbsp;&nbsp;D</b></label><br /> <br />
					<sf:input id="id" path="id" size="50" maxlength="20" class="txt"
							placeholder="ex)soldesk@naver.com"/> <br />
				</tr>
				<tr>
					<td colspan="2"><label><b>Password</b></label><br /> <br />
					<sf:input class="txt" type="password" path="pw" size="50" maxlength="12" /><br />
				</tr>
				<tr>
					<td colspan="2"><label><b>Confirm password</b></label><br /> <br />
					<sf:input class="txt" type="password" path="rePw" size="50" maxlength="12" /><br />
				</tr>
				<tr>
					<td colspan="2"><label><b>Name</b></label><br /> <br />
					<sf:input class="txt" path="name" size="50" maxlength="10" /><br />
				</tr>
				<tr>
					<td colspan="2"><label><b>Birthday</b></label><br /> <br />
					<sf:input id="birthday" path="birthday" size="50" maxlength="8" class="txt"
							placeholder="ex)19950612" /><br />
				</tr>
				<tr>
					<td><label><b>Class Name</b></label><br /> <br /><sf:select id="select" path="cour_name">
							<c:forEach var="cl" items="${courseList}">
								<option id="option" value="${cl.cour_name}">${cl.cour_name}</option>
							</c:forEach>
						</sf:select>
						
						<td align="right"><label><b></b></label><br /> <br />
						<input id="reset" type="reset" value="Rewrite"></td>
						</tr>
				<tr>
					<td colspan="2" align="center"><input id="SignUp" type="button" value="Sign Up"
						onclick="idCk()">   
					</td>
				</tr>
			</table>
		</sf:form>
	</div>
</body>
</html>