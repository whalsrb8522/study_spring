# [Spring 개발 환경]
- STS 3.9.18
- JDK11
- Apache Tomcat 9
- mysql 8.0.32.0

# [라이브러리]
1. pom.xml 추가
	- 다른 설정에 영향을 미치는 값이 있는지 체크
2. 파일 경로 설정 xml에 추가
	- web.xml: 파일 경로 설정
	- servlet-context.xml: 파일 경로 매핑, multipartResolver Bean 설정
3. 파일 업로드 할 경로 외부에 생성 (폴더 생성)
	- E:\_spring_workspace\_myweb\_java\fileUpload
4. web.xml 
	- 파일 최대 크기 설정 (max-file-size, 1mb ~ 2mb) 
	- 최대 응답 크기 설정 (max-rquest-size, 4mb ~ 10mb)
	- 파일 크기 여분 메모리 설정 (file-size-threshold, 1mb ~ 2mb)
- lombok 1.18.28
- HikariCP 5.0.1: DB 커넥션 풀 프레임워크
- jackson: comment 라이브러리 추가
	- jackson-databind 2.13.0
	- jackson-dataformat-xml  2.13.0
- BCryptPasswordEncoder: 패스워드 암호화 클래스
	- spring-security-core 5.5.3
	- spring-security-web 5.5.3
	- spring-security-config 5.5.3
- commons: 파일 업로드 라이브러리
	- commons-fileupload 1.4
	- commons-io 2.11.0
	- thumbnailator 0.4.14
	- tika-core 1.28
	- tika-parsers 1.28

# [DB]
- table: springtest
- user: springuser
- password: mysql

- 기본 설정: pom.xml, web.xml, root-context.xml
- DB 설정
```
create database springtest;

use mysql;

create user springuser@localhost identified by 'mysql';

grant all privileges on springtest.* to springuser@localhost with grant option;
flush privileges;

create table user (
id varchar(100) not null,
pw varchar(100) not null,
name varchar(100) not null,
email varchar(100),
home varchar(100),
age int,
reg_date datetime default now(),
isDel tinyint default 0,
primary key(id)
);

create table board (
bno int not null auto_increment,
title varchar(200) not null,
content text,
writer varchar(100),
reg_date datetime default now(),
readcount int default 0,
isDel varchar(50) default "n",
primary key(bno)
);

create table comment (
cno int not null auto_increment,
bno int not null,
writer varchar(100) not null,
content text not null,
regdate datetime default now(),
primary key(cno)
);

create table file (
uuid varchar(256) not null,
save_dir varchar(256) not null,
file_name varchar(256) not null,
file_type tinyint(1) default 0,
bno int,
file_size int,
reg_date datetime default now(),
primary key (uuid)
);
* uuid(범용 고유 식별자) : random한 고유번호를 설정

alter table comment add constraint fk_comment_bno foreign key(bno) references board(bno) 
	on delete cascade on update cascade;
alter table file add constraint fk_file_bno foreign key(bno) references board(bno)
	on delete cascade on update cascade;
alter table board add constraint fk_fboard_writer foreign key(writer) references user(id)
	on delete cascade on update cascade;
```

# [JAVA]
- controller
- domain
- repository
- service