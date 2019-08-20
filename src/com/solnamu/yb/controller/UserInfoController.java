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

	// 메인 폼 매핑 (GET 방식)
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String login_form(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		logger.info("세션 없음. 로그인 폼 호출!!!!");
		logger.info("세션 ID : " + session.getAttribute("id"));

		return "Main";
	}

	// 메인 폼 매핑 (POST 방식)
	@RequestMapping(value = "/main.do", method = RequestMethod.POST)
	public String main_form(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		logger.info("세션 있음. 메인 폼 호출!!!!");
		logger.info("세션 ID : " + session.getAttribute("id"));

		return "Main";
	}

	// 회원가입 폼 매핑
	@RequestMapping(value = "/user_signUp_form.do", method = RequestMethod.GET)
	public String signUp_form(Model model) {

		logger.info("signUp_form 호출!!!!");

		model.addAttribute("courseList", courseService.getCourses());
		model.addAttribute("signUpWrite", new UserBean());

		return "user/SignUp";
	}

	// 로그아웃 폼 매핑 (로그아웃 시 로그인 폼으로 이동)
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout_form(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("logout 실행!!!!");
		logger.info("세션 ID 제거 전 : " + request.getSession().getAttribute("id"));

		request.getSession().removeAttribute("id");
		request.getSession().removeAttribute("code");
		request.getSession().removeAttribute("admin");
		request.getSession().removeAttribute("name");
		logger.info("세션 ID 제거 결과 : " + request.getSession().getAttribute("id"));

		return "redirect:main.do";
	}

	// id 중복확인
	@RequestMapping(value = "/idCheck.do", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Integer> idCheck(@RequestParam("id") String id) {

		logger.info("idCheck 호출!!!!");
		logger.info("중복체크 요청 ID : " + id);

		int checkID = 0;
		Map<String, Integer> map = new HashMap<String, Integer>();
		checkID = userService.getIdCheck(id);
		logger.info("중복 체크 결과 : " + checkID); // 결과가 1이면 아이디 중복, 0이면 아이디 사용가능
		map.put("checkID", checkID);

		return map;
	}
		
	// membercheck 테이블에 데이터 저장
	@RequestMapping(value = "/user_signUp_insert.do", method = RequestMethod.POST)
	public String signUp_insert(Model model, @ModelAttribute("signUpWrite") UserBean userBean) {

		logger.info("signUp_insert 호출!!!!");
		int code;

		code = courseService.selectCode(userBean.getCour_name());
		userBean.setCode(code);
		logger.info("강좌코드 : " + userBean.getCode());

		userService.insertUserInfo(userBean);

		return "redirect:main.do";
	}

	// 로그인 요청 시 ID, PW 체크
	@RequestMapping(value = "/loginCheck.do", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Integer> LoginInfoCheck(@RequestParam("id") String id, @RequestParam("pw") String pw,
			HttpSession session) {

		logger.info("LoginInfoCheck 호출!!!!");
		Map<String, Integer> map = new HashMap<String, Integer>();
		int checkLogin = 0;
		checkLogin = userService.getLoginInfoCheck(id, pw);
		logger.info("로그인 요청 ID : " + id);
		logger.info("로그인 요청 PW : " + pw);
		logger.info("로그인 체크 결과  : " + checkLogin);

		map.put("checkLogin", checkLogin);

		// 결과가 1이면 로그인 성공, 0이면 실패
		if (checkLogin == 1) {
			logger.info("로그인 성공");
			// userBean = userService.getInfo(id);
			UserBean userBean = userService.getInfo(id);
			logger.info(userBean.getId());
			session.setAttribute("id", userBean.getId());
			session.setAttribute("code", userBean.getCode());
			session.setAttribute("admin", userBean.getAdministrator());
			session.setAttribute("name", userBean.getName());
		} else {
			logger.info("로그인 실패");
		}

		return map;
	}
}
