package com.solnamu.yb.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.solnamu.yb.dto.CommunityBoardContentsBean;

public interface CommunityBoardMapper { 
	
	//final String SELECT_CHECKCOUNT = "select count(*) from membercheck";
	
	final String SELECT_CNT_BY_SUBJECT = "select count(*) from communityboard where subject like #{searchtext}";
	final String SELECT_CNT_BY_CONTENTS = "select count(*) from communityboard where contents like #{searchtext}";
	final String SELECT_CNT_BY_NAME = "select count(*) from communityboard where name like #{searchtext}";
					  	
	final String SELECT_BY_CONTENTS = "select * from communityboard where contents like #{searchtext} order by no desc limit #{limit} offset #{offset}";
	final String SELECT_BY_SUBJECT = "select * from communityboard where subject like #{searchtext} order by no desc limit #{limit} offset #{offset}";
	final String SELECT_BY_NAME = "select * from communityboard where name like #{searchtext} order by no desc limit #{limit} offset #{offset}";
	
	final String SELECT_SEARCH_CONTENTS= "select * from communityboard where name = #{searchtext}";
	
	final String SELECT_CONTENTS = "select * from communityboard order by no desc limit #{limit} offset #{offset}";
	
	final String SELECT_CONTENTS_COUNT = "select count(*) from communityboard";
	
	final String UPDATE_VIEWS = "update communityboard set views = views+1 where no = #{no}";
	
	final String SELECT_VIEW = "select * from communityboard where no = #{no}";
	
	final String UPDATE_BOARD_FILE = "update communityboard set subject = #{subject}, filename = #{fileName} , contents = #{contents} where no = #{no}";

	final String UPDATE_BOARD = "update communityboard set subject = #{subject}, contents = #{contents} where no = #{no}";

	final String DELETE_BY_ID = "delete from communityboard where no = #{no}";

	final String INSERT = "insert into communityboard(id,subject,contents,name,wdate,views,filename,kategorie) values(#{id},#{subject},#{contents},#{name},sysdate(),0,'',#{kategorie})";

	final String SELECT_BY_FILENAME = "select filename from communityboard where no = #{no}";

	final String INSERT_CONTENTS = "insert into communityboard(id,subject,contents,name,wdate,views,filename,kategorie) values(#{id},#{subject},#{contents},#{name},sysdate(),0,#{fileName},#{kategorie})";
	final String SELECT_WRITER_ID = "select id from communityboard where id= #{id}";
	
	final String DELETE_FILE = "update communityboard set filename='' where filename=#{filename}";
	
	final String DELETE_REAL_REPLY = "delete from communityreply where bno= #{no}";
	
	final String DELETE_REAL_REREPLY = "delete from communityrereply where bno= #{no}";
	
	@Update(DELETE_FILE)
	void deleteFile(@Param("filename")String filename);

	@Delete(DELETE_REAL_REPLY)
	void deleteReplyBoard(int no);
	
	@Delete(DELETE_REAL_REREPLY)
	void deleteRereplyBoard(int no);
	
	@Select(SELECT_CONTENTS_COUNT)
	int getContentsCount();
	
	@Select(SELECT_BY_CONTENTS)
	@Results(value= {
			@Result(property="id", column="id"),
			@Result(property="no", column="no"),
			@Result(property="subject", column="subject"),
			@Result(property="name", column="name"),
			@Result(property="contents", column="contents"),
			@Result(property="wdate", column="wdate"),
			@Result(property="views", column="views"),
			@Result(property="fileName", column="filename"),
			@Result(property="kategorie", column="kategorie")
			
	})
	ArrayList<CommunityBoardContentsBean> getSearchContents(@Param("searchtext") String searchtext,@Param("offset") int offset,@Param("limit") int limit);
	
	@Select(SELECT_BY_NAME)
	@Results(value= {
			@Result(property="id", column="id"),
			@Result(property="no", column="no"),
			@Result(property="subject", column="subject"),
			@Result(property="name", column="name"),
			@Result(property="contents", column="contents"),
			@Result(property="wdate", column="wdate"),
			@Result(property="views", column="views"),
			@Result(property="fileName", column="filename"),
			@Result(property="kategorie", column="kategorie")
			
	})
	ArrayList<CommunityBoardContentsBean> getSearchName(@Param("searchtext") String searchtext,@Param("offset") int offset,@Param("limit") int limit);
	
	@Select(SELECT_BY_SUBJECT)
	@Results(value= {
			@Result(property="id", column="id"),
			@Result(property="no", column="no"),
			@Result(property="subject", column="subject"),
			@Result(property="name", column="name"),
			@Result(property="contents", column="contents"),
			@Result(property="wdate", column="wdate"),
			@Result(property="views", column="views"),
			@Result(property="fileName", column="filename"),
			@Result(property="kategorie", column="kategorie")
			
	})
	ArrayList<CommunityBoardContentsBean> getSearchSubject(@Param("searchtext") String searchtext,@Param("offset") int offset,@Param("limit") int limit);
	
	@Select(SELECT_CONTENTS)
	@Results(value= {
			@Result(property="id", column="id"),
			@Result(property="no", column="no"),
			@Result(property="subject", column="subject"),
			@Result(property="name", column="name"),
			@Result(property="contents", column="contents"),
			@Result(property="wdate", column="wdate"),
			@Result(property="views", column="views"),
			@Result(property="fileName", column="filename"),
			@Result(property="kategorie", column="kategorie")
	})
	ArrayList<CommunityBoardContentsBean> getContentsList(@Param("offset") int offset,@Param("limit") int limit);

	@Insert(INSERT)
	void getInsert(CommunityBoardContentsBean boardBeanObjToWrite);
	
	@Insert(INSERT_CONTENTS)
	void getContentsInsert(CommunityBoardContentsBean boardBeanObjToWrite);

	@Select(SELECT_CNT_BY_CONTENTS)
	int getSearchCountContents(@Param("searchtext") String searchtext);
	
	
	@Select(SELECT_CNT_BY_NAME)
	int getSearchCountName(@Param("searchtext") String searchtext);
	
	
	@Select(SELECT_CNT_BY_SUBJECT)
	int getSearchCountSubject(@Param("searchtext") String searchtext);
	
	@Select(SELECT_VIEW)
	CommunityBoardContentsBean getView(int no);
	
	@Select(SELECT_BY_FILENAME)
	String getFileName(@Param("no") int no);
	
	@Update(UPDATE_VIEWS)
	void getUpdateViews(int no);
	
	@Update(UPDATE_BOARD_FILE)
	void updateFileContentsBoard(CommunityBoardContentsBean boardContentsBean);
	
	@Update(UPDATE_BOARD)
	void updateContentsBoard(CommunityBoardContentsBean boardContentsBean);
	
	@Delete(DELETE_BY_ID)	
	void deleteContentsBoard(int no);

	@Select(SELECT_WRITER_ID)
	String getWriterId(int no);

} 
