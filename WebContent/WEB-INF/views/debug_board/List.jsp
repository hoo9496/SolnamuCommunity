<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="./resources/js/jquery.js"></script>
<script type="text/javascript" src="./resources/js/board.js" charset="utf-8"></script>

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link type="text/css" rel="stylesheet" href="./resources/css/boardList.css">
<link type="text/css" rel="stylesheet" href="./resources/css/banner.css">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<title>디버깅게시판</title>
</head>
<body>
	<jsp:include page="../Head.jsp" />
	<jsp:include page="../navigation.jsp" />
	<div id="banner" style="background-image: url('/resources/img/debugging_banner.png')">
		<div id="banner_label">
			<label>Debugging</label>
		</div>
	</div>
	<c:if test="${!empty sessionScope.id}" >
	
	<div id="boardList">
		<table align="center" border="0">
			<thead>
				<tr style="background: white">
					<td colspan="5" align="right">
							<form name="searchForm" action="/debug_board_serch_list.do" method="GET" >
								<div class="inputIcon">
									<select id="search_find" name="find">
											<option value="name">이름</option>
											<option value="subject">제목</option>
											<option value="contents">내용</option>
									</select>
									<input type="hidden" name="offset" value="0" />
									<input type="hidden" name="pagelink" value="1" />
									<input type="text" id="search" name="search" onkeyup="enterkey()">
									<i class="fa fa-search" onclick="searchCheck()"></i>
								</div>
							</form>
					</td>
				</tr>
				<tr>
					<th width="12%"><p align="center">No</p></th>
					<th width="13%"><p align="center">Name</p></th>
					<th width="43%"><p align="center">Subject</p></th>
					<th width="20%"><p align="center">Date</p></th>
					<th width="12%"><p align="center">Views</p></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${boardList}">
					<tr>
						<td><p align="center">${board.no}</p></td>
						<td><p align="center">${board.name}</p></td>
						<td>
							<p align="left" style="margin-left: 10px">
								<a id="href" href="debug_board_view_contents.do?no=${board.no}&offset=${offset}&pagelink=${pagelink}">
								<c:out value="${board.subject}" />
								</a>
							</p>
						</td>
						<td><p align="center">${board.wdate}</p></td>
						<td><p align="center">${board.views}</p></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
					<tr>
						<td></td>
						<td colspan="3">
								<span id="paging">
									${PagingCount}
								</span>
						</td>
						<td align="right">
							<form action="/debug_board_write_form.do" method="get">
								<input type="submit" id="btn" value="">
							</form>
						</td>
					</tr>
			</tfoot>
		</table>
	</div>
	
	</c:if>
	



	<!--
	<jsp:include page="../Head.jsp" />
	<c:if test="${!empty sessionScope.id}" >
	<table align="center" cellpadding="1" width="700" border="0">
		<tr>
			<td>총 게시물 수 : <c:out value="${boardCount}" /></td>
			<td><p align="right">
					페이지:
					<c:out value="1" />
				</p></td>
			<td><form action="/debug_board_write_form.do" method="get">
				<input type="submit" value="글쓰기">
			</form></td>
		</tr>
	</table>

	<table align="center" cellpadding="1" width="700" border="1">
		<tr>
			<td width="80"><p align="center">번호</p></td>
			<td width="100"><p align="center">이름</p></td>
			<td width="320"><p align="center">제목</p></td>
			<td width="100"><p align="center">등록일</p></td>
			<td width="100"><p align="center">조회수</p></td>
		</tr>

		<c:forEach var="board" items="${boardList}">
			<tr>
				<td width="80"><p align="center">${board.getNo()}</p></td>
				<td width="100"><p align="center">${board.getName()}</p></td>
				<td width="320">
					<p align="center">
						<a href="debug_board_view_contents.do?no=${board.getNo()}&offset=${offset}&pagelink=${pagelink}">
						<c:out value="${board.getSubject()}" />
						</a>
					</p>
				</td>
				<td width="100"><p align="center">${board.getWdate()}</p></td>
				<td width="100"><p align="center">${board.getViews()}</p></td>
			</tr>
		</c:forEach>
	</table>
	<form action="/debug_board_serch_list.do" method="GET" >
		<table align="center" width="700" >
			<tr height="40"><td colspan="3">${PagingCount}</td></tr>
			<tr>
				<td width="80"><select name="find">
						<option value="name">이름</option>
						<option value="subject">제목</option>
						<option value="contents">내용</option>
				</select></td>
				
				<td width="520"><input type="hidden" name="offset" value="0" />
				<input type="hidden" name="pagelink" value="1" />
				<input type="text" name="search"  style="width:500px;"></td>
				<td width="100"><input type="submit" value="검색" style="width:90px; height:30px"></td>
			</tr>
		</table>
	</form>
	</c:if>
	-->
</body>
</html>