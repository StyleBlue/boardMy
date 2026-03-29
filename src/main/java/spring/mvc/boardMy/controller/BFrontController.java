package spring.mvc.boardMy.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.mvc.boardMy.command.ContentFormHandler;
import spring.mvc.boardMy.command.DeleteFormHandler;
import spring.mvc.boardMy.command.DeleteProHandler;
import spring.mvc.boardMy.command.ListHandler;
import spring.mvc.boardMy.command.LoginFormHandler;
import spring.mvc.boardMy.command.LoginProHandler;
import spring.mvc.boardMy.command.LogoutHandler;
import spring.mvc.boardMy.command.ModifyFormHandler;
import spring.mvc.boardMy.command.ModifyProHandler;
import spring.mvc.boardMy.command.ModifyViewHandler;
import spring.mvc.boardMy.command.RegisterFormHandler;
import spring.mvc.boardMy.command.RegisterProHandler;
import spring.mvc.boardMy.command.WriteFormHandler;
import spring.mvc.boardMy.command.WriteProHandler;
import spring.mvc.boardMy.dao.BoardDAO;
import spring.mvc.boardMy.dto.BoardDTO;

@Controller
public class BFrontController {
	String viewPage;
	@Autowired ListHandler listhandler;
	@Autowired BoardDAO bdao;
	@Autowired WriteFormHandler writeformhandler;
	@Autowired WriteProHandler writeprohandler;
	@Autowired ContentFormHandler contentformhandler;
	@Autowired ModifyFormHandler modifyformhandler;
	@Autowired ModifyViewHandler modifyviewhandler;
	@Autowired ModifyProHandler modifyprohandler;
	@Autowired DeleteFormHandler deleteformhandler;
	@Autowired DeleteProHandler deleteprohandler;
	@Autowired LoginFormHandler loginformhandler;
	@Autowired LoginProHandler loginprohandler;
	@Autowired RegisterFormHandler registerformhandler;
	@Autowired RegisterProHandler registerprohandler;
	@Autowired LogoutHandler logouthandler;

	@RequestMapping("/list")
	public String list(HttpServletRequest req, Model m) { m.addAttribute("request", req); viewPage = listhandler.execute(m); return viewPage; }
	@RequestMapping("/writeForm")
	public String writeForm(HttpServletRequest req, Model m) { m.addAttribute("request", req); viewPage = writeformhandler.execute(m); return viewPage; }
	@RequestMapping("/writePro")
	public String writePro(HttpServletRequest req, Model m) { m.addAttribute("request", req); viewPage = writeprohandler.execute(m); return viewPage; }
	@RequestMapping("/contentForm")
	public String contentForm(HttpServletRequest req, Model m) { m.addAttribute("request", req); viewPage = contentformhandler.execute(m); return viewPage; }
	@RequestMapping("/modifyForm")
	public String modifyForm(HttpServletRequest req, Model m) { m.addAttribute("request", req); viewPage = modifyformhandler.execute(m); return viewPage; }
	@RequestMapping("/modifyView")
	public String modifyView(HttpServletRequest req, Model m) { m.addAttribute("request", req); viewPage = modifyviewhandler.execute(m); return viewPage; }
	@RequestMapping("/modifyPro")
	public String modifyPro(HttpServletRequest req, Model m) { m.addAttribute("request", req); viewPage = modifyprohandler.execute(m); return viewPage; }
	@RequestMapping("/deleteForm")
	public String deleteForm(HttpServletRequest req, Model m) { m.addAttribute("request", req); viewPage = deleteformhandler.execute(m); return viewPage; }
	@RequestMapping("/deletePro")
	public String deletePro(HttpServletRequest req, Model m) { m.addAttribute("request", req); viewPage = deleteprohandler.execute(m); return viewPage; }
	@RequestMapping("/loginForm")
	public String loginForm(HttpServletRequest req, Model m) { m.addAttribute("request", req); viewPage = loginformhandler.execute(m); return viewPage; }
	@RequestMapping("/loginPro")
	public String loginPro(HttpServletRequest req, Model m) { m.addAttribute("request", req); viewPage = loginprohandler.execute(m); return viewPage; }
	@RequestMapping("/registerForm")
	public String registerForm(HttpServletRequest req, Model m) { m.addAttribute("request", req); viewPage = registerformhandler.execute(m); return viewPage; }
	@RequestMapping("/registerPro")
	public String registerPro(HttpServletRequest req, Model m) { m.addAttribute("request", req); viewPage = registerprohandler.execute(m); return viewPage; }
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req, Model m) { m.addAttribute("request", req); viewPage = logouthandler.execute(m); return viewPage; }

	@RequestMapping("/download")
	public void download(@RequestParam("num") int num, HttpServletResponse response) throws IOException {
		BoardDTO bdto = bdao.getArticle(num);
		if (bdto == null || bdto.getStored_file_name() == null || bdto.getStored_file_name().trim().isEmpty()) { response.sendError(HttpServletResponse.SC_NOT_FOUND); return; }
		File uploadFile = resolveUploadFile(bdto.getStored_file_name());
		if (!uploadFile.exists() || !uploadFile.isFile()) { response.sendError(HttpServletResponse.SC_NOT_FOUND); return; }
		String downloadName = bdto.getOrg_file_name();
		if (downloadName == null || downloadName.trim().isEmpty()) { downloadName = bdto.getStored_file_name(); }
		String encodedName = URLEncoder.encode(downloadName, "UTF-8").replace("+", "%20");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + encodedName);
		response.setHeader("Content-Length", String.valueOf(uploadFile.length()));
		Files.copy(uploadFile.toPath(), response.getOutputStream());
		response.flushBuffer();
	}

	private File resolveUploadFile(String storedFileName) {
		String catalinaBase = System.getProperty("catalina.base");
		if (catalinaBase != null && !catalinaBase.trim().isEmpty()) {
			return new File(catalinaBase + File.separator + "upload" + File.separator + "boardMy", storedFileName);
		}
		return new File(storedFileName);
	}
}