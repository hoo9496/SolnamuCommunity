package com.solnamu.yb.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.solnamu.yb.dto.UserBean;

public interface UserMapper {
	final String INSERT_MEMBERCHECK_INFO = "insert into membercheck(id, pw, name, code, birthday, administrator) values(#{id}, #{pw}, #{name}, #{code}, #{birthday}, 0)";
	final String SELECT_LOGIN_CHECK = "select count(id) from userinfo where id=#{id} and pw=#{pw}";
	
	final String SELECT_CHECKCOUNT = "select count(*) from membercheck";
	final String SELECT_MEMBERCHECK = "select * from membercheck";
	final String DELETE_CHECKUSER = "Delete from membercheck where id = #{checkid}";

	final String SELECT_BY_ID = "select count(*) from userinfo";
	final String SELECT_USERLIST = "select * from userinfo";
	final String SELECT_USER_INFO = "select * from userinfo where id = #{id}";
	final String UPDATE_USER_INFO = "update userinfo set pw = #{pw}, birthday = #{birthday} where id = #{id}";
	
	final String CHECK_ID = "select count(*) from userinfo where id = #{id}";
	final String DELETE_USER = "Delete from userinfo where id = #{checkid}";
	final String UPDATE_USERPW = "update userinfo set pw='12345' where id = #{id}";
	final String INSERT_CHECKIDMOVE = "insert into userinfo(id,pw,name,code,birthday,administrator) select id,pw,name,code,birthday,administrator from membercheck where id = #{checkid}";
	final String SELECT_BY_NAME = "select name from userinfo where id = #{id}";

	final String UPDATE_USER_ADMIN = "update userinfo set administrator='1' where id = #{id}";
	
	@Insert(INSERT_MEMBERCHECK_INFO)
	void insertUserInfo(UserBean userBean);
	
	@Select(SELECT_LOGIN_CHECK)
	int getLoginInfoCheck(@Param("id") String id, @Param("pw") String pw);
	
	@Select(SELECT_BY_ID)
	int getUserCount();
	
	@Select(SELECT_USERLIST)
	@Results(value= {
		@Result(property="id", column="id"),
		@Result(property="pw", column="pw"),
		@Result(property="name", column="name"),
		@Result(property="code", column="code"),
		@Result(property="birthday", column="birthday"),
		@Result(property="administrator", column="administrator")
	})
	ArrayList<UserBean> getUserinfo();
	
	@Select(SELECT_MEMBERCHECK)
	@Results(value= {
			@Result(property="id", column="id"),
			@Result(property="pw", column="pw"),
			@Result(property="name", column="name"),
			@Result(property="code", column="code"),
			@Result(property="birthday", column="birthday"),
			@Result(property="administrator", column="administrator")	
	})
	ArrayList<UserBean> getMemberCheck();
	
	@Select(SELECT_USER_INFO)
	@Results(value= {
		@Result(property="id", column="id"),
		@Result(property="pw", column="pw"),
		@Result(property="name", column="name"),
		@Result(property="code", column="code"),
		@Result(property="birthday", column="birthday"),
		@Result(property="administrator", column="administrator")
	})
	UserBean getInfo(@Param("id")String id);
	
	@Update(UPDATE_USER_INFO)
	void updateBoard(UserBean userBean);
	
	/*@Select(COUNT_BOARD)
	int countBoard();*/
	
	@Select(CHECK_ID)
	int checkId(@Param("id")String id);
	
	@Delete(DELETE_CHECKUSER)
	void getCheckDelete(String checkid);

	@Insert(INSERT_CHECKIDMOVE)
	void getCheckIdMove(String checkid);

	@Select(SELECT_CHECKCOUNT)
	int getCheckCount();

	@Update(UPDATE_USERPW)
	void getUpdatepw(String id);

	@Delete(DELETE_USER)
	void getUserDelete(String id);
	
	@Update(UPDATE_USER_ADMIN)
	void getUpdateUserAdmin(String id);

	@Select(SELECT_BY_NAME)
	String getName(String id);
}
