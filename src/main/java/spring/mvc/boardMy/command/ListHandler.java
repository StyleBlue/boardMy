package spring.mvc.boardMy.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.boardMy.dao.BoardDAO;
import spring.mvc.boardMy.dto.BoardDTO;

@Service("listhandler")
public class ListHandler implements CommandHandler {
	
	@Autowired
	BoardDAO bdao;
	
	@Override
	public String execute(Model m) {

		// FrontController 에서 보낸 Model에 담긴 request를 key와 value로 보내서 받았을때 Map으로 받는다.
		// 값이 문자가 올지 숫자가 올지모르니 Object로 받는다.
		Map<String, Object> map = m.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("request");
		
		int pageSize = 5; // 한 페이지당 출력할 게시글 수
		int pageBlock = 3; // 출력할 페이지 수
		int cnt = 0; // 글 개수
		int start = 0; // 현재 페이지 시작 rownum
		int end = 0; // 현재 페이지 끝 rownum
		int number = 0; // 출력할 글 번호
		String pageNum = null; // 페이지 번호
		int currentPage = 0; // 현재페이지
		int pageCount = 0; // 페이지 개수
		int startPage = 0; // 시작 페이지
		int endPage = 0; // 마지막 페이지

		cnt = bdao.getCount();

		pageNum = req.getParameter("pageNum"); // 페이지번호를 넘겨받는다.

		if (pageNum == null) {
			pageNum = "1";
		}

		currentPage = Integer.parseInt(pageNum);

		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0);

		start = (currentPage - 1) * pageSize + 1; // (5-1) * 10 + 1 = 41
		end = start + pageSize - 1; // 41 + 10 - 1 = 50

		if (end > cnt)
			end = cnt;

		// 글번호
		number = cnt - (currentPage - 1) * pageSize;

		if (cnt > 0) {
			
			Map<String, Integer> dtosMap = new HashMap<String, Integer>();
			dtosMap.put("start",start);
			dtosMap.put("end",end);
			
			ArrayList<BoardDTO> dtos = bdao.getArticles(dtosMap);
			m.addAttribute("dtos",dtos);
		}

		startPage = (currentPage / pageBlock) * pageBlock + 1; // (5 / 3) * 3 +
																// 1 = 4
		if (currentPage % pageBlock == 0)
			startPage -= pageBlock; // if(5 % 3)

		endPage = startPage + pageBlock - 1; // 4 + 3 - 1 = 6;
		if (endPage > pageCount)
			endPage = pageCount;

		m.addAttribute("cnt", cnt);
		m.addAttribute("number", number);
		m.addAttribute("pageNum", pageNum);

		if (cnt > 0) {
			m.addAttribute("currentPage", currentPage);
			m.addAttribute("startPage", startPage);
			m.addAttribute("endPage", endPage);
			m.addAttribute("pageCount", pageCount);
			m.addAttribute("pageBlock", pageBlock);
		}

		return "/board/list";
	}
}