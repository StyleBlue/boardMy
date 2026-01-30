package spring.mvc.boardMy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.boardMy.command.ListHandler;
import spring.mvc.boardMy.command.ContentFormHandler;

import spring.mvc.boardMy.command.DeleteFormHandler;
import spring.mvc.boardMy.command.DeleteProHandler;
import spring.mvc.boardMy.command.ModifyFormHandler;
import spring.mvc.boardMy.command.ModifyProHandler;
import spring.mvc.boardMy.command.ModifyViewHandler;
import spring.mvc.boardMy.command.WriteFormHandler;
import spring.mvc.boardMy.command.WriteProHandler;

@Controller
public class BFrontController {

	String viewPage;
	
	@Autowired
	ListHandler listhandler;

	
	// 글목록
	@RequestMapping("/list")
	public String list(HttpServletRequest req, Model m) {

		m.addAttribute("request", req);

		viewPage = listhandler.execute(m);

		return viewPage;
	}

	@Autowired
	WriteFormHandler writeformhandler;

	// 글쓰기 페이지
	@RequestMapping("/writeForm")
	public String writeForm(HttpServletRequest req, Model m) {

		m.addAttribute("request", req);

		viewPage = writeformhandler.execute(m);

		return viewPage;
	}

	@Autowired
	WriteProHandler writeprohandler;
	
	// 글쓰기 처리 페이지
	@RequestMapping("/writePro")
	public String writePro(HttpServletRequest req, Model m) {

		m.addAttribute("request", req);

		viewPage = writeprohandler.execute(m);

		return viewPage;
	}


	@Autowired
	ContentFormHandler contentformhandler;
	
	// 상세 페이지
	@RequestMapping("/contentForm")
	public String contentForm(HttpServletRequest req, Model m) {

		m.addAttribute("request", req);

		viewPage = contentformhandler.execute(m);

		return viewPage;
	}

	@Autowired
	ModifyFormHandler modifyformhandler;
	
	// 글 수정 폼
	@RequestMapping("/modifyForm")
	public String modifyForm(HttpServletRequest req, Model m) {

		m.addAttribute("request", req);

		viewPage = modifyformhandler.execute(m);

		return viewPage;
	}

	@Autowired
	ModifyViewHandler modifyviewhandler;
	
	// 글 수정 뷰
	@RequestMapping("/modifyView")
	public String modifyView(HttpServletRequest req, Model m) {

		m.addAttribute("request", req);

		viewPage = modifyviewhandler.execute(m);

		return viewPage;
	}

	@Autowired
	ModifyProHandler modifyprohandler;
	
	// 글수정 로직 처리
	@RequestMapping("/modifyPro")
	public String modifyPro(HttpServletRequest req, Model m) {

		m.addAttribute("request", req);

		viewPage = modifyprohandler.execute(m);

		return viewPage;
	}

	@Autowired
	DeleteFormHandler deleteformhandler;
	
	// 글 삭제 폼
	@RequestMapping("/deleteForm")
	public String deleteForm(HttpServletRequest req, Model m) {

		m.addAttribute("request", req);

		viewPage = deleteformhandler.execute(m);

		return viewPage;
	}

	@Autowired
	DeleteProHandler deleteprohandler;
	
	// 글 삭제 로직 처리
	@RequestMapping("/deletePro")
	public String deletePro(HttpServletRequest req, Model m) {

		m.addAttribute("request", req);

		viewPage = deleteprohandler.execute(m);

		return viewPage;
	}
}