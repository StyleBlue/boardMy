package spring.mvc.boardMy.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.boardMy.dao.BoardDAO;
import spring.mvc.boardMy.dto.BoardDTO;

@Service("modifyviewhandler")
public class ModifyViewHandler implements CommandHandler {
	
	@Autowired
	BoardDAO mdao;
	
	@Override
	public String execute(Model m) {
		
		// FrontController 에서 보낸 Model에 담긴 request를 key와 value로 보내서 받았을때 Map으로 받는다.
		// 값이 문자가 올지 숫자가 올지모르니 Object로 받는다.
		Map<String, Object> map = m.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("request");
		
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum	= Integer.parseInt(req.getParameter("pageNum"));
		String passwd = req.getParameter("passwd");
		
		Map<String, Object> dtosMap = new HashMap<String, Object>();
		dtosMap.put("num", num);
		dtosMap.put("passwd", passwd);
		
		// 패스워드가 일치하면 cnt = 1, 패스워드가 일치하지 않으면 cnt = 0;
		int cnt = mdao.pwdCheck(dtosMap);
		
		if (cnt == 1) {
			BoardDTO bdto = mdao.getArticle(num);
			m.addAttribute("bdto", bdto);
			m.addAttribute("cnt", cnt);
			m.addAttribute("num", num);
			m.addAttribute("pageNum", pageNum);
		} else {
			return "/board/fail";
		}
		return "/board/modifyView";
	}
}