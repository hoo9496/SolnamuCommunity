/*ȸ�� ���� ���̺�*/
create table userinfo (
	id varchar(100),
	pw varchar(20),
	name varchar(20),
	code int,
	birthday date,
	administrator varchar(20),
	primary key(id)
)DEFAULT CHARSET=EUCKR;
/*ȸ�� ���� ��û ���� ���̺�*/
create table membercheck (
	id varchar(100),
	pw varchar(20),
	name varchar(20),
	code int,
	birthday date,
	administrator varchar(20),
	primary key(id)
)	DEFAULT CHARSET=EUCKR;
/*�������̺�*/
create table courses(
	code int(11),
	cour_name varchar(50),
	start_date date,
	end_date date,
	primary key (code)
)DEFAULT CHARSET=EUCKR;
/*�����������̺�*/
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
/*��������̺�*/
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
/*Ŀ�´�Ƽ���̺�*/
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
/*����� ������̺�*/
create table Reply (
	id 		  varchar(100),
    bno   	  int not null,
    rno       int not null,
    content   varchar(400)    not null,
    writer    varchar(30)      not null,
    regDate   date,
    primary key(bno, rno)
)DEFAULT CHARSET=EUCKR;
/*����� �������̺�*/
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
/*Ŀ�´�Ƽ ������̺�*/
create table communityReply (
    id 		  varchar(100),
    bno       int not null,
    rno       int not null,
    content   varchar(400) not null,
    writer    varchar(30)  not null,
    regDate   date,
    primary key(bno, rno)
)DEFAULT CHARSET=EUCKR;
/*Ŀ�´�Ƽ �������̺�*/
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

insert into userinfo values('admin@admin.com','1234','������',1111,'19/02/19','1');

insert into courses values(1111, '�ڹٺ����ڵ� 1ȸ��','18/08/01','19/02/01');
insert into courses values(2222, '�ڹٺ����ڵ� 2ȸ��','18/12/01','19/06/01');
insert into courses values(3333, '��Ʈ��ũ 1ȸ��','18/08/01','19/02/01');
insert into courses values(4444, '������ 1ȸ��','18/10/01','19/04/01');