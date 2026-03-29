package spring.mvc.boardMy.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.boardMy.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;
	@Override
	public int insert(MemberDTO mdto) { return this.sqlSession.getMapper(MemberDAO.class).insert(mdto); }
	@Override
	public int idCheck(String memberId) { return this.sqlSession.getMapper(MemberDAO.class).idCheck(memberId); }
	@Override
	public MemberDTO getMember(String memberId) { return this.sqlSession.getMapper(MemberDAO.class).getMember(memberId); }
	@Override
	public MemberDTO loginCheck(MemberDTO mdto) { return this.sqlSession.getMapper(MemberDAO.class).loginCheck(mdto); }
}