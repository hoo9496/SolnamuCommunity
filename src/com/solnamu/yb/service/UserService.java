package com.solnamu.yb.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.solnamu.yb.dto.UserBean;
import com.solnamu.yb.mapper.UserMapper;

@Component
public class UserService {
	@Autowired
	private UserMapper userInfoMapper;
	
	public void insertUserInfo(UserBean userBean) {
		userInfoMapper.insertUserInfo(userBean);
	}
	
	public int getLoginInfoCheck(String id, String pw) {
		return userInfoMapper.getLoginInfoCheck(id, pw);
	}
	
	public int getMembers(){
		return userInfoMapper.getUserCount();
	} 
	
	public ArrayList<UserBean> getUsersinfo(){
		return userInfoMapper.getUserinfo(); 
	}

	public ArrayList<UserBean> getUserCheck() {
		return userInfoMapper.getMemberCheck();
	}

	public void getCheckDelete(String checkid) {
		userInfoMapper.getCheckDelete(checkid);
	}

	public void getCheckIdMove(String checkid) {
		userInfoMapper.getCheckIdMove(checkid);
	}

	public int getCheckMembers() {
		return userInfoMapper.getCheckCount();
	}

	public void getUpdatepw(String id) {
		userInfoMapper.getUpdatepw(id);
	}

	public void getUserDelete(String id) {
		userInfoMapper.getUserDelete(id);
	}
	
	public int getIdCheck(String id) {
		return userInfoMapper.checkId(id);
	}
	
	public UserBean getInfo(String id) {
		return userInfoMapper.getInfo(id);
	}
	
	public void updateBoard(UserBean userBean) {
		userInfoMapper.updateBoard(userBean);
	}
	
	public String getName(String id) {
		return userInfoMapper.getName(id);
	}

	public void getUpdateUserAdmin(String id) {
		userInfoMapper.getUpdateUserAdmin(id);
		
	}
}