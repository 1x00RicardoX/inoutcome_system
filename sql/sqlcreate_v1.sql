USE jdbctest1;
create table userFile1(
	id int identity(1,1),
	account varchar(20) not null unique,
	password char(256) not null,
	name varchar(40),
	sex char(1),
	phone int,
	mail varchar(40),
	address varchar(200),
	portrait int
)
