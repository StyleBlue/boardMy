package spring.mvc.boardMy.dao;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.boardMy.dto.BoardDTO;

public interface BoardDAO {
	
public int getCount(); // 게시글 건수

public ArrayList<BoardDTO> getArticles(Map<String, Integer> dtosMap); // 게시글 목록 조회

public BoardDTO getArticle(int num); // 상세페이지

public void addReadCnt(int num); // 내가 쓴글이 아닌것만 조회수 증가

public int insert(BoardDTO bdto); // 게시글 작성

public int getMaxNum(); // 글쓰기 - 제목글인 경우 (inset 메소드에서 출시)

public void updateReply(BoardDTO bdto); // 글쓰기 - 답변글인 경우

public int pwdCheck(Map<String, Object> dtosMap); // 비밀번호 확인

public int update(BoardDTO bdto); // 게시글 수정

public int checkReply(BoardDTO bdto);

public void updateRef_step(BoardDTO bdto);

public int delete(int num); // 게시글 삭제

}
