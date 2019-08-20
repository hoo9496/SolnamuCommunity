<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="/resources/css/adminpage.css" rel="stylesheet"
	type="text/css">
<link href="/resources/css/banner.css" rel="stylesheet" type="text/css">
<title>������ - ȸ�� ���� ����</title>
</head>

<script type="text/javascript">
function deleteuser() {
	if ($('input:checkbox[id="check"]').is(":checked") == false) {
		alert("üũ�ڽ��� �������ּ���.");
		return;
	} else {
		alert("����");
		UserCheck.select.value = 1
		UserCheck.submit()
	}
}

function okuser() {
	if ($('input:checkbox[id="check"]').is(":checked") == false) {
		alert("üũ�ڽ��� �������ּ���.");
		return;
	} else {
		alert("����");
		UserCheck.select.value = 0
		UserCheck.submit()
	}
}
</script>
<body>
	<jsp:include page="../Head.jsp" />
	<jsp:include page="../navigation.jsp" />
	<div id="banner" style="background-image: url('/resources/img/banner.png')">
		<div id="banner_label">
			<label>Sign Up Management</label>
		</div>
	</div>
	<c:if test="${!empty sessionScope.admin && sessionScope.admin == '1'}">
		<div id="gunsu">�� ${MemberCount} ���� ��û�� �ֽ��ϴ�.</div>
		<form name="UserCheck" action="admin_usercheck.do" method="post">
			<Table id="table" align="center">

				<tr>
					<th>I&nbsp;&nbsp;D</th>
					<th>Name</th>
					<th>Birthday</th>
					<th>Class Name</th>
					<th>Grade</th>
					<th>Choice</th>
					<!-- ���̵� pk
					��й�ȣ
					�̸�
					�������
					�����ڵ� fk
					�����ڱ��� -->
				</tr>
				<c:forEach items="${MemberList}" var="member">
					<tr>
						<td>${member.getId()}</td>
						<td>${member.getName()}</td>
						<td>${member.getBirthday()}</td>
						<td>${member.getCour_name()}</td>
						<c:if test="${member.getAdministrator() == 1}">
							<td><c:out value="������" /></td>
						</c:if>
						<c:if test="${member.getAdministrator() != 1}">
							<td><c:out value="ȸ��" /></td>
						</c:if>
						<td><input type="checkbox" name="check" id="check"
							value="${member.getId()}"></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="6" style="background-color: transparent;">
					<input type="hidden" name="select" value="0">
					<input type="button" name="yes" onclick="okuser()" value="Ok">
					<input type="button" name="no" onclick="deleteuser()" value="No">
				</tr>
			</Table>
		</form>
	</c:if>
	<c:if test="${sessionScope.admin != '1' || empty sessionScope.admin}">
		<h1>������ ���� ������� �߸��� ���� �Դϴ�!</h1>
	</c:if>
</body>
</html>