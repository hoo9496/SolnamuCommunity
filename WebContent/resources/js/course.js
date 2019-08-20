var pattern_num = /[0-9]/; // 숫자 체크
var pattern_eng = /[a-zA-Z]/; // 문자 체크
var pattern_spc = /[`~!@#$%^&*|\\\'\";:\/?]/gi; // 특수문자 체크
var pattern_kor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/; // 한글 체크
var pattern_blank = /[\s]/g; // 공백 체크

var code;
var courseName;

/*** 강좌등록 폼 유효성 ***/

// 강좌코드 체크
function courseCodeCk() {

	$.ajax({
		url : "codeCheck.do",
		type : "POST",
		data : { code : $(".code").val() },
		dataType : "json",
		success : function(codeCheckResult) {
			
			if (document.courseWriteForm.code.value == "" || document.courseWriteForm.code.value.length < 4) {
				alert("강좌코드를 확인하세요.");
				$(".code").focus();
				return;
			}
			if (pattern_kor.test(document.courseWriteForm.code.value) == true) {
				alert("강좌코드는 숫자만 사용가능합니다.");
				$(".code").focus();
				return;
			}
			if (pattern_spc.test(document.courseWriteForm.code.value) == true) {
				alert("강좌코드는 숫자만 사용가능합니다.");
				$(".code").focus();
				return;
			}
			if (pattern_blank.test(document.courseWriteForm.code.value) == true) {
				alert("강좌코드는 숫자만 사용가능합니다.");
				$(".code").focus();
				return;
			}
			if (pattern_eng.test(document.courseWriteForm.code.value) == true) {
				alert("강좌코드는 숫자만 사용가능합니다.");
				$(".code").focus();
				return;
			}
			if (codeCheckResult.checkCode > 0) {
				alert("이미 존재하는 강좌코드입니다. 다른 강좌코드를 입력해주세요.");
				$(".code").focus();
				return;
			}
			
			code = $(".code").val();
			courseNameCk();
			
		},
		error : function(request, status, error) {
			alert("codes : " + request.status + "\r\nmessage : "
					+ request.reponseText + "\r\nerror : " + error);
		}
	});
}

//강좌명 체크
function courseNameCk() {

	$.ajax({
		url : "courNameCheck.do",
		type : "POST",
		data : { cour_name : $(".cour_name").val() },
		dataType : "json",
		success : function(courNameCheckResult) {
			
			if (document.courseWriteForm.cour_name.value == "") {
				alert("강좌명을 입력하세요");
				$(".cour_name").focus();
				return;
			} 
			if (courNameCheckResult.checkCourName > 0) {
				alert("이미 존재하는 강좌명입니다. 다른 강좌명을 입력해주세요.");
				$(".cour_name").focus();
				return;
			} 
			
			courseName = $(".cour_name").val();	
			courseCheck();
			
		},
		error : function(request, status, error) {
			alert("codes : " + request.status + "\r\nmessage : "
					+ request.reponseText + "\r\nerror : " + error);
		}
	});
}

// 강좌 추가 유효성 검사
function courseCheck() {
	
	var codeConfirm = document.courseWriteForm.code.value;
	var courNameConfirm = document.courseWriteForm.cour_name.value;
	
	if (document.courseWriteForm.start_date.value == "") {
		alert("시작날짜를 입력하세요");
		$(".start_date").focus();
		return;
	}
	if (pattern_kor.test(document.courseWriteForm.start_date.value) == true || pattern_eng.test(document.courseWriteForm.start_date.value) == true ||
			 pattern_blank.test(document.courseWriteForm.start_date.value) == true) {
		alert("시작날짜는 숫자만 입력 가능합니다.");
		$(".start_date").focus();
		return;
	}
	if (document.courseWriteForm.start_date.value.length < 8) {
		alert("시작날짜 8자리를 정확히 입력하세요");
		$(".start_date").focus();
		return;
	}
	if (document.courseWriteForm.end_date.value == "") {
		alert("종료날짜를 입력하세요");
		$(".end_date").focus();
		return;
	}
	if (pattern_kor.test(document.courseWriteForm.end_date.value) == true || pattern_eng.test(document.courseWriteForm.end_date.value) == true ||
			 pattern_blank.test(document.courseWriteForm.end_date.value) == true) {
		alert("종료날짜는 숫자만 입력 가능합니다.");
		$(".end_date").focus();
		return;
	}
	if (document.courseWriteForm.end_date.value.length < 8) {
		alert("종료날짜 8자리를 정확히 입력하세요");
		$(".end_date").focus();
		return;
	}
	
	if (code != codeConfirm) {
		courseCodeCk();
		return;
	}
	if (courseName != courNameConfirm) {
		courseNameCk();
		return;
	}
	
	document.courseWriteForm.submit();
}


/*** 강좌수정 폼 유효성 ***/

//강좌명 체크
function updateCourseNameCk() {

	$.ajax({
		url : "courNameCheck.do",
		type : "POST",
		data : { cour_name : $(".cour_name").val() },
		dataType : "json",
		success : function(courNameCheckResult) {
			
			if (document.courseUpdateForm.cour_name.value == "") {
				alert("강좌명을 입력하세요");
				$(".cour_name").focus();
				return;
			} 
			if (courNameCheckResult.checkCourName > 0) {
				alert("이미 존재하는 강좌명입니다. 다른 강좌명을 입력해주세요.");
				$(".cour_name").focus();
				return;
			} 
			
			courseName = $(".cour_name").val();	
			courseUpdateCheck();
			
		},
		error : function(request, status, error) {
			alert("codes : " + request.status + "\r\nmessage : "
					+ request.reponseText + "\r\nerror : " + error);
		}
	});
}

// 강좌 수정 유효성 검사
function courseUpdateCheck() {
	
	var courNameConfirm = $(".cour_name").val();	
	
	if (document.courseUpdateForm.cour_name.value == "") {
		alert("강좌명을 확인해주세요.");
		$(".cour_name").focus();
		return;
	}
	if (document.courseUpdateForm.start_date.value == "") {
		alert("시작날짜를 입력하세요");
		$(".start_date").focus();
		return;
	}
	if (pattern_kor.test(document.courseUpdateForm.start_date.value) == true || pattern_eng.test(document.courseUpdateForm.start_date.value) == true ||
			 pattern_blank.test(document.courseUpdateForm.start_date.value) == true) {
		alert("시작날짜는 숫자만 입력 가능합니다.");
		$(".start_date").focus();
		return;
	}
	if (document.courseUpdateForm.start_date.value.length < 8) {
		alert("시작날짜 8자리를 정확히 입력하세요");
		$(".start_date").focus();
		return;
	}
	if (document.courseUpdateForm.end_date.value == "") {
		alert("종료날짜를 입력하세요");
		$(".end_date").focus();
		return;
	}
	if (pattern_kor.test(document.courseUpdateForm.end_date.value) == true || pattern_eng.test(document.courseUpdateForm.end_date.value) == true ||
			 pattern_blank.test(document.courseUpdateForm.end_date.value) == true) {
		alert("종료날짜는 숫자만 입력 가능합니다.");
		$(".end_date").focus();
		return;
	}
	if (document.courseUpdateForm.end_date.value.length < 8) {
		alert("종료날짜 8자리를 정확히 입력하세요");
		$("#end_date").focus();
		return;
	}
	
	if (courseName != courNameConfirm) {
		updateCourseNameCk();
		return;
	}
	
	document.courseUpdateForm.submit();
}