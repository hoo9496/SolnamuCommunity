package com.solnamu.yb.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.solnamu.yb.dto.*;

public interface CoursesMapper {
	final String SELECT_COURSES_ALL = "select * from courses";
	final String SELECT_COURSES = "select * from courses where code = #{code}";
	final String SELECT_COURSES_NAME = "select cour_name from courses where code = #{code}";
	
	final String INSERT_COURSES = "insert into courses values(#{code}, #{cour_name},"
			+ "#{start_date}, #{end_date})";
	final String UPDATE_COURSES = "update courses set cour_name = #{cour_name},"
			+ "start_date = #{start_date}, end_date = #{end_date} where code = #{code}";
	final String DELETE_COURSES = "Delete from courses where code = #{code}";
	final String SELECT_CODE = "select code from courses where cour_name=#{cour_name}";
	
	final String CHECK_CODE = "select count(*) from courses where code = #{code}";
	final String CHECK_COURSES_NAME = "select count(*) from courses where cour_name = #{cour_name}";
	
	@Select(SELECT_COURSES_ALL)
	@Results(value= {
			@Result(property="code", column="code"),
			@Result(property="cour_name", column="cour_name"),
			@Result(property="start_date", column="start_date"),
			@Result(property="end_date", column="end_date"),
	})
	ArrayList<CourseBean> getCourses();
	
	@Select(SELECT_COURSES_NAME)
	String getCourName(@Param("code")int code);
	
	
	@Select(SELECT_COURSES)
	@Results(value= {
			@Result(property="code", column="code"),
			@Result(property="cour_name", column="cour_name"),
			@Result(property="start_date", column="start_date"),
			@Result(property="end_date", column="end_date"),
	})
	CourseBean getCour(@Param("code")int code);
	
	@Insert(INSERT_COURSES)
	void insertCourse(CourseBean courseBean);
	
	@Update(UPDATE_COURSES)
	void updateCourse(CourseBean courseBean);
	
	@Delete(DELETE_COURSES)
	void deleteCourse(@Param("code")int code);
	
	@Select(SELECT_CODE)
	int selectCode(@Param("cour_name") String cour_name);
	
	@Select(CHECK_CODE)
	int checkCode(@Param("code")String code);
	
	@Select(CHECK_COURSES_NAME)
	int checkCoursesName(@Param("cour_name")String cour_name);
}
