<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<link type="text/css" rel="stylesheet" href="./resources/css/chat.css">
<link type="text/css" rel="stylesheet" href="./resources/css/banner.css">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>${course_name}채팅방</title>
</head>
<body>
	<jsp:include page="Head.jsp" />
	<jsp:include page="navigation.jsp" />
	<div id="banner"
		style="background-image: url('/resources/img/banner.png')">
		<div id="banner_label">
			<label>Chat</label>
		</div>
	</div>
	<c:if test="${!empty sessionScope.id}">
		<fieldset id="fieldset">
			<h2>${course_name} 채팅방</h2>
			<textarea id="messageWindow" rows="23" cols="100" readonly="true"></textarea>
			<br />
			<h3 id="marginid"></h3>
			<input id="inputMessage" type="text" onkeyup="enterkey()" />
			<input type="submit" value="send" id="btn" onclick="send()" />
		</fieldset>
	</c:if>
	<c:if test="${empty sessionScope.id}">
		<h1>잘못된 접근입니다</h1>
	</c:if>
</body>
<script type="text/javascript">
	var textarea = document.getElementById("messageWindow");
	var webSocket = new WebSocket('ws://172.16.3.22:8080/${code}');
	var inputMessage = document.getElementById('inputMessage');
	webSocket.onerror = function(event) {
		onError(event)
	};
	webSocket.onopen = function(event) {
		onOpen(event)
	};
	webSocket.onmessage = function(event) {
		onMessage(event)
		onList(event);
	};
	function onMessage(event) {
		textarea.value += event.data + "\n";
		textarea.scrollTop = textarea.scrollHeight;
	}
	function onOpen(event) {
		textarea.value += "연결 성공\n";
		webSocket.send("${sessionScope.name} 님이 입장하셨습니다. ");
	}
	function onError(event) {
		alert(event.data);
	}
	function send() {
		textarea.value += "나 : " + inputMessage.value + "\n";
		webSocket.send("${sessionScope.name} : " + inputMessage.value);
		inputMessage.value = "";
		textarea.scrollTop = textarea.scrollHeight;
	}
	function enterkey() {
		if (window.event.keyCode == 13) {
			send();
		}
	}
</script>
</html>