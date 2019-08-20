package com.solnamu.yb.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.solnamu.yb.service.ChatService;

@Controller
public class ChatController {
	@Autowired
	ChatService chatService;
	
	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public String home(HttpServletRequest request,Locale locale, Model model) {
		try {
			String code = request.getSession().getAttribute("code").toString();
			model.addAttribute("course_name",chatService.getCourseName(code));
		}catch (NullPointerException e) {
			e.getMessage();
		}
		return "Client";
	}
}
