package com.solnamu.yb.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.solnamu.yb.dto.BoardReplyBean;
import com.solnamu.yb.dto.BoardRereplyBean;

public interface CommunityReplyMapper {
	final String SELECT_REPLY = "select * from communityreply where bno = #{no}";

	final String SELECT_REPLYNO = "select rno+1 from communityreply where bno = #{no} order by rno desc limit 1";

	final String INSERT_REPLY = "insert into communityreply(bno,rno,content,writer,regDate) values(#{no},#{rno},#{content},#{writer},sysdate())";

	final String SELECT_REREPLY = "select * from communityrereply where bno = #{no}";

	final String SELECT_REREPLYNO = "select sno+1 from communityrereply where bno = #{no} and rno = #{rno} order by sno desc limit 1";

	final String INSERT_REREPLY = "insert into communityrereply(bno,rno,sno,content,writer,regDate) values(#{no},#{rno},#{sno},#{content},#{writer},sysdate())";

	final String DELETE_REREPLY = "update communityrereply set content='[삭제된 메세지입니다]' where bno = #{no} and rno = #{rno} and sno = #{sno}";

	final String DELETE_REPLY = "update communityreply set content='[삭제된 메세지입니다]' where bno = #{no} and rno = #{rno}" ;
	
	
	@Select(SELECT_REPLY)
	@Results(value = {
			@Result(property="bno", column="bno"),
			@Result(property="rno", column="rno"),
			@Result(property="content", column="content"),
			@Result(property="writer", column="writer"),
			@Result(property="regDate", column="regDate")	
	})
	ArrayList<BoardReplyBean> getReplyList(@Param("no") int no);

	
	@Select(SELECT_REPLYNO)
	String getReplyNoCount(@Param("no") int no);

	@Insert(INSERT_REPLY)
	void getInsertReply(@Param("id") String id,@Param("no") int no, @Param("rno") int rno, @Param("content") String content,@Param("writer") String writer);

	
	@Select(SELECT_REREPLY)
	@Results(value = {
			
			@Result(property="bno", column="bno"),
			@Result(property="rno", column="rno"),
			@Result(property="sno", column="sno"),
			@Result(property="content", column="content"),
			@Result(property="writer", column="writer"),
			@Result(property="regDate", column="regDate")	
	})
	ArrayList<BoardRereplyBean> getRereplyList(int no);

	
	@Select(SELECT_REREPLYNO)
	String getRereplyNoCount(@Param("no") int no,@Param("rno") int rno);

	@Insert(INSERT_REREPLY)
	void getInsertRereply(@Param("id") String id,@Param("no") int no,@Param("rno") int rno,@Param("sno") int sno,@Param("content") String content,@Param("writer") String writer);

	@Update(DELETE_REREPLY)
	void getDeleteRereply(@Param("no") int no,@Param("rno") int rno,@Param("sno") int sno);
	
	@Update(DELETE_REPLY)
	void getDeleteReply(@Param("no") int no,@Param("rno") int rno);
}
