var insertError = "글 작성에 실패했습니다.\n다시 시도해 주세요.";
var passwdError = "비밀번호가 일치하지 않습니다.\n다시 시도해 주세요.";
var updateError = "";
var msg_writer = "작성자를 확인해 주세요.";
var msg_subject = "제목을 입력해 주세요.";
var msg_passwd = "비밀번호를 입력해 주세요.";
var replyError = "답글이 있는 글은 삭제할 수 없습니다.";
var deleteError = "글 삭제에 실패했습니다.\n다시 시도해 주세요.";
var modifyError = "수정에 실패했습니다.\n다시 시도해 주세요.";

function writeFocus() {
	if (document.writeform && document.writeform.subject) {
		document.writeform.subject.focus();
	}
}

function writeCheck() {
	if (!document.writeform.writer.value) {
		alert(msg_writer);
		return false;
	}
	if (!document.writeform.subject.value) {
		alert(msg_subject);
		document.writeform.subject.focus();
		return false;
	}
	if (!document.writeform.passwd.value) {
		alert(msg_passwd);
		document.writeform.passwd.focus();
		return false;
	}
	return true;
}

function loginCheck() {
	if (!document.loginform.member_id.value) {
		alert("아이디를 입력해 주세요.");
		document.loginform.member_id.focus();
		return false;
	}
	if (!document.loginform.passwd.value) {
		alert(msg_passwd);
		document.loginform.passwd.focus();
		return false;
	}
	return true;
}

function registerCheck() {
	if (!document.registerform.member_id.value) {
		alert("아이디를 입력해 주세요.");
		document.registerform.member_id.focus();
		return false;
	}
	if (!document.registerform.member_name.value) {
		alert("이름을 입력해 주세요.");
		document.registerform.member_name.focus();
		return false;
	}
	if (!document.registerform.passwd.value) {
		alert(msg_passwd);
		document.registerform.passwd.focus();
		return false;
	}
	if (document.registerform.passwd.value !== document.registerform.passwd_confirm.value) {
		alert("비밀번호 확인이 일치하지 않습니다.");
		document.registerform.passwd_confirm.focus();
		return false;
	}
	return true;
}

function errorAlert(msg) {
	alert(msg);
}

function passwdFocus() {
	if (document.passwordform && document.passwordform.passwd) {
		document.passwordform.passwd.focus();
	}
}

function passwdCheck() {
	if (!document.passwordform || !document.passwordform.passwd.value) {
		alert(msg_passwd);
		if (document.passwordform && document.passwordform.passwd) {
			document.passwordform.passwd.focus();
		}
		return false;
	}
	return true;
}

function modifyFocus() {
	document.modifyform.subject.focus();
}

function modifyCheck() {
	if (!document.modifyform.subject.value) {
		alert(msg_subject);
		document.modifyform.subject.focus();
		return false;
	}
	if (!document.modifyform.passwd.value) {
		alert(msg_passwd);
		document.modifyform.passwd.focus();
		return false;
	}
	return true;
}