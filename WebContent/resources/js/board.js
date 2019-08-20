// 글쓰기폼 유효성 검사
function writeCheck() {
	if (document.writeForm.subject.value == "") {
		alert("제목을 입력하세요.");
		$("#subject").focus();
		return;
	} 
	if (document.writeForm.contents.value == "") {
		alert("내용을 입력하세요.");
		$("#contents").focus();
		return;
	}
	var str = document.writeForm.contents.value;
	str = str.replace(/(?:\r\n|\r|\n)/g, '<br/>');
	document.writeForm.contents.value = str;
	document.writeForm.submit();
}
// 글수정폼 유효성 검사
function updateWriteCheck() {
	if (document.updateWriteForm.subject.value == "") {
		alert("제목을 입력하세요.");
		$("#subject").focus();
		return;
	} 
	if (document.updateWriteForm.contents.value == "") {
		alert("내용을 입력하세요.");
		$("#contents").focus();
		return;
	}
	var str = document.updateWriteForm.contents.value;
	str = str.replace(/(?:\r\n|\r|\n)/g, '<br/>');
	document.updateWriteForm.contents.value = str;
	document.updateWriteForm.submit();
}
// 파일 검사 ajax
function writeFileCk(){
	$.ajax({
		url : "fileCheck.do",
		type : "POST",
		data : { filename : $("#txt_file").val() },
		dataType : "json",
		success : function(fileCheckResult) {
			if(fileCheckResult.flag == 1){
				$("#txt_file").focus();
				alert("업로드가 불가능한 파일입니다. \n" +
					  "이미지 파일만 첨부해 주세요.(jpg, png..)");
				return;
			}
			writeCheck();
		},
		error : function(request, status, error) {
			alert("codes : " + request.status + "\r\nmessage : "
					+ request.reponseText + "\r\nerror : " + error);
		}
	});
}
function updateFileCk(){
	$.ajax({
		url : "fileCheck.do",
		type : "POST",
		data : { filename : $("#txt_file").val() },
		dataType : "json",
		success : function(fileCheckResult) {
			if(fileCheckResult.flag == 1){
				$("#txt_file").focus();
				alert("업로드가 불가능한 파일입니다. \n" +
					  "이미지 파일만 첨부해 주세요.(jpg, png..)");
				return;
			}
			updateWriteCheck();
		},
		error : function(request, status, error) {
			alert("codes : " + request.status + "\r\nmessage : "
					+ request.reponseText + "\r\nerror : " + error);
		}
	});
}
function community_deleteCrrentFile(){
	$.ajax({
		url : "community_fileDelete.do",
		type : "POST",
		data : { filename : $("#prev_file").html() },
		dataType : "json",
		success : function(fileDelResult) {
			if(fileDelResult.flag == 1){
				$('#prev_file').hide();
				$('#del_file').hide();
			}
			else{
				alert('삭제실패');
			}
		},
		error : function(request, status, error) {
			alert("codes : " + request.status + "\r\nmessage : "
					+ request.reponseText + "\r\nerror : " + error);
		}
	});
}
function debug_deleteCrrentFile(){
	$.ajax({
		url : "debug_fileDelete.do",
		type : "POST",
		data : { filename : $("#prev_file").html() },
		dataType : "json",
		success : function(fileDelResult) {
			if(fileDelResult.flag == 1){
				$('#prev_file').hide();
				$('#del_file').hide();
			}
			else{
				alert('삭제실패');
			}
		},
		error : function(request, status, error) {
			alert("codes : " + request.status + "\r\nmessage : "
					+ request.reponseText + "\r\nerror : " + error);
		}
	});
}
// 오류게시판 리스트 불러오기
function debugBoardList() {
	document.location.href = 'debug_board_list.do?offset=0&pagelink=1';
}

//공지게시판 리스트 불러오기
function noticeBoardList() {
	document.location.href = 'notice_board_list.do?offset=0&pagelink=1';
}

// 글 검색
function searchCheck() {
	if (document.searchForm.search.value == "") {
		alert("검색내용을 입력하세요.");
		$("#search").focus();
		return;
	}
	document.searchForm.submit();
}

function enterkey() {
    if (window.event.keyCode == 13) {
         searchCheck();
    }
}