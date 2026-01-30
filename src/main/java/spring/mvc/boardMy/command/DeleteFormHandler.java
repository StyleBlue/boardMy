package spring.mvc.boardMy.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service("deleteformhandler")
public class DeleteFormHandler implements CommandHandler {

	@Override
	public String execute(Model m) {
		
		// FrontController 에서 보낸 Model에 담긴 request를 key와 value로 보내서 받았을때 Map으로 받는다.
		// 값이 문자가 올지 숫자가 올지모르니 Object로 받는다.
		Map<String, Object> map = m.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("request");
		
		m.addAttribute("num", req.getParameter("num"));
		m.addAttribute("pageNum", req.getParameter("pageNum"));
		
		return "/board/deleteForm";
	}
}