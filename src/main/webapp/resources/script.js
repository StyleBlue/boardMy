
var insertError = "글 작성에 실패했습니다. \n 잠시후 다시 시도하세요.";
var passwdError = "비밀번호가 일치하지않습니다. \n 잠시후 다시 시도하세요.";
var updateError = "";
var msg_writer = "이름을 입력하세요";
var msg_subject = "제목을 입력하세요";
var msg_passwd = "비밀번호 입력을 확인해주세요.";
var replyError = "답글이 있는 글은 삭제할 수 없습니다.";
var deleteError = "글 삭제에 실패 하였습니다. \n 잠시후 다시 시도하세요.";
var modifyError = "수정에 실패 하였습니다. \n 잠시후 다시 시도하세요.";
	
function writeFocus(msg) {
	document.writeform.writer.focus();
}

function writeCheck() {
	if(!document.writeform.writer.value) {
		alert(msg_writer);
		document.writeform.writer.focus();
	} else if(!document.writeform.subject.value) {
		alert(msg_subject);
		document.writeform.subject.focus();
	}
}

function errorAlert(msg){
	alert(msg);
}

// 게시글 수정
function passwdFocus() {
	document.passwordform.passwd.focus();
}

function passwdCheck() {
	if(!document.passwordform.passwd.value) {
		alert(msg_passwd);
		return false;
	}
}

function modifyFoucs() {
	document.modifyform.subject.focus();
}

function modifyCheck() {
	if (!document.modifyform.subject.value) {
		alert(msg_subject);
		document.modifyform.subject.focus();
		return false;
	} else if (!document.modifyform.passwd.value) {
		alert(msg_passwd);
		document.modifyform.passwd.focus();
		return false;
	}
}