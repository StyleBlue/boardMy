package spring.mvc.boardMy.command;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service("logouthandler")
public class LogoutHandler implements CommandHandler {
	@Override
	public String execute(Model m) {
		Map<String, Object> map = m.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("request");
		if (req.getSession(false) != null) { req.getSession(false).invalidate(); }
		return "redirect:list";
	}
}