package spring.mvc.boardMy.command;

import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.boardMy.dao.BoardDAO;
import spring.mvc.boardMy.dto.BoardDTO;

@Service("writeprohandler")
public class WriteProHandler implements CommandHandler {

	@Autowired
	BoardDAO bdao;
	
	@Override
	public String execute(Model m) {

		// FrontController 에서 보낸 Model에 담긴 request를 key와 value로 보내서 받았을때 Map으로
		// 받는다.
		// 값이 문자가 올지 숫자가 올지모르니 Object로 받는다.
		Map<String, Object> map = m.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("request");

		BoardDTO bdto = new BoardDTO();

		bdto.setNum(Integer.parseInt(req.getParameter("num")));
		bdto.setWriter(req.getParameter("writer"));
		bdto.setPasswd(req.getParameter("passwd"));
		bdto.setSubject(req.getParameter("subject"));
		bdto.setContent(req.getParameter("content"));
		bdto.setRef(Integer.parseInt(req.getParameter("ref")));
		bdto.setRef_step(Integer.parseInt(req.getParameter("ref_step")));
		bdto.setRef_level(Integer.parseInt(req.getParameter("ref_level")));

		bdto.setReg_date(new Timestamp(System.currentTimeMillis())); // 타임스탬프
																		// 불러오기
		bdto.setIp(req.getRemoteAddr()); // 자기 아이피를 요쳥한다.

		int cnt = bdao.insert(bdto);

		m.addAttribute("cnt", cnt);
		m.addAttribute("pageNum", req.getParameter("pageNum"));

		return "/board/writePro";
	}
}