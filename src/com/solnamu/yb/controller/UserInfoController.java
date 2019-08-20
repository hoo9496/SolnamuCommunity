package com.solnamu.yb.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.solnamu.yb.dto.UserBean;
import com.solnamu.yb.service.*;

@Controller
public class UserInfoController {
	@Autowired
	UserService userService;
	@Autowired
	CourseService courseService;

	private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

	@RequestMapping(value = "/user_info.do", method = RequestMethod.GET)
	public String viewUserInfo(Model model, HttpServletRequest request) {
		
		String id = request.getSession().getAttribute("id").toString();
		UserBean user = userService.getInfo(id);
		user.setCour_name(courseService.getCourName(user.getCode()));
		model.addAttribute("user", user);

		return "user/UserInfo";
	}

	@RequestMapping(value = "user_info_update_form.do", method = RequestMethod.GET)
	public String UserInfoEdit(Model model,HttpServletRequest request) {
		
		String id = request.getSession().getAttribute("id").toString();
		UserBean user = userService.getInfo(id);
		user.setCour_name(courseService.getCourName(user.getCode()));
		System.out.println(user.getCour_name());
		model.addAttribute("userInfoUpdateBean", user);
		return "user/UserInfoUpdateWrite";
	}

	@RequestMapping(value = "/user_info_update.do", method = RequestMethod.POST)
	public String userInfoUpdate(Model model, @ModelAttribute("userInfoUpdateBean") UserBean userBean) {
		userService.updateBoard(userBean);
		
		return "redirect:main.do";
	}

	// ���� �� ���� (GET ���)
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String login_form(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		logger.info("���� ����. �α��� �� ȣ��!!!!");
		logger.info("���� ID : " + session.getAttribute("id"));

		return "Main";
	}

	// ���� �� ���� (POST ���)
	@RequestMapping(value = "/main.do", method = RequestMethod.POST)
	public String main_form(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		logger.info("���� ����. ���� �� ȣ��!!!!");
		logger.info("���� ID : " + session.getAttribute("id"));

		return "Main";
	}

	// ȸ������ �� ����
	@RequestMapping(value = "/user_signUp_form.do", method = RequestMethod.GET)
	public String signUp_form(Model model) {

		logger.info("signUp_form ȣ��!!!!");

		model.addAttribute("courseList", courseService.getCourses());
		model.addAttribute("signUpWrite", new UserBean());

		return "user/SignUp";
	}

	// �α׾ƿ� �� ���� (�α׾ƿ� �� �α��� ������ �̵�)
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout_form(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("logout ����!!!!");
		logger.info("���� ID ���� �� : " + request.getSession().getAttribute("id"));

		request.getSession().removeAttribute("id");
		request.getSession().removeAttribute("code");
		request.getSession().removeAttribute("admin");
		request.getSession().removeAttribute("name");
		logger.info("���� ID ���� ��� : " + request.getSession().getAttribute("id"));

		return "redirect:main.do";
	}

	// id �ߺ�Ȯ��
	@RequestMapping(value = "/idCheck.do", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Integer> idCheck(@RequestParam("id") String id) {

		logger.info("idCheck ȣ��!!!!");
		logger.info("�ߺ�üũ ��û ID : " + id);

		int checkID = 0;
		Map<String, Integer> map = new HashMap<String, Integer>();
		checkID = userService.getIdCheck(id);
		logger.info("�ߺ� üũ ��� : " + checkID); // ����� 1�̸� ���̵� �ߺ�, 0�̸� ���̵� ��밡��
		map.put("checkID", checkID);

		return map;
	}
		
	// membercheck ���̺� ������ ����
	@RequestMapping(value = "/user_signUp_insert.do", method = RequestMethod.POST)
	public String signUp_insert(Model model, @ModelAttribute("signUpWrite") UserBean userBean) {

		logger.info("signUp_insert ȣ��!!!!");
		int code;

		code = courseService.selectCode(userBean.getCour_name());
		userBean.setCode(code);
		logger.info("�����ڵ� : " + userBean.getCode());

		userService.insertUserInfo(userBean);

		return "redirect:main.do";
	}

	// �α��� ��û �� ID, PW üũ
	@RequestMapping(value = "/loginCheck.do", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Integer> LoginInfoCheck(@RequestParam("id") String id, @RequestParam("pw") String pw,
			HttpSession session) {

		logger.info("LoginInfoCheck ȣ��!!!!");
		Map<String, Integer> map = new HashMap<String, Integer>();
		int checkLogin = 0;
		checkLogin = userService.getLoginInfoCheck(id, pw);
		logger.info("�α��� ��û ID : " + id);
		logger.info("�α��� ��û PW : " + pw);
		logger.info("�α��� üũ ���  : " + checkLogin);

		map.put("checkLogin", checkLogin);

		// ����� 1�̸� �α��� ����, 0�̸� ����
		if (checkLogin == 1) {
			logger.info("�α��� ����");
			// userBean = userService.getInfo(id);
			UserBean userBean = userService.getInfo(id);
			logger.info(userBean.getId());
			session.setAttribute("id", userBean.getId());
			session.setAttribute("code", userBean.getCode());
			session.setAttribute("admin", userBean.getAdministrator());
			session.setAttribute("name", userBean.getName());
		} else {
			logger.info("�α��� ����");
		}

		return map;
	}
}
