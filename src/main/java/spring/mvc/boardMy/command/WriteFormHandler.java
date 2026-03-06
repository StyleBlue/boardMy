package spring.mvc.boardMy.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service("writeformhandler")
public class WriteFormHandler implements CommandHandler {
	
	@Override
	public String execute(Model m) {

		// FrontController 에서 보낸 Model에 담긴 request를 key와 value로 보내서 받았을때 Map으로 받는다.
		// 값이 문자가 올지 숫자가 올지모르니 Object로 받는다.
		Map<String, Object> map = m.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("request");
		
		// 제목글
		int num = 0;
		int ref = 1; // 그룹화 아이디
		int ref_step = 0; // 글 순서
		int ref_level = 0; // 글 레벨

		// 답변글

		// 게시글 번호가 있다면
		if (req.getParameter("num") != null) {
			num = Integer.parseInt(req.getParameter("num"));
			ref = Integer.parseInt(req.getParameter("ref"));
			ref_step = Integer.parseInt(req.getParameter("ref_step"));
			ref_level = Integer.parseInt(req.getParameter("ref_level"));

		}
		
		m.addAttribute("num", num);
		m.addAttribute("ref", ref);
		m.addAttribute("ref_step", ref_step);
		m.addAttribute("ref_level", ref_level);
		m.addAttribute("pageNum", req.getParameter("pageNum"));
		
		return "/board/writeForm";
	}
}