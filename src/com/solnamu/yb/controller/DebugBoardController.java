package com.solnamu.yb.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.solnamu.yb.dto.DebugBoardContentsBean;
import com.solnamu.yb.service.DebugBoardService;
import com.solnamu.yb.service.ReplyService;
import com.solnamu.yb.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DebugBoardController {
	@Autowired
	DebugBoardService boardService;
	@Autowired
	ReplyService replyService;
	@Autowired
	UserService userService;
	private static final Logger logger = LoggerFactory.getLogger(DebugBoardController.class);
	public static final int limit = 10;
	private static final String dataPath = "D:\\soldesk_project\\spring_mywork\\SolnamuCommunity\\WebContent\\resources\\data\\";
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/debug_board_list.do", method = RequestMethod.GET)
	public String List(@RequestParam("offset") int offset, @RequestParam("pagelink") int pagelink, Model model) {
		
		model.addAttribute("boardCount", boardService.getContentsCount());
		model.addAttribute("boardList",  boardService.getBoardList(offset,limit) );
		model.addAttribute("PagingCount",boardService.showPaging(pagelink,"/debug_board_list.do"));
		model.addAttribute("offset",offset);
		model.addAttribute("pagelink",pagelink);
		return "debug_board/List";
	}
	@RequestMapping(value = "/debug_board_serch_list.do", method = RequestMethod.GET)
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
		model.addAttribute("PagingCount",boardService.showPaging(pagelink,"/debug_board_serch_list.do",search,searchtext));
		return "debug_board/List";
	}
	
	@RequestMapping(value = "/debug_board_write_form.do", method = RequestMethod.GET)
	public String show_write_form(Model model , HttpServletRequest request) {
		logger.info("show_write_form called!!");
		
		DebugBoardContentsBean bean = new DebugBoardContentsBean();
		bean.setName(userService.getName(request.getSession().getAttribute("id").toString()));
		model.addAttribute("boardBeanObjToWrite", bean);
		return "debug_board/WriteForm";
	}
	
	@RequestMapping(value = "/debug_board_do_write.do", method = RequestMethod.POST)
	public String DoWriteBoard(@ModelAttribute("boardBeanObjToWrite") DebugBoardContentsBean boardBeanObjToWrite, Model model) {
				
		logger.info("debug_board_do_write called!!");
		logger.info("이름=["+boardBeanObjToWrite.getName()+"]");
		logger.info("파일이름=["+boardBeanObjToWrite.getFile()+"]");

		MultipartFile file = boardBeanObjToWrite.getFile();
		if(file != null) {
			String fileName = file.getOriginalFilename();
			if(!fileName.equals("")) {
				boolean flag = true;
				while(flag) {
					File checkFile = new File(dataPath+fileName);
					if(checkFile.exists())
						fileName = "r_"+fileName;
					else
						flag = false;
				}
				boardBeanObjToWrite.setFileName(fileName);
				try {
					byte[] fileData = file.getBytes();
					FileOutputStream output = new FileOutputStream(dataPath+fileName);
					output.write(fileData);
					output.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
				boardService.insertContentsBoard(boardBeanObjToWrite);
			}else {	
				boardService.insertBoard(boardBeanObjToWrite);
			}
		}
		return "redirect:debug_board_list.do?offset=0&pagelink=1";
	}
	
	@RequestMapping(value="/debug_board_view_contents.do", method=RequestMethod.GET)
	public String viewWork(@RequestParam("no") int no, @RequestParam("offset")int offset,
			@RequestParam("pagelink")int pagelink, Model model,HttpServletRequest request) throws Exception{
		logger.info("debug_board_view_contents called!!");
		request.setCharacterEncoding("euc-kr");
		model.addAttribute("no", no);
		model.addAttribute("offset", offset);
		model.addAttribute("pagelink", pagelink);
		boardService.getUpdateViews(no);
		DebugBoardContentsBean boardData = boardService.getView(no);
		model.addAttribute("boardData", boardData);
		String filename = "/resources/data/"+boardData.getFileName();
		model.addAttribute("filename", filename);
		model.addAttribute("replyData", replyService.getReplyList(no));
		model.addAttribute("rereplyData", replyService.getRereplyList(no));
		
		return "debug_board/ViewContents";
	}
	
	@RequestMapping(value = "debug_board_update_form.do", method = RequestMethod.GET)
	public String update(@RequestParam("no") int no, @RequestParam("offset")int offset,
			@RequestParam("pagelink")int pagelink, Model model) {
		logger.info("debug_board_update_form called!!");
		model.addAttribute("no", no);
		model.addAttribute("offset", offset);
		model.addAttribute("pagelink", pagelink);
		model.addAttribute("boardData", boardService.getView(no));
		
		return "debug_board/ContentsUpdateWrite";
	}

	@RequestMapping(value = "debug_board_update.do", method = RequestMethod.POST)
	public String DoUpdateBoard(@ModelAttribute("boardData")DebugBoardContentsBean boardBeanObjToUpdate, @RequestParam("offset")int offset,
			@RequestParam("pagelink")int pagelink, Model model) {
		logger.info("debug_board_update called!!");
		
		MultipartFile file = boardBeanObjToUpdate.getFile();
		if (file != null) {
			String fileName = file.getOriginalFilename();
			if(!fileName.equals("")) {
				//중복명 파일 처리
				boolean flag = true;
				while(flag) {
					File checkFile = new File(dataPath+fileName);
					if(checkFile.exists())
						fileName = "r_"+fileName;
					else
						flag = false;
				}
				//이전 파일 삭제
				String beforeFileName = boardService.getFileName(boardBeanObjToUpdate.getNo());
				File beforeFile = new File(dataPath+beforeFileName);
				if( beforeFile.exists() ){
		            if(beforeFile.delete()){
		                System.out.println("파일삭제 성공");
		            }else{
		                System.out.println("파일삭제 실패");
		            }
		        }else{
		            System.out.println("파일이 존재하지 않습니다.");
		        }
				//새 파일 업로드
				boardBeanObjToUpdate.setFileName(fileName);
				try {
					byte[] fileData = file.getBytes();
					FileOutputStream output = new FileOutputStream(dataPath + fileName);
					output.write(fileData);
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				boardService.updateFileContentsBoard(boardBeanObjToUpdate);
			}else {
				boardService.updateContentsBoard(boardBeanObjToUpdate);
			}
		}
		return "redirect:debug_board_view_contents.do?no="+boardBeanObjToUpdate.getNo()+"&offset="+offset+"&pagelink="+pagelink ;
	}

	@RequestMapping(value = "/debug_board_delete.do", method = RequestMethod.GET)
	public String delete(@RequestParam("no") int no, Model model, Object boardBeanObjToDelete,HttpServletRequest request) {
		logger.info("debug_board_delete called!!");
		String id = boardService.getWriterId(no);
		
		if(request.getSession().getAttribute("id").toString().equals(id) || request.getSession().getAttribute("admin").toString().equals("1")) {
			//파일 삭제
			String beforeFileName = boardService.getFileName(no);
			File beforeFile = new File(dataPath+beforeFileName);
			if( beforeFile.exists() ){
	            if(beforeFile.delete()){
	                System.out.println("파일삭제 성공");
	            }else{
	                System.out.println("파일삭제 실패");
	            }
	        }else{
	            System.out.println("파일이 존재하지 않습니다.");
	        }
			boardService.deleteRereplyBoard(no);
			boardService.deleteReplyBoard(no);
			boardService.deleteContentsBoard(no);
			return "redirect:debug_board_list.do?offset=0&pagelink=1";
		}
		return "redirect:main.do";
	}
	
	@RequestMapping(value="debug_board_reply.do", method=RequestMethod.POST)
	public String reply(@RequestParam("no")int no, @RequestParam("content")String content, RedirectAttributes redirctAttributes,
			HttpServletRequest request, @RequestParam("offset")int offset, @RequestParam("pagelink")int pagelink, Model model){
		logger.info("viewWork called!!");

		String rno = replyService .getReplyNoCount(no);
		if(rno == null  ){
			rno = "1";
		}
		String id = request.getSession().getAttribute("id").toString();
		
		String writer = userService.getName(id);
		
		replyService.getInsertReply(id,no,Integer.parseInt(rno),content,writer);
		
		redirctAttributes.addAttribute("no", no);
		
		return "redirect:debug_board_view_contents.do?no="+no+"&offset="+offset+"&pagelink="+pagelink;
	}
	
	@RequestMapping(value="debug_board_rereply.do", method=RequestMethod.POST)
	public String rereply(@RequestParam("no")int no,
			@RequestParam("rno")int rno,
			@RequestParam("content")String content, 
			RedirectAttributes redirctAttributes,
			HttpServletRequest request,
			@RequestParam("offset")int offset,
			@RequestParam("pagelink")int pagelink,
			Model model){
		
		String sno = replyService.getRereplyNoCount(no,rno);

		if(sno == null  ){
			sno = "1";
		}
		System.out.println("번호" + no);
		System.out.println("댓글번호" +rno);
		System.out.println("대댓번호" +sno);
		System.out.println("글내용 " + content);
		
		String id = request.getSession().getAttribute("id").toString();
		String writer = userService.getName(id);	
		replyService.getInsertRereply(id,no,rno,Integer.parseInt(sno),content,writer);
		redirctAttributes.addAttribute("no", no);
		
		return "redirect:debug_board_view_contents.do?no="+no+"&offset="+offset+"&pagelink="+pagelink;
	}
	
	@RequestMapping(value = "debug_delete_reply.do", method = RequestMethod.GET)
	public String deletereply(@RequestParam("no") int no,@RequestParam("rno") int rno,
			RedirectAttributes redirctAttributes, HttpServletRequest request, @RequestParam("offset") int offset,
			@RequestParam("pagelink") int pagelink, Model model) {
				
		replyService.getDeleteReply(no, rno);
		redirctAttributes.addAttribute("no", no);

		return "redirect:debug_board_view_contents.do?no=" + no + "&offset=" + offset + "&pagelink=" + pagelink;
	}

	@RequestMapping(value = "debug_delete_rereply.do", method = RequestMethod.GET)
	public String deleterereply(@RequestParam("no") int no, @RequestParam("rno") int rno,@RequestParam("sno") int sno,
		 RedirectAttributes redirctAttributes, HttpServletRequest request,@RequestParam("offset") int offset, @RequestParam("pagelink") int pagelink, Model model) {
		
		//String id = request.getSession().getAttribute("id").toString();
		
		replyService.getDeleteRereply(no, rno, sno);
		redirctAttributes.addAttribute("no", no);
		
		return "redirect:debug_board_view_contents.do?no=" + no + "&offset=" + offset + "&pagelink=" + pagelink;
	}
	
	// file 삭제
		@RequestMapping(value = "/debug_fileDelete.do", method = RequestMethod.POST, produces = "application/json")
		@ResponseBody
		public Map<String, Integer> fileDelete(@RequestParam("filename") String filename) {

			logger.info("fileDelete 호출!!!!");

			int checkFile = 0;
			Map<String, Integer> map = new HashMap<String, Integer>();
			
			File beforeFile = new File(dataPath+filename);
			if (beforeFile.exists()) {
				if (beforeFile.delete()) {
					System.out.println("파일삭제 성공");
					boardService.deleteFile(filename);
					checkFile = 1;
				} else {
					System.out.println("파일삭제 실패");
				}
			} else {
				System.out.println("파일이 존재하지 않습니다.");
			}
			map.put("flag", checkFile);
			return map;
		}
}
