package spring.mvc.boardMy.dto;

import java.sql.Timestamp;

public class MemberDTO {
	private String member_id;
	private String member_name;
	private String passwd;
	private Timestamp reg_date;

	public String getMember_id() { return member_id; }
	public void setMember_id(String member_id) { this.member_id = member_id; }
	public String getMember_name() { return member_name; }
	public void setMember_name(String member_name) { this.member_name = member_name; }
	public String getPasswd() { return passwd; }
	public void setPasswd(String passwd) { this.passwd = passwd; }
	public Timestamp getReg_date() { return reg_date; }
	public void setReg_date(Timestamp reg_date) { this.reg_date = reg_date; }
}