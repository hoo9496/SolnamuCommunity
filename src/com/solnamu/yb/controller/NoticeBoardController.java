package com.solnamu.yb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.solnamu.yb.dto.BoardBean;
import com.solnamu.yb.service.NoticeBoardService;
import com.solnamu.yb.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class NoticeBoardController {
	
	@Autowired
	NoticeBoardService boardService;
	@Autowired
	UserService userService;
	private static final Logger logger = LoggerFactory.getLogger(DebugBoardController.class);
	public static final int limit = 10;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	// 공지사항 리스트 폼 매핑
	@RequestMapping(value = "/notice_board_list.do", method = RequestMethod.GET)
	public String List(@RequestParam("offset") int offset, @RequestParam("pagelink") int pagelink, Model model) {
		
		model.addAttribute("boardCount", boardService.getContentsCount());
		model.addAttribute("boardList",  boardService.getBoardList(offset,limit) );
		model.addAttribute("PagingCount",boardService.showPaging(pagelink,"/notice_board_list.do"));
		model.addAttribute("offset",offset);
		model.addAttribute("pagelink",pagelink);
		return "notice_board/List";
	}
	
	// 검색리스트 폼 매핑
	@RequestMapping(value = "/notice_board_serch_list.do", method = RequestMethod.GET)
	public String SearchList(@RequestParam("offset") int offset, @RequestParam("pagelink") int pagelink ,
			@RequestParam("find") String search,@RequestParam("search") String searchtext, Model model) {
	
		System.out.println(offset);
		System.out.println(search);
		System.out.println(searchtext);
		
		if(search.equals("name")) {
			model.addAttribute("boardCount", boardService.getSearchCountName('%'+searchtext+'%'));
			model.addAttribute("boardList",  boardService.getSearchBoardName(offset,limit,'%'+searchtext+'%'));
		
		}else if(search.equals("subject")) {
			model.addAttribute("boardCount", boardService.getSearchCountSubject('%'+searchtext+'%'));
			model.addAttribute("boardList",  boardService.getSearchBoardSubject(offset,limit,'%'+searchtext+'%'));
			
		}else {
			model.addAttribute("boardCount", boardService.getSearchCountContents('%'+searchtext+'%'));
			model.addAttribute("boardList",  boardService.getSearchBoardContents(offset,limit,'%'+searchtext+'%'));
			
		}
		model.addAttribute("offset", offset);
		model.addAttribute("pagelink", pagelink);
		model.addAttribute("PagingCount",boardService.showPaging(pagelink,"/notice_board_serch_list.do",search,searchtext));
		
		return "notice_board/List";
	}
	
	// 공지글 작성 폼 매핑
	@RequestMapping(value = "/notice_board_write_form.do", method = RequestMethod.GET)
	public String show_write_form(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();  
		
		logger.info("show_write_form called!!");
		logger.info("세션 권한 : " + session.getAttribute("admin"));
		BoardBean boardBean = new BoardBean();
		boardBean.setName(userService.getName(session.getAttribute("id").toString()));
		model.addAttribute("boardBeanObjToWrite", boardBean);
		
		return "notice_board/WriteForm";
	}
	
	// 공지글 작성 매핑
	@RequestMapping(value = "/notice_board_do_write.do", method = RequestMethod.POST)
	public String DoWriteBoard(@ModelAttribute("boardBeanObjToWrite") BoardBean boardBeanObjToWrite, Model model) {
		
		System.out.println(boardBeanObjToWrite.getName());
		
		logger.info("notice_board_do_write called!!");
		logger.info("이름=["+boardBeanObjToWrite.getName()+"]");
		
		boardService.insertBoard(boardBeanObjToWrite);

		return "redirect:notice_board_list.do?offset=0&pagelink=1";
	}
	
	// 글보기 폼 매핑
	@RequestMapping(value="/notice_board_view_contents.do", method=RequestMethod.GET)
	public String viewWork(@RequestParam("no") int no, @RequestParam("offset")int offset,
			@RequestParam("pagelink")int pagelink, Model model){
		logger.info("notice_board_view_contents called!!");
		model.addAttribute("no", no);
		model.addAttribute("offset", offset);
		model.addAttribute("pagelink", pagelink);
		boardService.getUpdateViews(no);
		model.addAttribute("boardData", boardService.getView(no));
		
		return "notice_board/ViewContents";
	}
	
	// 공지사항 수정 폼 매핑
	@RequestMapping(value = "notice_board_update_form.do", method = RequestMethod.GET)
	public String update(@RequestParam("no") int no, @RequestParam("offset")int offset,
			@RequestParam("pagelink")int pagelink, Model model) {
		logger.info("notice_board_update_form called!!");
		model.addAttribute("no", no);
		model.addAttribute("offset", offset);
		model.addAttribute("pagelink", pagelink);
		model.addAttribute("boardData", boardService.getView(no));
		
		return "notice_board/ContentsUpdateWrite";
	}

	// 공지사항 수정 매핑
	@RequestMapping(value = "notice_board_update.do", method = RequestMethod.POST)
	public String DoUpdateBoard(@ModelAttribute("boardData")BoardBean boardBeanObjToUpdate,@RequestParam("offset")int offset,
			@RequestParam("pagelink")int pagelink, Model model) {
		logger.info("notice_board_update called!!");
		
		boardService.updateContentsBoard(boardBeanObjToUpdate);
		return "redirect:notice_board_view_contents.do?no="+boardBeanObjToUpdate.getNo()+"&offset="+offset+"&pagelink="+pagelink;
	}

	// 공지사항 삭제 매핑
	@RequestMapping(value = "/notice_board_delete.do", method = RequestMethod.GET)
	public String delete(@RequestParam("no") int no, Model model, Object boardBeanObjToDelete) {
		logger.info("notice_board_delete called!!");
		boardService.deleteContentsBoard(no);
		return "redirect:notice_board_list.do?offset=0&pagelink=1";
	}
	
}
