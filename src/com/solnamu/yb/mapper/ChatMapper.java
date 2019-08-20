package com.solnamu.yb.mapper;

import org.apache.ibatis.annotations.Select;


public interface ChatMapper {

	final String SELECT_COURSENAME = "select cour_name from courses where code = #{code}";

	@Select(SELECT_COURSENAME)
	String getCourseName(String code); 

} 
