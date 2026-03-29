package spring.mvc.boardMy.dto;

import java.sql.Timestamp;

public class BoardDTO {
	private int num; // 湲踰덊샇
	private String writer; // ?묒꽦??
	private String passwd; // 鍮꾨?踰덊샇
	private String subject; // ?쒕ぉ
	private String content; // ?댁슜
	private int readCnt; // 議고쉶??
	private int ref; // 洹몃９???꾩씠??
	private int ref_step; // 湲?쒖꽌
	private int ref_level; // 湲?덈꺼
	private Timestamp reg_date; // ?묒꽦??
	private String ip; // ?꾩씠??
	private String org_file_name; // ?좏븫 ?먮옒 ?뚯씪紐?
	private String stored_file_name; // ?좏븫 ??ν븯??뚯씪紐?
	private long file_size; // ?좏븫 ?뚯씪 ?ш린

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

	public String getOrg_file_name() {
		return org_file_name;
	}

	public void setOrg_file_name(String org_file_name) {
		this.org_file_name = org_file_name;
	}

	public String getStored_file_name() {
		return stored_file_name;
	}

	public void setStored_file_name(String stored_file_name) {
		this.stored_file_name = stored_file_name;
	}

	public long getFile_size() {
		return file_size;
	}

	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}
}
