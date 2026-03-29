package spring.mvc.boardMy.command;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service("writeformhandler")
public class WriteFormHandler implements CommandHandler {

	@Override
	public String execute(Model m) {
		Map<String, Object> map = m.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("request");
		HttpSession session = req.getSession(false);

		if (session == null || session.getAttribute("loginMemberId") == null) {
			String query = req.getQueryString();
			String returnUrl = "writeForm" + (query == null || query.isEmpty() ? "" : "?" + query);
			return "redirect:loginForm?returnUrl=" + encode(returnUrl);
		}

		int num = 0;
		int ref = 1;
		int ref_step = 0;
		int ref_level = 0;

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

	private String encode(String value) {
		try {
			return URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "list";
		}
	}
}