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

	@Override
	public int getCount() {
		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		return iDao.getCount();
	}

	@Override
	public int getSearchCount(Map<String, Object> searchMap) {
		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		return iDao.getSearchCount(searchMap);
	}

	@Override
	public ArrayList<BoardDTO> getArticles(Map<String, Integer> dtosMap) {
		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		return iDao.getArticles(dtosMap);
	}

	@Override
	public ArrayList<BoardDTO> getSearchArticles(Map<String, Object> searchMap) {
		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		return iDao.getSearchArticles(searchMap);
	}

	@Override
	public int getMaxNum() {
		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		return iDao.getMaxNum();
	}

	@Override
	public int getMaxRefStep(int ref) {
		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		return iDao.getMaxRefStep(ref);
	}

	@Override
	public int getNextRefStep(BoardDTO bdto) {
		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		return iDao.getNextRefStep(bdto);
	}

	@Override
	public void updateReply(BoardDTO bdto) {
		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		iDao.updateReply(bdto);
	}

	@Override
	public int insert(BoardDTO bdto) {
		int cnt = 0;

		int num = bdto.getNum();
		int ref = bdto.getRef();
		int ref_step = bdto.getRef_step();
		int ref_level = bdto.getRef_level();

		if (num == 0) {
			cnt = getCount();

			if (cnt > 0) {
				ref = getMaxNum() + 1;
			} else {
				ref = 1;
			}

			bdto.setRef(ref);
			ref_step = 0;
			ref_level = 0;
		} else {
			int nextRefStep = getNextRefStep(bdto);
			if (nextRefStep == 0) {
				ref_step = getMaxRefStep(ref) + 1;
			} else {
				ref_step = nextRefStep;
				bdto.setRef_step(ref_step);
				updateReply(bdto);
			}
			ref_level++;
		}

		bdto.setRef_step(ref_step);
		bdto.setRef_level(ref_level);

		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		cnt = iDao.insert(bdto);
		return cnt;
	}

	@Override
	public BoardDTO getArticle(int num) {
		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		return iDao.getArticle(num);
	}

	@Override
	public void addReadCnt(int num) {
		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		iDao.addReadCnt(num);
	}

	@Override
	public int pwdCheck(Map<String, Object> dtosMap) {
		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		return iDao.pwdCheck(dtosMap);
	}

	@Override
	public int update(BoardDTO bdto) {
		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		return iDao.update(bdto);
	}

	@Override
	public int checkReply(BoardDTO bdto) {
		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		return iDao.checkReply(bdto);
	}

	@Override
	public void updateRef_step(BoardDTO bdto) {
		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		iDao.updateRef_step(bdto);
	}
	
	@Override
	public int delete(int num) {
		int cnt = 0;
		BoardDAO iDao = this.sqlSession.getMapper(BoardDAO.class);
		BoardDTO bdto = getArticle(num);
		int chkRepCnt = checkReply(bdto);
		
		if (chkRepCnt > 0) {
			cnt = -1;
		} else {
			updateRef_step(bdto);
			iDao.delete(num);
		}
		return cnt;
	}
}