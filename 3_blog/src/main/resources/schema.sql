drop table if exists blog_user;
create table blog_user(
	username varchar2(255) primary key,
	email varchar2(512) not null unique,
	password varchar2(512) not null,
	name varchar2(1024) not null,
	enabled number(1) not null default 1,
	created_on DATETIME not null default sysdate,
	created_by varchar2(255),
	updated_on DATETIME,
	updated_by varchar2(255)
);

drop table if exists blog_role;
create table blog_role(
	role_name varchar2(255) primary key
);
insert into blog_role(role_name) values('admin');
insert into blog_role(role_name) values('user');
insert into blog_role(role_name) values('author');
insert into blog_role(role_name) values('editor');

drop table if exists blog_user_role;
create table blog_user_role(
	username varchar2(255) NOT NULL,
	role_name varchar2(255) NOT NULL,
	PRIMARY KEY(username, role_name),
	foreign key (role_name) references blog_role(role_name),
	foreign key (username) references blog_user(username)
);
