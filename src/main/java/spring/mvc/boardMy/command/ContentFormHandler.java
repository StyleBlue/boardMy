package spring.mvc.boardMy.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.boardMy.dao.BoardDAO;
import spring.mvc.boardMy.dto.BoardDTO;

@Service("contentformhandler")
public class ContentFormHandler implements CommandHandler {

	@Autowired
	BoardDAO bdao;
	
	@Override
	public String execute(Model m) {
		
		Map<String, Object> map = m.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("request");
		
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		int number = Integer.parseInt(req.getParameter("number"));

		// 상세 화면 진입 시 조회수를 증가시키고, 증가된 값을 다시 조회한다.
		bdao.addReadCnt(num);
		BoardDTO bdto = bdao.getArticle(num);
		
		m.addAttribute("num", num);
		m.addAttribute("pageNum", pageNum);
		m.addAttribute("bdto", bdto);
		m.addAttribute("number", number);
		
		return "/board/contentForm";
	}
}
