package com.solnamu.yb.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.solnamu.yb.dto.BoardBean;

public interface NoticeBoardMapper { 
	
	//final String SELECT_CHECKCOUNT = "select count(*) from membercheck";
	
	final String SELECT_CNT_BY_SUBJECT = "select count(*) from noticeboard where subject like #{searchtext}";
	final String SELECT_CNT_BY_CONTENTS = "select count(*) from noticeboard where contents like #{searchtext}";
	final String SELECT_CNT_BY_NAME = "select count(*) from noticeboard where name like #{searchtext}";
					  	
	final String SELECT_BY_CONTENTS = "select * from noticeboard where contents like #{searchtext} order by no desc limit #{limit} offset #{offset}";
	final String SELECT_BY_SUBJECT = "select * from noticeboard where subject like #{searchtext} order by no desc limit #{limit} offset #{offset}";
	final String SELECT_BY_NAME = "select * from noticeboard where name like #{searchtext} order by no desc limit #{limit} offset #{offset}";
	
	final String SELECT_SEARCH_CONTENTS= "select * from noticeboard where name = #{searchtext}";
	
	final String SELECT_CONTENTS = "select * from noticeboard order by no desc limit #{limit} offset #{offset}";
	
	final String SELECT_CONTENTS_COUNT = "select count(*) from noticeboard";
	
	final String UPDATE_VIEWS = "update noticeboard set views = views+1 where no = #{no}";
	
	final String SELECT_VIEW = "select * from noticeboard where no = #{no}";
	
	final String UPDATE_BOARD = "update noticeboard set subject = #{subject}, contents = #{contents} where no = #{no}";

	final String DELETE_BY_ID = "delete from noticeboard where no = #{no}";
	
	final String INSERT = "insert into noticeboard(subject,contents,name,wdate,views) values(#{subject},#{contents},#{name},sysdate(),0)";
	
	//final String SELECT_BY_ID = "select count(*) from userinfo";
	
	//final String SELECT_USERLIST = "select * from userinfo";
	
	//final String SELECT_MEMBERCHECK = "select * from membercheck";
	
	//final String DELETE_CHECKUSER = "Delete from membercheck where id = #{checkid}";
	
	//final String DELETE_USER = "Delete from userinfo where id = #{checkid}";
	
	//final String INSERT_CHECKIDMOVE = "insert into userinfo(id,pw,name,course,birthday,administrator) select id,pw,name,course,birthday,administrator from membercheck where id = #{checkid}";
	
	//final String UPDATE_USERPW = "update userinfo set pw='12345' where id = #{id}";
	
	//
	@Select(SELECT_CONTENTS_COUNT)
	int getContentsCount();
	
	@Select(SELECT_BY_CONTENTS)
	@Results(value= {
			@Result(property="no", column="no"),
			@Result(property="subject", column="subject"),
			@Result(property="name", column="name"),
			@Result(property="contents", column="contents"),
			@Result(property="wdate", column="wdate"),
			@Result(property="views", column="views")			
	})
	ArrayList<BoardBean> getSearchContents(@Param("searchtext") String searchtext,@Param("offset") int offset,@Param("limit") int limit);
	
	@Select(SELECT_BY_NAME)
	@Results(value= {
			@Result(property="no", column="no"),
			@Result(property="subject", column="subject"),
			@Result(property="name", column="name"),
			@Result(property="contents", column="contents"),
			@Result(property="wdate", column="wdate"),
			@Result(property="views", column="views")			
	})
	ArrayList<BoardBean> getSearchName(@Param("searchtext") String searchtext,@Param("offset") int offset,@Param("limit") int limit);
	
	@Select(SELECT_BY_SUBJECT)
	@Results(value= {
			@Result(property="no", column="no"),
			@Result(property="subject", column="subject"),
			@Result(property="name", column="name"),
			@Result(property="contents", column="contents"),
			@Result(property="wdate", column="wdate"),
			@Result(property="views", column="views")			
	})
	ArrayList<BoardBean> getSearchSubject(@Param("searchtext") String searchtext,@Param("offset") int offset,@Param("limit") int limit);
	
	//
	@Select(SELECT_CONTENTS)
	@Results(value= {
			@Result(property="no", column="no"),
			@Result(property="subject", column="subject"),
			@Result(property="name", column="name"),
			@Result(property="contents", column="contents"),
			@Result(property="wdate", column="wdate"),
			@Result(property="views", column="views")
	})
	ArrayList<BoardBean> getContentsList(@Param("offset") int offset,@Param("limit") int limit);

	//
	@Insert(INSERT)
	void getInsert(BoardBean boardBeanObjToWrite);

	//
	@Select(SELECT_CNT_BY_CONTENTS)
	int getSearchCountContents(@Param("searchtext") String searchtext);
	
	//
	@Select(SELECT_CNT_BY_NAME)
	int getSearchCountName(@Param("searchtext") String searchtext);
	
	//
	@Select(SELECT_CNT_BY_SUBJECT)
	int getSearchCountSubject(@Param("searchtext") String searchtext);
	
	//
	@Select(SELECT_VIEW)
	BoardBean getView(int no);
	
	//
	@Update(UPDATE_VIEWS)
	void getUpdateViews(int no);
	
	//
	@Update(UPDATE_BOARD)
	void updateContentsBoard(BoardBean boardContentsBean);
	
	@Delete(DELETE_BY_ID)	
	void deleteContentsBoard(int no);
} 
