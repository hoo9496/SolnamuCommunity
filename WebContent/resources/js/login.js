function loginCheck() {

	$.ajax({
		url : "loginCheck.do",
		type : "POST",
		data : { 
			id : $("#id").val(),
			pw : $("#pw").val()
		},
		dataType : "json",
		success : function(loginCheckResult) {
			
			if (document.loginForm.id.value == "") {
				alert("아이디를 입력하세요.");
				$("#id").focus();
				return;
			} 
			if (document.loginForm.pw.value == "") {
				alert("비밀번호를 입력하세요.");
				$("#pw").focus();
				return;
			} 
			if (loginCheckResult.checkLogin == 1) {
				document.loginForm.submit();
			} else {
				alert("로그인 실패. 입력하신 정보를 확인해주세요.");
				return;
			}
		},
		error : function(request, status, error) {
			alert("codes : " + request.status + "\r\nmessage : "
					+ request.reponseText + "\r\nerror : " + error);
		}
	});
}

function enterkeyLogin() {
    if (window.event.keyCode == 13) {
         loginCheck();
    }
}
