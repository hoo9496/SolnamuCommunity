<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>������ - ȸ�� ���� ����</title>
<link href="/resources/css/adminpage.css" rel="stylesheet"
	type="text/css">
<link href="/resources/css/banner.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<jsp:include page="../Head.jsp" />
	<jsp:include page="../navigation.jsp" />
	
	<c:if test="${!empty sessionScope.admin && sessionScope.admin == '1'}">
	<div id="banner" style="background-image: url('/resources/img/banner.png')">
		<div id="banner_label">
			<label>User Management</label>
		</div>
	</div>

		<script type="text/javascript">
		function deleteuser() {
			if ($('input:checkbox[id="check"]').is(":checked") == false) {
				alert("üũ�ڽ��� �������ּ���.");
				return;
			} else {
				alert("���� ����");
				UserCheck.select.value = 1;
				UserCheck.submit();
			}
		}

		function pwreset() {
			if ($('input:checkbox[id="check"]').is(":checked") == false) {
				alert("üũ�ڽ��� �������ּ���.");
				return;
			} else {
				alert("��й�ȣ �ʱ�ȭ");
				UserCheck.select.value = 0;
				UserCheck.submit();
			}
		}
		function adminuser() {
			if ($('input:checkbox[id="check"]').is(":checked") == false) {
				alert("üũ�ڽ��� �������ּ���.");
				return;
			} else {
				alert("������ ���� �ο�");
				UserCheck.select.value = 2;
				UserCheck.submit();
			}
		}

		</script>
		<div id="gunsu">�� ${MemberCount} ���� ȸ���� �ֽ��ϴ�.</div>

		<form name="UserCheck" action="admin_userupdate.do" method="post">
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
						<input type="button" name="OK" onclick="pwreset()" value="Reset Password">
						<input type="button" name="delete" onclick="deleteuser()" value="Del Member">
						<input type="button" name="admin" onclick="adminuser()" value="Grant Admin"></td>
				</tr>
			</Table>
		</form>
	</c:if>
	<c:if test="${sessionScope.admin != '1' || empty sessionScope.admin}">
		<h1>������ ���� ������� �߸��� ���� �Դϴ�!</h1>
	</c:if>
</body>
</html>