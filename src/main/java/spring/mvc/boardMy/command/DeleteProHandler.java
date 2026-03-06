package spring.mvc.boardMy.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.boardMy.dao.BoardDAO;

@Service("deleteprohandler")
public class DeleteProHandler implements CommandHandler {

	@Autowired
	BoardDAO bdao;

	@Override
	public String execute(Model m) {

		// FrontController 에서 보낸 Model에 담긴 request를 key와 value로 보내서 받았을때 Map으로
		// 받는다.
		// 값이 문자가 올지 숫자가 올지모르니 Object로 받는다.
		Map<String, Object> map = m.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("request");

		int num = Integer.parseInt(req.getParameter("num"));
		String passwd = req.getParameter("passwd");
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));

		Map<String, Object> dtosMap = new HashMap<String, Object>();
		dtosMap.put("num", num);
		dtosMap.put("passwd", passwd);

		int pwdCnt = bdao.pwdCheck(dtosMap);
		System.out.println("pwdCnt: " + pwdCnt);

		if (pwdCnt == 1) {
			int deleteCnt = bdao.delete(num);
			m.addAttribute("deleteCnt", deleteCnt);
		}

		m.addAttribute("pwdCnt", pwdCnt);
		m.addAttribute("pageNum", pageNum);

		return "/board/deletePro";
	}
}