package spring.mvc.boardMy.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.boardMy.dao.BoardDAO;
import spring.mvc.boardMy.dto.BoardDTO;

@Service("modifyprohandler")
public class ModifyProHandler implements CommandHandler {

	@Autowired
	BoardDAO bdao;
	
	@Override
	public String execute(Model m) {
		
		// FrontController 에서 보낸 Model에 담긴 request를 key와 value로 보내서 받았을때 Map으로 받는다.
		// 값이 문자가 올지 숫자가 올지모르니 Object로 받는다.
		Map<String, Object> map = m.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("request");
		
		@SuppressWarnings("unused")
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		
		// 1. 바구니 생성
			BoardDTO bdto = new BoardDTO();
		// 2. 바구니에 화면으로 부터 받은값을 담는다.
			bdto.setNum(Integer.parseInt(req.getParameter("num")));
			bdto.setSubject(req.getParameter("subject"));
			bdto.setContent(req.getParameter("content"));
			bdto.setPasswd(req.getParameter("passwd"));
			
			int cnt = bdao.update(bdto); // 게시글 수정
			
		if (cnt == 1) {
			m.addAttribute("cnt", cnt);
			m.addAttribute("pageNum", pageNum);
		}
					
		return "/board/modifyPro";
	}
}