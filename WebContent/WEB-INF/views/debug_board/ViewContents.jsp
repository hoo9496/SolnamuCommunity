<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link type="text/css" rel="stylesheet" href="/resources/css/banner.css">
<link type="text/css" rel="stylesheet" href="/resources/css/navi.css">
<link type="text/css" rel="stylesheet" href="/resources/css/view.css">
<title>디버깅 게시글 보기</title>
<script type="text/javascript" src="./resources/js/jquery.js"></script>
<script type="text/javascript">
	function hideshow(no) {
	  var x = document.getElementById("InputTR_"+no);
	  if (x.style.display === "none") {
	    x.style.display = "";
	  } else {
	    x.style.display = "none";
	  }
	}
	function boardmodify(no,offset,pagelink){
		if(${boardData.getId() == sessionScope.id}){
			document.location.href = "debug_board_update_form.do?no="+no+"&offset="+offset+"&pagelink="+pagelink;
		}else{
			alert("수정권한이 없습니다.");
		}
	}
	function boarddelete(no){
		if(${boardData.getId() == sessionScope.id || sessionScope.admin == 1} ){
			
		document.location.href = "debug_board_delete.do?no="+no;
		}else{
			alert("삭제권한이 없습니다.");
		}
	}

	function submit2(form){
		
		if(form.content.value == ""){
			alert("댓글을 입력하세요.");
		}else{
			form.submit();
		}
		
	}
	
	$(document).ready(function(){
		var Target = $('.rere_icon'); 
		Target.on('click', function(){
			var text = $(this).siblings('.textarea').val();
			if(text == ""){
				alert("댓글을 입력하세요.");
			} else {
				$(this).parent('.re_form').submit();
			}
			
		});
	});
	
	function backlist(offset, pagelink){
		document.location.href = "/debug_board_list.do?offset="+offset+"&pagelink="+pagelink;
	}
	
	function deletereply(rno,id) {
		var no = ${boardData.getNo()};
		var offset = ${offset}
		var pagelink = ${pagelink}
		
		
		if( ${"sessionScope.id" == id || sessionScope.admin == 1 }){
			document.location.href = "/debug_delete_reply.do?no="+no+"&rno="+rno+"&offset="+offset+"&pagelink="+pagelink;
		}else{
			alert("삭제권한이 없습니다.");
		}
		
	}
	function deleterereply(rno,sno,id) {
		var no = ${boardData.getNo()};
		var offset = ${offset};
		var pagelink = ${pagelink};
		if( ${"sessionScope.id" == id || sessionScope.admin == 1 }){
			document.location.href = "/debug_delete_rereply.do?no="+no+"&rno="+rno+"&sno="+sno+"&offset="+offset+"&pagelink="+pagelink;
		}else{
			alert("삭제권한이 없습니다.");
		}
		
	}
</script>
</head>
<body>
	<jsp:include page="../Head.jsp" />
	<jsp:include page="../navigation.jsp" />
	<div id="banner"
		style="background-image: url('/resources/img/debugging_banner.png')">
		<div id="banner_label">
			<label>Debugging</label>
		</div>
	</div>
	<c:if test="${!empty sessionScope.id}">
		<div id="subject">
			<c:out escapeXml="false" value="${boardData.getSubject()}" />
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
			<div id="img">
				<img src="${filename}" alt="" width="60%" align="bottom" />
			</div>
		</div>
		<form action="/debug_board_reply.do" method="POST" >
			<table id="buttonTable">
				<tr>
					<td id="buttontd" colspan="2"><input type="button" value="Update"
						onclick="javascript:boardmodify(${boardData.getNo()},${offset},${pagelink})">
						<input type="button" value="Delete"
						onclick="javascript:boarddelete(${boardData.getNo()})"></td>
				</tr>

				<tr>
					<td><textarea class="textarea" id="reply" name="content"
							Style="width: 350px;height:30px;"></textarea> <input
						type="hidden" name="no" value="${boardData.getNo()}"> <input
						type="hidden" name="writer" value="${SessionScope.name}">
						<input type="hidden" name="offset" value="${offset}"> <input
						type="hidden" name="pagelink" value="${pagelink}"></td>
					<td><input type="button" onclick="submit2(this.form)" value="Comment"></td>
				</tr>

			</table>
		</form>
		<div>
			<table id="replytable">

				<c:forEach var="reply" items="${replyData}">
					
					<tr id="replytr">
						<td align="center"><c:out value="${reply.getRno()}" /></td>
						<td id="td_name"><c:out value="${reply.getWriter()}" /></td>
						<td><c:out value="${reply.getContent()}" /></td>
						<td id="td_icon" align="right"><img id="re_icon" alt="" src="./resources/img/write.png" 
								onclick="hideshow(${reply.getRno()})"></td>
						<td id="td_icon" align="right"><img id="re_icon" alt="" src="./resources/img/delete.png"
							 	onclick="deletereply(${reply.getRno()},'${reply.getId()}')"></td>
					</tr>

					<tr id="InputTR_${reply.getRno()}" style="display: none;">
						<td colspan="5" align="center">
							<form class="re_form" action="/debug_board_rereply.do" method="post">
								<input type="hidden" name="no" value="${boardData.getNo()}">
								<input type="hidden" name="rno" value="${reply.getRno()}">
								<input type="hidden" name="offset" value="${offset}">
								<input type="hidden" name="pagelink" value="${pagelink}">
								<textarea class="textarea" name="content"
									Style="width: 350px;height:30px;"></textarea>
								<img class="rere_icon" alt="" src="./resources/img/write.png">
							</form>	
						</td>
					</tr>

					<c:forEach var="rereply" items="${rereplyData}">
						<c:if test="${reply.getRno() == rereply.getRno() }">
							<tr id="rereplytr"><td></td>
								<td id="td_name"><c:out value="${rereply.getWriter()}" /></td>
								<td colspan="2"><c:out value="${rereply.content}" /></td>
								<td id="td_icon" align="right">
									<img id="re_icon" alt="" src="./resources/img/delete.png" onclick="deleterereply(${rereply.getRno()},${rereply.getSno()},'${rereply.getId()}')">
								</td>
							</tr>
						</c:if>
					</c:forEach>
					
				</c:forEach>
			</table>
		</div>
	</c:if>
</body>
</html>