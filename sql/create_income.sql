use jdbctest1;
create table if not exists userIncome(
id int primary key,
income int ,
d date,
t char(10)
);