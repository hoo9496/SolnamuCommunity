<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="./resources/js/jquery.js"></script>
<script type="text/javascript">
	function show() {
		if ($("#contents").css("display") == "none") {
			$('#contents').show();
		} else {
			$('#contents').hide();
		}
	}
	
	/* $('body').click(function(e) { 
		if ($("#contents").css("display") == "block") {
			if(!$('#contents, #my_btn').has(e.target).length){
				show();
			};
		};
	}); */
</script>
<style type="text/css">
#my_con:link {color: #333333; text-decoration: none;}
#my_con:visited {color: #333333; text-decoration: none;}
#my_con:hover {color: #333333; text-decoration: underline;}

#my_btn {
	width: 30px;
	height: 30px;
	cursor: pointer;
}

#contents {
	z-index:10;
	position: absolute;
	top: 35px;
	right: 0px;
	width: 120px;
	background-color: #e0e0e0;
	border-radius: 10px;
	display: none;
}
</style>
</head>
<body>
	<div style="height: 80px;">
	<div style="float:left; margin-top: 25px; margin-left: 100px;">
		<a href="/main.do"><img src="./resources/img/SOLNAMU.png"></a>
	</div>
	<div style="float:right; margin-top: 25px; margin-right: 100px;">
		<div style="float:right; margin-top: 3px; margin-left: 15px;">
			${sessionScope.name}님</div>
		<div id="mycontents" style="float:right; position: relative;">
			<img id="my_btn" src="./resources/img/mycontents.png" onclick="show()">
			<div id="contents">
				<table style="border-spacing: 0px 5px; margin-left: 10px">
					<tr><td><a id="my_con" href="/user_info.do">My page</a></td></tr>
					<tr><td><a id="my_con" href="/logout.do">Sign out</a></td></tr>
					<c:if test="${sessionScope.admin == 1 }">
					<tr><td><a id="my_con" href="/admin_usercheck.do">Sign up Mgt.</a></td></tr>
					<tr><td><a id="my_con" href="/admin_userinfo.do">User Mgt.</a></td></tr>
					<tr><td><a id="my_con" href="/admin_course_list.do">Class Mgt.</a></td></tr>
					</c:if>
				</table>
			</div>
		</div>
	</div>
	</div>
</body>
</html>