package com.solnamu.yb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.solnamu.yb.mapper.ChatMapper;

@Component
public class ChatService {
	@Autowired
	private ChatMapper chatMapper;

	public String getCourseName(String code) {
		// TODO Auto-generated method stub
		return chatMapper.getCourseName(code);
	}
	
	
}
