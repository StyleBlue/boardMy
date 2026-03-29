package spring.mvc.boardMy.dao;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.boardMy.dto.BoardDTO;

public interface BoardDAO {
	public int getCount();
	public int getSearchCount(Map<String, Object> searchMap);
	public ArrayList<BoardDTO> getArticles(Map<String, Integer> dtosMap);
	public ArrayList<BoardDTO> getSearchArticles(Map<String, Object> searchMap);
	public BoardDTO getArticle(int num);
	public void addReadCnt(int num);
	public int insert(BoardDTO bdto);
	public int getMaxNum();
	public int getMaxRefStep(int ref);
	public int getNextRefStep(BoardDTO bdto);
	public void updateReply(BoardDTO bdto);
	public int pwdCheck(Map<String, Object> dtosMap);
	public int update(BoardDTO bdto);
	public int checkReply(BoardDTO bdto);
	public void updateRef_step(BoardDTO bdto);
	public int delete(int num);
}