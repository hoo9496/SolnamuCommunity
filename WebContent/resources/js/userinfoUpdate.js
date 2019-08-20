var pattern_num = /[0-9]/; // 숫자 체크
var pattern_eng = /[a-zA-Z]/; // 문자 체크
var pattern_spc = /[`~!@#$%^&*|\\\'\";:\/?]/gi; // 특수문자 체크
var pattern_kor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/; // 한글 체크
var pattern_blank = /[\s]/g; // 공백 체크

// 회원정보 수정 체크
function userInfoUpdateCheck() {
	
	if (document.userInfoUpdateForm.pw.value == "") {
		alert("비밀번호를 입력하세요");
		document.userInfoUpdateForm.pw.focus();
		return;
	}
	if (document.userInfoUpdateForm.pw.value.length < 6) {
		alert("비밀번호를 6~12자리 사이로 입력하세요");
		document.userInfoUpdateForm.pw.focus();
		return;
	}
	if (document.userInfoUpdateForm.rePw.value == "") {
		alert("비밀번호 확인을 입력하세요");
		document.userInfoUpdateForm.rePw.focus();
		return;
	}
	if (document.userInfoUpdateForm.birthday.value == "") {
		alert("생년월일을 입력하세요");
		document.userInfoUpdateForm.birthday.focus();
		return;
	}
	if (pattern_kor.test(document.userInfoUpdateForm.birthday.value) == true || pattern_eng.test(document.userInfoUpdateForm.birthday.value) == true ||
			 pattern_blank.test(document.userInfoUpdateForm.birthday.value) == true) {
		alert("생년월일은 숫자만 입력 가능합니다.");
		document.userInfoUpdateForm.birthday.focus();
		return;
	}
	if (document.userInfoUpdateForm.birthday.value.length < 8) {
		alert("생년월일 8자리를 정확히 입력하세요");
		document.userInfoUpdateForm.birthday.focus();
		return;
	}
	if (document.userInfoUpdateForm.pw.value != document.userInfoUpdateForm.rePw.value) {
		alert("비밀번호가 같지 않습니다. 비밀번호를 다시 확인해주세요.");
		document.userInfoUpdateForm.rePw.focus();
		return;
	}
	document.userInfoUpdateForm.submit();
	
}