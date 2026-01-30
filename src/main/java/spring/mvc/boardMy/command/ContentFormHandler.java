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
		
		// FrontController 에서 보낸 Model에 담긴 request를 key와 value로 보내서 받았을때 Map으로 받는다.
		// 값이 문자가 올지 숫자가 올지모르니 Object로 받는다.
		Map<String, Object> map = m.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("request");
		
		// get 방식이나 input으로 값을 넘길경우 getParameter로 받는다.
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		int number = Integer.parseInt(req.getParameter("number")); // DB글번호가아닌 현재의 넘버링한 글번호
		
		BoardDTO bdto = bdao.getArticle(num);
		
		// 내가 쓴글이 아닌것만 조회수 증가
		
		if (!req.getRemoteAddr().equals(bdto.getIp())) {
			bdao.addReadCnt(num);
		}
		
		m.addAttribute("num", num);
		m.addAttribute("pageNum", pageNum);
		m.addAttribute("bdto", bdto);
		m.addAttribute("number", number);
		
		return "/board/contentForm";
	}
}