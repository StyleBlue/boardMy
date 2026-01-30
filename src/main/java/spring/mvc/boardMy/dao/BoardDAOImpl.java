package spring.mvc.boardMy.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.boardMy.dto.BoardDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSession sqlSession;

	// 게시글 건수 조회
	@Override
	public int getCount() {
		int cnt = 0;

		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		cnt = iDao.getCount();

		return cnt;
	}

	// 게시글 목록 조회
	@Override
	public ArrayList<BoardDTO> getArticles(Map<String, Integer> dtosMap) {

		@SuppressWarnings("unused")
		BoardDTO bdto = new BoardDTO();
		ArrayList<BoardDTO> articles = null;

		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		articles = iDao.getArticles(dtosMap);

		return articles;
	}

	// 글쓰기 - 제목글인 경우 (최대값 구하는 메소드)
	@Override
	public int getMaxNum() {
		int maxNum = 0;
		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		maxNum = iDao.getMaxNum();

		return maxNum;
	}

	// 글쓰기 - 답변글인 경우
	public void updateReply(BoardDTO bdto) {

		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		iDao.updateReply(bdto);

	}

	// 글작성
	@Override
	public int insert(BoardDTO bdto) {
		int cnt = 0;

		int num = bdto.getNum();
		int ref = bdto.getRef();
		int ref_step = bdto.getRef_step();
		int ref_level = bdto.getRef_level();

		if (num == 0) {
			cnt = getCount(); // 글 개수 가져오는 메소드

			// 글쓰기 - 답변글인 경우
			if (cnt > 0) {
				// ref = rs.getInt(1) +1; // ref: 그룹화 아이디 = 글번호 최대값 + 1
				ref = getMaxNum() + 1;

			} else {
				ref = 1;
			}

			bdto.setRef(ref);
			ref_step = 0;
			ref_level = 0;

		} else {
			updateReply(bdto); // 메소드 생성

			ref_step++;
			ref_level++;
		}

		bdto.setRef_step(ref_step);
		bdto.setRef_level(ref_level);

		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		cnt = iDao.insert(bdto);

		return cnt;
	}

	// 상세 페이지, 수정내역 페이지
	@Override
	public BoardDTO getArticle(int num) {

		BoardDTO bdto = new BoardDTO();

		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		bdto = iDao.getArticle(num);

		return bdto;
	}

	@Override
	public void addReadCnt(int num) {

		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		iDao.addReadCnt(num);

	}

	@Override
	public int pwdCheck(Map<String, Object> dtosMap) {
		int cnt = 0;

		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		cnt = iDao.pwdCheck(dtosMap);

		/*if (cnt > 0) {
			cnt = 1;
		} else {
			cnt = 0;
		}*/
		return cnt;
	}

	// 게시글 수정
	@Override
	public int update(BoardDTO bdto) {
		int cnt = 0;

		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		cnt = iDao.update(bdto);

		return cnt;
	}

	// 게시글 삭제

	@Override
	public int checkReply(BoardDTO bdto) {
		int cnt = 0;
		
		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		cnt = iDao.checkReply(bdto); // xml에서 resultType="int" , SELECT COUNT(*)
		
		return cnt;
	}

	@Override
	public void updateRef_step(BoardDTO bdto) {
		
		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		iDao.updateRef_step(bdto);
	}
	
	@Override
	public int delete(int num) { // int num 변수명 달라도 상관없음.
		int cnt = 0;
		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		
		BoardDTO bdto = getArticle(num); // 글 개수 가져오는 메소드
		
		int chkRepCnt = checkReply(bdto);
		
		// 답글이 있는 경우
		if(chkRepCnt > 0) {
			cnt = -1;
		
		// 답글이 없는 경우
		} else {
			updateRef_step(bdto);
			
			iDao.delete(num);
		}
		return cnt;
	}
}