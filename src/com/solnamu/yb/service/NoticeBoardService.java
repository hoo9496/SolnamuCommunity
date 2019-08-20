package com.solnamu.yb.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.solnamu.yb.dto.*;
import com.solnamu.yb.mapper.NoticeBoardMapper;
import com.solnamu.yb.util.*;

@Component
public class NoticeBoardService {
	@Autowired
	private NoticeBoardMapper boardMapper;
	
	/*public int getMembers(){
		return boardMapper.getUserCount();
	} 
	
	public ArrayList<UserBean> getUsersinfo(){
		return boardMapper.getUserinfo(); 
	}

	public ArrayList<UserBean> getUserCheck() {
		// TODO Auto-generated method stub
		return boardMapper.getMemberCheck();
	}

	public void getCheckDelete(String checkid) {
		// TODO Auto-generated method stub
		 boardMapper.getCheckDelete(checkid);
	}

	public void getCheckIdMove(String checkid) {
		// TODO Auto-generated method stub
		boardMapper.getCheckIdMove(checkid);
	}

	public int getCheckMembers() {
		// TODO Auto-generated method stub
		return boardMapper.getCheckCount();
	}

	public void getUpdatepw(String id) {
		// TODO Auto-generated method stub
		boardMapper.getUpdatepw(id);
	}

	public void getUserDelete(String id) {
		// TODO Auto-generated method stub
		boardMapper.getUserDelete(id);
	}*/

	//
	public int getContentsCount() {
		return boardMapper.getContentsCount(); 
	}
	
	//
	public int getSearchCountContents(String searchtext) {
		return boardMapper.getSearchCountContents(searchtext); 
	}
	
	//
	public int getSearchCountSubject(String searchtext) {
		return boardMapper.getSearchCountSubject(searchtext); 
	}
	
	//
	public int getSearchCountName(String searchtext) {
		return boardMapper.getSearchCountName(searchtext); 
	}

	//
	public ArrayList<BoardBean> getBoardList(int offset, int limit) {
		return boardMapper.getContentsList(offset,limit);
	}

	//
	public void insertBoard(BoardBean boardBeanObjToWrite) {
		boardMapper.getInsert(boardBeanObjToWrite);
	}

	//
	public ArrayList<BoardBean> getSearchBoardName(int offset, int limit, String searchtext) {
		return boardMapper.getSearchName(searchtext,offset,limit);
	}
	
	//
	public ArrayList<BoardBean> getSearchBoardContents(int offset, int limit, String searchtext) {
		return boardMapper.getSearchContents(searchtext,offset,limit);
	}
	
	//
	public ArrayList<BoardBean> getSearchBoardSubject(int offset, int limit, String searchtext) {
		return boardMapper.getSearchSubject(searchtext,offset,limit);
	}

	//
	public String showPaging(int pagelink, String pagename) {
		PagingCount pc = new PagingCount(boardMapper.getContentsCount());
		return pc.showPaging(pagelink, pagename);
	}
	
	//
	public String showPaging(int pagelink, String pagename,String find,String search) {
		int i = 0;
		if(find.equals("name")) {
			i = boardMapper.getSearchCountName('%'+search+'%');
		}else if(find.equals("subject")) {
			i = boardMapper.getSearchCountSubject('%'+search+'%');
		}else
			i = boardMapper.getSearchCountContents('%'+search+'%');
		
		PagingCount pc = new PagingCount(i);
		return pc.showPaging(pagelink, pagename,find,search);
	}
	
	//
	public BoardBean getView(int no) {
		return boardMapper.getView(no);
	}
	
	//
	public void getUpdateViews(int no) {
		boardMapper.getUpdateViews(no);
	}
	
	//
	public void updateContentsBoard(BoardBean boardContentsBean) {
		boardMapper.updateContentsBoard(boardContentsBean);
	}
	
	//
	public void deleteContentsBoard(int no) {
		boardMapper.deleteContentsBoard(no);
	}
	
}
