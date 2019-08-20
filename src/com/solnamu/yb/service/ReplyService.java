package com.solnamu.yb.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.solnamu.yb.mapper.ReplyMapper;
import com.solnamu.yb.dto.*;
@Component
public class ReplyService {
	@Autowired
	private ReplyMapper Mapper;

	public ArrayList<BoardReplyBean> getReplyList(int no) {
		return Mapper.getReplyList(no);
	}
	
	public String getReplyNoCount(int no) {
		return Mapper.getReplyNoCount(no);
	}

	public void getInsertReply(String id,int no, int rno, String content, String writer) {
		Mapper.getInsertReply(id,no,rno,content,writer);
	}

	public ArrayList<BoardRereplyBean> getRereplyList(int no) {
		return Mapper.getRereplyList(no);
	}

	public String getRereplyNoCount(int no, int rno) {
		return Mapper.getRereplyNoCount(no,rno);
	}

	public void getInsertRereply(String id,int no, int rno, int sno, String content, String writer) {
		Mapper.getInsertRereply(id,no,rno,sno,content,writer);
	}
	public void getDeleteRereply(int no, int rno, int sno) {
		// TODO Auto-generated method stub
		Mapper.getDeleteRereply(no,rno,sno);
	}

	public void getDeleteReply(int no, int rno) {
		// TODO Auto-generated method stub
		Mapper.getDeleteReply(no,rno);
	}	

}
