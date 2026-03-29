package spring.mvc.boardMy.command;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service("loginformhandler")
public class LoginFormHandler implements CommandHandler {
	@Override
	public String execute(Model m) {
		Map<String, Object> map = m.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("request");
		m.addAttribute("returnUrl", normalizeReturnUrl(req.getParameter("returnUrl")));
		m.addAttribute("registered", req.getParameter("registered"));
		return "/member/loginForm";
	}
	private String normalizeReturnUrl(String returnUrl) {
		if (returnUrl == null || returnUrl.trim().isEmpty()) { returnUrl = "list"; }
		if (returnUrl.startsWith("http://") || returnUrl.startsWith("https://") || returnUrl.startsWith("//")) { return "list"; }
		return returnUrl;
	}
}