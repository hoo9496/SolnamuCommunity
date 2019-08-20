<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>메인 페이지</title>
<script type="text/javascript" src="./resources/js/jquery.js"></script>
<script type="text/javascript" src="./resources/js/login.js"
	charset="utf-8"></script>
<link href="/resources/css/main.css" rel="stylesheet" type="text/css">
</head>
<c:if test="${!empty sessionScope.id}">
	<body id="loginok">
		<jsp:include page="Head.jsp" />
		<div id="menu">
			<a href="/notice_board_list.do?offset=0&pagelink=1" id="a1">
				<span class="menu a">
					<img src="/resources/img/notice.png" id="no1">
				</span>
			</a>
			 
			<a href="/debug_board_list.do?offset=0&pagelink=1" id="a2">
				<span class="menu b">
					<img src="/resources/img/debugging.png" id="no2">
				</span> 
			</a>
			<a href="/community_board_list.do?offset=0&pagelink=1" id="a3">
				<span class="menu c">
					<img src="/resources/img/community.png" id="no3">
				</span>
			</a>
			<a href="/chat.do" id="a4">
				<span class="menu d">
					<img src="/resources/img/chat.png" id="no4" >
				</span>
			</a>
		</div>

	</body>
</c:if>

<c:if test="${empty sessionScope.id}">
<c:url var="signUpUrl" value="/user_signUp_form.do" />

	<body id="loginNo">
		<div id="container">
			<c:url var="mainUrl" value="/main.do" />
			<form name="loginForm" method="post" action="${mainUrl}">
				<table align="center" id="logo">
					<tr>
						<td colspan="2" align="center"><img
							src="/resources/img/SOLNAMU_white.png" /></td>
					</tr>

					<tr>
						<td><label><b>I&nbsp;&nbsp;D</b></label><br /> <br /> <input
							class="txt" type="text" id="id" size="50" maxlength="20" /><br /> <br />
					</tr>
					<tr>
						<td><label><b>Password</b></label><br /> <br /> <input
							class="txt" type="password" id="pw" size="50" maxlength="12"  onkeyup="enterkeyLogin()" /><br />
					</tr>

					<tr>
						<td colspan="2" align="right"><p>
								<a id="a5" href="${signUpUrl}">Sign Up</a>
							</p></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="button"
							id="login" value="LOG IN" onclick="loginCheck()" /></td>
					</tr>
				</table>
			</form>
			

		</div>
</c:if>
</body>
</html>