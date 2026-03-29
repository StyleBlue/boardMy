package spring.mvc.boardMy.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.boardMy.dao.MemberDAO;
import spring.mvc.boardMy.dto.MemberDTO;
import spring.mvc.boardMy.util.PasswordUtil;

@Service("loginprohandler")
public class LoginProHandler implements CommandHandler {

	@Autowired
	private MemberDAO mdao;

	@Override
	public String execute(Model m) {
		Map<String, Object> map = m.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("request");
		String memberId = safe(req.getParameter("member_id"));
		String passwd = safe(req.getParameter("passwd"));
		String returnUrl = normalizeReturnUrl(req.getParameter("returnUrl"));

		if (memberId.isEmpty() || passwd.isEmpty()) {
			m.addAttribute("returnUrl", returnUrl);
			m.addAttribute("member_id", memberId);
			m.addAttribute("error", "아이디와 비밀번호를 입력하세요.");
			return "/member/loginForm";
		}

		MemberDTO input = new MemberDTO();
		input.setMember_id(memberId);
		input.setPasswd(PasswordUtil.sha256(passwd));
		MemberDTO loginMember = mdao.loginCheck(input);

		if (loginMember == null) {
			m.addAttribute("returnUrl", returnUrl);
			m.addAttribute("member_id", memberId);
			m.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
			return "/member/loginForm";
		}

		HttpSession session = req.getSession();
		session.setAttribute("loginMemberId", loginMember.getMember_id());
		session.setAttribute("loginMemberName", loginMember.getMember_name());
		return "redirect:" + returnUrl;
	}

	private String safe(String value) {
		return value == null ? "" : value.trim();
	}

	private String normalizeReturnUrl(String returnUrl) {
		if (returnUrl == null || returnUrl.trim().isEmpty()) {
			returnUrl = "list";
		}
		if (returnUrl.startsWith("http://") || returnUrl.startsWith("https://") || returnUrl.startsWith("//")) {
			return "list";
		}
		return returnUrl;
	}
}