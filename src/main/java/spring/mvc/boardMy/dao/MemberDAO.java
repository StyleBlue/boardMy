package spring.mvc.boardMy.dao;

import spring.mvc.boardMy.dto.MemberDTO;

public interface MemberDAO {
	int insert(MemberDTO mdto);
	int idCheck(String memberId);
	MemberDTO getMember(String memberId);
	MemberDTO loginCheck(MemberDTO mdto);
}