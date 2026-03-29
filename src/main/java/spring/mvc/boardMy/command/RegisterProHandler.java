package spring.mvc.boardMy.command;

import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.boardMy.dao.MemberDAO;
import spring.mvc.boardMy.dto.MemberDTO;
import spring.mvc.boardMy.util.PasswordUtil;

@Service("registerprohandler")
public class RegisterProHandler implements CommandHandler {

	@Autowired
	private MemberDAO mdao;

	@Override
	public String execute(Model m) {
		Map<String, Object> map = m.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("request");
		String memberId = safe(req.getParameter("member_id"));
		String memberName = safe(req.getParameter("member_name"));
		String passwd = safe(req.getParameter("passwd"));
		String passwdConfirm = safe(req.getParameter("passwd_confirm"));

		if (memberId.isEmpty() || memberName.isEmpty() || passwd.isEmpty() || passwdConfirm.isEmpty()) {
			m.addAttribute("member_id", memberId);
			m.addAttribute("member_name", memberName);
			m.addAttribute("error", "모든 항목을 입력하세요.");
			return "/member/registerForm";
		}
		if (!passwd.equals(passwdConfirm)) {
			m.addAttribute("member_id", memberId);
			m.addAttribute("member_name", memberName);
			m.addAttribute("error", "비밀번호가 일치하지 않습니다.");
			return "/member/registerForm";
		}
		if (mdao.idCheck(memberId) > 0) {
			m.addAttribute("member_id", memberId);
			m.addAttribute("member_name", memberName);
			m.addAttribute("error", "이미 사용 중인 아이디입니다.");
			return "/member/registerForm";
		}

		MemberDTO mdto = new MemberDTO();
		mdto.setMember_id(memberId);
		mdto.setMember_name(memberName);
		mdto.setPasswd(PasswordUtil.sha256(passwd));
		mdto.setReg_date(new Timestamp(System.currentTimeMillis()));
		mdao.insert(mdto);

		return "redirect:loginForm?registered=1";
	}

	private String safe(String value) {
		return value == null ? "" : value.trim();
	}
}