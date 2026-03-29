package spring.mvc.boardMy.command;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
		Map<String, Object> map = m.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("request");

		int pageSize = 10;
		int pageBlock = 3;
		int cnt = 0;
		int start = 0;
		int end = 0;
		int number = 0;
		String pageNum = req.getParameter("pageNum");
		int currentPage = 0;
		int pageCount = 0;
		int startPage = 0;
		int endPage = 0;

		String searchType = safe(req.getParameter("searchType"));
		String keyword = safe(req.getParameter("keyword"));
		boolean searching = isValidSearchType(searchType) && !keyword.isEmpty();

		if (pageNum == null || pageNum.isEmpty()) {
			pageNum = "1";
		}

		currentPage = Integer.parseInt(pageNum);

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchType", searchType);
		searchMap.put("keyword", keyword);

		cnt = searching ? bdao.getSearchCount(searchMap) : bdao.getCount();
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0);

		start = (currentPage - 1) * pageSize + 1;
		end = start + pageSize - 1;
		if (end > cnt) {
			end = cnt;
		}

		number = cnt - (currentPage - 1) * pageSize;

		if (cnt > 0) {
			if (searching) {
				searchMap.put("start", start);
				searchMap.put("end", end);
				ArrayList<BoardDTO> dtos = bdao.getSearchArticles(searchMap);
				m.addAttribute("dtos", dtos);
			} else {
				Map<String, Integer> dtosMap = new HashMap<String, Integer>();
				dtosMap.put("start", start);
				dtosMap.put("end", end);
				ArrayList<BoardDTO> dtos = bdao.getArticles(dtosMap);
				m.addAttribute("dtos", dtos);
			}
		}

		startPage = (currentPage / pageBlock) * pageBlock + 1;
		if (currentPage % pageBlock == 0) {
			startPage -= pageBlock;
		}

		endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}

		m.addAttribute("cnt", cnt);
		m.addAttribute("number", number);
		m.addAttribute("pageNum", pageNum);
		m.addAttribute("searchType", searchType);
		m.addAttribute("keyword", keyword);
		m.addAttribute("searching", searching);
		m.addAttribute("searchQuery", buildSearchQuery(searchType, keyword));

		if (cnt > 0) {
			m.addAttribute("currentPage", currentPage);
			m.addAttribute("startPage", startPage);
			m.addAttribute("endPage", endPage);
			m.addAttribute("pageCount", pageCount);
			m.addAttribute("pageBlock", pageBlock);
		}

		return "/board/list";
	}

	private String safe(String value) {
		return value == null ? "" : value.trim();
	}

	private boolean isValidSearchType(String searchType) {
		return "subject".equals(searchType) || "writer".equals(searchType) || "content".equals(searchType);
	}

	private String buildSearchQuery(String searchType, String keyword) {
		if (!isValidSearchType(searchType) || keyword.isEmpty()) {
			return "";
		}
		try {
			return "&searchType=" + URLEncoder.encode(searchType, "UTF-8") + "&keyword="
					+ URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}