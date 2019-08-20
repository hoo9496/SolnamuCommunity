package com.solnamu.yb.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.solnamu.yb.dto.CourseBean;
import com.solnamu.yb.mapper.CoursesMapper;

@Component
public class CourseService {
	@Autowired
	private CoursesMapper mapper;
	
	public ArrayList<CourseBean> getCourses(){
		return mapper.getCourses();
	};
	
	public void insertCourse(CourseBean courseBean) {
		mapper.insertCourse(courseBean);
	};
	public String getCourName(int code) {
		return mapper.getCourName(code);
	};
	public CourseBean getCour(int code) {
		return mapper.getCour(code);
	};
	
	public void updateCourse(CourseBean courseBean) {
		mapper.updateCourse(courseBean);
	};
	
	public void deleteCourse(int code) {
		mapper.deleteCourse(code);
	};
	
	public int selectCode(String cour_name) {
		return mapper.selectCode(cour_name);
	};
	
	public int getCodeCheck(String code) {
		return mapper.checkCode(code);
	};
	
	public int getCoursesNameCheck(String cour_name) {
		return mapper.checkCoursesName(cour_name);
	};
}
