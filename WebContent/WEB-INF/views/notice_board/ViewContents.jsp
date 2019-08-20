<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>공지글 보기</title>
<link type="text/css" rel="stylesheet" href="/resources/css/banner.css">
<link type="text/css" rel="stylesheet" href="/resources/css/navi.css">
<link type="text/css" rel="stylesheet" href="/resources/css/view.css">
<script type="text/javascript">
	function hideshow(no) {
	  var x = document.getElementById("InputTR_"+no);
	  if (x.style.display === "none") {
	    x.style.display = "block";
	  } else {
	    x.style.display = "none";
	  }
	}
	function boardmodify(no,offset,pagelink){
		document.location.href = "notice_board_update_form.do?no="+no+"&offset="+offset+"&pagelink="+pagelink;;
	}
	function boarddelete(no){
		document.location.href = "notice_board_delete.do?no="+no;
	}
	function backlist(offset, pagelink){
		document.location.href = "/notice_board_view_contents.do?offset="+offset+"&pagelink="+pagelink;
	}
</script>
</head>
<body>
	<jsp:include page="../Head.jsp" />
	<jsp:include page="../navigation.jsp" />
	<div id="banner"
		style="background-image: url('/resources/img/notice_banner.png')">
		<div id="banner_label">
			<label>Notice</label>
		</div>
	</div>
	<c:if test="${!empty sessionScope.id}">
		<div id="subject">
			<c:out value="[공지]${boardData.getSubject()}" />
		</div>
		<hr width="60%" align="center" />
		<div id="namedate">
			<span id="name"><c:out value="${boardData.getName()}" /></span> <span
				id="date"><c:out value="${boardData.getWdate()}" /></span>
		</div>
		<br>
		<div id="view">
			<c:out escapeXml="false" value="${boardData.getContents()}" />
			<br>
			<div id="img"></div>
		</div>
		<form action="/community_board_reply.do" method="POST">
			<table id="buttonTable">
				<tr>
					<td id="buttontd" colspan="2"><input type="button" value="Update"
						onclick="javascript:boardmodify(${boardData.getNo()},${offset},${pagelink})">
						<input type="button" value="Delete"
						onclick="javascript:boarddelete(${boardData.getNo()})"></td>
				</tr>
			</table>
		</form>
		<div></div>
	</c:if>
</body>
</html>