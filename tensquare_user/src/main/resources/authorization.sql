use tensquare_user;

drop table if exists tb_role;
drop table if exists tb_permission;
drop table if exists user_role;
drop table if exists role_permission;

create table tb_role(
    id tinyint primary key ,
    name varchar(32) ,
    namezh varchar(32)
);

create table tb_permission(
    id int primary key ,
    path varchar(255)
);

create table user_role(
    id int unique not null ,
    uid varchar(20) ,
    rid tinyint ,
    constraint ur primary key (uid,rid)
);

create table role_permission(
    id int(11) unique not null ,
    rid tinyint ,
    pid int ,
    constraint rp primary key (rid,pid)
);

insert into tb_role values(1,'ROLE_dba','数据库管理员');
insert into tb_role values(2,'ROLE_admin','系统管理员');
insert into tb_role values(3,'ROLE_user','用户');

insert into tb_permission values (1,'/**');
insert into tb_permission values (2,'/admin/**');
insert into tb_permission values (3,'/user/**');



