package spring.mvc.boardMy.command;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.mvc.boardMy.dao.BoardDAO;
import spring.mvc.boardMy.dto.BoardDTO;

@Service("writeprohandler")
public class WriteProHandler implements CommandHandler {

	@Autowired
	BoardDAO bdao;

	@Override
	public String execute(Model m) {
		Map<String, Object> map = m.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("request");
		HttpSession session = req.getSession(false);

		if (session == null || session.getAttribute("loginMemberId") == null) {
			return "redirect:loginForm?returnUrl=writeForm";
		}

		BoardDTO bdto = new BoardDTO();
		bdto.setNum(parseInt(req.getParameter("num"), 0));
		bdto.setWriter(String.valueOf(session.getAttribute("loginMemberName")));
		bdto.setPasswd(req.getParameter("passwd"));
		bdto.setSubject(req.getParameter("subject"));
		bdto.setContent(req.getParameter("content"));
		bdto.setRef(parseInt(req.getParameter("ref"), 1));
		bdto.setRef_step(parseInt(req.getParameter("ref_step"), 0));
		bdto.setRef_level(parseInt(req.getParameter("ref_level"), 0));
		bdto.setReg_date(new Timestamp(System.currentTimeMillis()));
		bdto.setIp(req.getRemoteAddr());

		try {
			if (req instanceof MultipartHttpServletRequest) {
				MultipartHttpServletRequest multiReq = (MultipartHttpServletRequest) req;
				MultipartFile uploadFile = multiReq.getFile("uploadFile");
				handleUpload(uploadFile, req, bdto);
			}

			int cnt = bdao.insert(bdto);
			m.addAttribute("cnt", cnt);
		} catch (IOException e) {
			m.addAttribute("cnt", 0);
		}

		m.addAttribute("pageNum", req.getParameter("pageNum"));
		return "/board/writePro";
	}

	private int parseInt(String value, int defaultValue) {
		if (value == null || value.trim().isEmpty()) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	private void handleUpload(MultipartFile uploadFile, HttpServletRequest req, BoardDTO bdto) throws IOException {
		if (uploadFile == null || uploadFile.isEmpty()) {
			return;
		}

		String originalName = new File(uploadFile.getOriginalFilename()).getName();
		if (originalName.trim().isEmpty()) {
			return;
		}

		File uploadDir = resolveUploadDir(req);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		String extension = "";
		int dotIndex = originalName.lastIndexOf('.');
		if (dotIndex > -1) {
			extension = originalName.substring(dotIndex);
		}

		String storedName = UUID.randomUUID().toString().replace("-", "") + extension;
		File targetFile = new File(uploadDir, storedName);
		uploadFile.transferTo(targetFile);

		bdto.setOrg_file_name(originalName);
		bdto.setStored_file_name(storedName);
		bdto.setFile_size(uploadFile.getSize());
	}

	private File resolveUploadDir(HttpServletRequest req) {
		String catalinaBase = System.getProperty("catalina.base");
		if (catalinaBase != null && !catalinaBase.trim().isEmpty()) {
			return new File(catalinaBase, "upload/boardMy");
		}
		String realPath = req.getSession().getServletContext().getRealPath("/upload/boardMy");
		return new File(realPath);
	}
}