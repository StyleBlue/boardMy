package spring.mvc.boardMy.command;

import org.springframework.ui.Model;

public interface CommandHandler {

	public String execute(Model m);
	
}