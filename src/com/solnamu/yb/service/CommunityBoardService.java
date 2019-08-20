package com.solnamu.yb.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.solnamu.yb.dto.*;
import com.solnamu.yb.mapper.CommunityBoardMapper;
import com.solnamu.yb.util.*;

@Component
public class CommunityBoardService {
	@Autowired
	private CommunityBoardMapper boardMapper;

	public int getContentsCount() {
		return boardMapper.getContentsCount(); 
	}
	
	public int getSearchCountContents(String searchtext) {
		return boardMapper.getSearchCountContents(searchtext); 
	}
	
	public int getSearchCountSubject(String searchtext) {
		return boardMapper.getSearchCountSubject(searchtext); 
	}
	public int getSearchCountName(String searchtext) {
		return boardMapper.getSearchCountName(searchtext); 
	}

	public ArrayList<CommunityBoardContentsBean> getBoardList(int offset, int limit) {
		return boardMapper.getContentsList(offset,limit);
	}

	public void insertBoard(CommunityBoardContentsBean boardBeanObjToWrite) {
		boardMapper.getInsert(boardBeanObjToWrite);
	}
	public void insertContentsBoard(CommunityBoardContentsBean boardBeanObjToWrite) {
		boardMapper.getContentsInsert(boardBeanObjToWrite);
	}

	public ArrayList<CommunityBoardContentsBean> getSearchBoardName(int offset, int limit, String searchtext) {
		return boardMapper.getSearchName(searchtext,offset,limit);
	}
	
	public ArrayList<CommunityBoardContentsBean> getSearchBoardContents(int offset, int limit, String searchtext) {
		return boardMapper.getSearchContents(searchtext,offset,limit);
	}
	
	public ArrayList<CommunityBoardContentsBean> getSearchBoardSubject(int offset, int limit, String searchtext) {
		return boardMapper.getSearchSubject(searchtext,offset,limit);
	}

	public String showPaging(int pagelink, String pagename) {
		PagingCount pc = new PagingCount(boardMapper.getContentsCount());
		return pc.showPaging(pagelink, pagename);
	}
	
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
	
	public CommunityBoardContentsBean getView(int no) {
		return boardMapper.getView(no);
	}
	
	public void getUpdateViews(int no) {
		boardMapper.getUpdateViews(no);
	}
	
	public void updateFileContentsBoard(CommunityBoardContentsBean boardContentsBean) {
		boardMapper.updateFileContentsBoard(boardContentsBean);
	}
	
	public void updateContentsBoard(CommunityBoardContentsBean boardContentsBean) {
		boardMapper.updateContentsBoard(boardContentsBean);
	}
	public void deleteContentsBoard(int no) {
		boardMapper.deleteContentsBoard(no);
	}

	public String getWriterId(int no) {
		return boardMapper.getWriterId(no);
	}
	
	public String getFileName(int no) {
		return boardMapper.getFileName(no);
	}
	
	public void deleteFile(String filename) {
		boardMapper.deleteFile(filename);
	}

	public void deleteReplyBoard(int no) {
		// TODO Auto-generated method stub
		boardMapper.deleteReplyBoard(no);
	};
	
	public void deleteRereplyBoard(int no) {
		// TODO Auto-generated method stub
		boardMapper.deleteRereplyBoard(no);
	};
}
