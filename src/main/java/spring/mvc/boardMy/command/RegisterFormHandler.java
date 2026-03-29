package spring.mvc.boardMy.command;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service("registerformhandler")
public class RegisterFormHandler implements CommandHandler {
	@Override
	public String execute(Model m) {
		return "/member/registerForm";
	}
}