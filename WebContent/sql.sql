/*회원 정보 테이블*/
create table userinfo (
	id varchar(100),
	pw varchar(20),
	name varchar(20),
	code int,
	birthday date,
	administrator varchar(20),
	primary key(id)
)DEFAULT CHARSET=EUCKR;
/*회원 가입 신청 보관 테이블*/
create table membercheck (
	id varchar(100),
	pw varchar(20),
	name varchar(20),
	code int,
	birthday date,
	administrator varchar(20),
	primary key(id)
)	DEFAULT CHARSET=EUCKR;
/*강좌테이블*/
create table courses(
	code int(11),
	cour_name varchar(50),
	start_date date,
	end_date date,
	primary key (code)
)DEFAULT CHARSET=EUCKR;
/*공지사항테이블*/
create table noticeboard(
	id varchar(100), 
	no int auto_increment,
	subject varchar(50),
	contents varchar(600),
	name varchar(20),
	wdate date,
	views int,
	PRIMARY KEY (no)
)DEFAULT CHARSET=EUCKR;
/*디버깅테이블*/
create table board(
	id varchar(100), 
	no int auto_increment,
	subject varchar(50),
	contents varchar(600),
	name varchar(20),
	wdate date,
	views int,
	filename varchar(200),
	PRIMARY KEY (no)
)DEFAULT CHARSET=EUCKR;
/*커뮤니티테이블*/
create table communityboard(
	id varchar(100), 
	no int auto_increment,
	subject varchar(50),
	contents varchar(600),
	name varchar(20),
	wdate date,
	views int,
	filename varchar(200),
	kategorie varchar(10),
	PRIMARY KEY (no)
)DEFAULT CHARSET=EUCKR;
/*디버깅 댓글테이블*/
create table Reply (
	id 		  varchar(100),
    bno   	  int not null,
    rno       int not null,
    content   varchar(400)    not null,
    writer    varchar(30)      not null,
    regDate   date,
    primary key(bno, rno)
)DEFAULT CHARSET=EUCKR;
/*디버깅 대댓글테이블*/
create table Rereply (
	id 		  varchar(100),
    bno       int not null,
    rno       int not null,
    sno       int not null, 
    content   varchar(400) not null,
    writer    varchar(30)  not null,
    regDate   date,
    primary key(bno, rno,sno)
)DEFAULT CHARSET=EUCKR;
/*커뮤니티 댓글테이블*/
create table communityReply (
    id 		  varchar(100),
    bno       int not null,
    rno       int not null,
    content   varchar(400) not null,
    writer    varchar(30)  not null,
    regDate   date,
    primary key(bno, rno)
)DEFAULT CHARSET=EUCKR;
/*커뮤니티 대댓글테이블*/
create table communityRereply (
    id 	      varchar(100),
    bno       int not null,
    rno       int not null,
    sno       int not null, 
    content   varchar(400) not null,
    writer    varchar(30)  not null,
    regDate   date,
    primary key(bno, rno,sno)
)DEFAULT CHARSET=EUCKR;

insert into userinfo values('admin@admin.com','1234','관리자',1111,'19/02/19','1');

insert into courses values(1111, '자바보안코딩 1회차','18/08/01','19/02/01');
insert into courses values(2222, '자바보안코딩 2회차','18/12/01','19/06/01');
insert into courses values(3333, '네트워크 1회차','18/08/01','19/02/01');
insert into courses values(4444, '리눅스 1회차','18/10/01','19/04/01');