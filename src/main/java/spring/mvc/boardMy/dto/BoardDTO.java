package spring.mvc.boardMy.dto;

import java.sql.Timestamp;

public class BoardDTO {
	private int num; // 글번호
	private String writer; // 작성자
	private String passwd; // 비밀번호
	private String subject; // 제목
	private String content; // 내용
	private int readCnt; // 조회수
	private int ref; // 그룹화 아이디
	private int ref_step; // 글순서
	private int ref_level; // 글레벨
	private Timestamp reg_date; // 작성일
	private String ip; // 아이피

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getRef_step() {
		return ref_step;
	}

	public void setRef_step(int ref_step) {
		this.ref_step = ref_step;
	}

	public int getRef_level() {
		return ref_level;
	}

	public void setRef_level(int ref_level) {
		this.ref_level = ref_level;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}