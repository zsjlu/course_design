use library_reservation;

create table user_info (
    user_id varchar(32) not null comment '读者编号',
    reserve_status tinyint(2) default '0' comment '预约状态',
    sign_in_status tinyint(2) default '0' comment '签到状态',
    sign_out_status tinyint(2) default '0' comment '签离状态',
    primary key(user_id)
) comment '读者信息表' ;
 
create table administrator (
    admin_id varchar(32) not null comment '管理员编号',
    admin_name varchar(64) not null comment '管理员姓名',
    admin_account varchar(64) not null comment '管理员账号',
    admin_password varchar(64) not null comment '管理员密码',
    primary key(admin_id)
) comment '管理员表' ; 

create table floor (
    floor_id varchar(32) not null comment '楼层编号',
    floor_name varchar(64) not null comment '楼层名称',
    primary key(floor_id)
) comment '楼层表' ; 

insert into floor(floor_id, floor_name) values('101','新图书馆一楼');
insert into floor(floor_id, floor_name) values('102','新图书馆二楼');
insert into floor(floor_id, floor_name) values('103','新图书馆三楼');
insert into floor(floor_id, floor_name) values('104','新图书馆四楼');
insert into floor(floor_id, floor_name) values('105','新图书馆五楼');
insert into floor(floor_id, floor_name) values('106','新图书馆六楼');

create table reading_room (
    reading_room_id varchar(32) not null comment '阅览室编号',
    reading_room_name varchar(64) not null comment '阅览室名称',
    floor_id varchar(32) comment '阅览室所属楼层',
    seat_count int not null comment '座位数量',
    primary key(reading_room_id),
    foreign key(floor_id) references floor(floor_id) on delete set null
) comment '阅览室表' ;

insert into reading_room(reading_room_id, reading_room_name, floor_id, seat_count) values('101-1001','社会科学图书阅览室','101',100);
insert into reading_room(reading_room_id, reading_room_name, floor_id, seat_count) values('101-1002','自然科学图书阅览室','101',100);
insert into reading_room(reading_room_id, reading_room_name, floor_id, seat_count) values('101-1003','综合阅览室','101',100);

create table seat (
    seat_id varchar(32) not null comment '座位编号',
    seat_status tinyint(2) default '0' comment '使用状态',
    reading_room_id varchar(32) comment '座位所属阅览室',
    primary key(seat_id),
    foreign key(reading_room_id) references reading_room(reading_room_id) on delete set null
) comment '座位表' ;

insert into seat(seat_id, reading_room_id) values('101-1001-10001','101-1001');
insert into seat(seat_id, reading_room_id) values('101-1001-10002','101-1001');
insert into seat(seat_id, reading_room_id) values('101-1001-10003','101-1001');
insert into seat(seat_id, reading_room_id) values('101-1001-10004','101-1001');
insert into seat(seat_id, reading_room_id) values('101-1001-10005','101-1001');
insert into seat(seat_id, reading_room_id) values('101-1001-10006','101-1001');
insert into seat(seat_id, reading_room_id) values('101-1001-10008','101-1001');
insert into seat(seat_id, reading_room_id) values('101-1001-10009','101-1001');

create table reservation (
    id int not null auto_increment comment '自增主键',
    user_id varchar(32) not null comment '读者编号',
    seat_id varchar(32) not null comment '座位编号',
    reserve_time timestamp not null default current_timestamp comment '预约时间',
    create_time timestamp not null default current_timestamp comment '创建时间',
    primary key(id)
) comment '预约表' ;

insert into reservation(user_id, seat_id) values('3140602023','101-1001-10001');

create table sign_in (
    id int not null auto_increment comment '自增主键',
    user_id varchar(32) not null comment '读者编号',
    seat_id varchar(32) not null comment '座位编号',
    reserve_time timestamp comment '预约时间',
    sign_in_time timestamp not null default current_timestamp comment '签到时间',
    sign_in_type tinyint(2) comment '签到类别',
    primary key(id)
) comment '签到表' ;

insert into sign_in(user_id, seat_id, reserve_time, sign_in_time, sign_in_type) values('3140602023','101-1001-10001','2017-12-26 10:11:09','2017-12-26 10:12:12',1);

create table sign_out (
    id int not null auto_increment comment '自增主键',
    user_id varchar(32) not null comment '读者编号',
    seat_id varchar(32) not null comment '座位编号',
    reserve_time timestamp comment '预约时间',
    sign_out_type tinyint(2) comment '签离类别',
    sign_out_time timestamp not null default current_timestamp comment '签离时间',
    primary key(id)
) comment '签离表' ;

insert into sign_out(user_id, seat_id, reserve_time, sign_out_type, sign_out_time) values('3140602023', '101-1001-10001','2017-12-26 10:11:09',1,'2017-12-26 11:11:11');

create table inobservance_type (
    inobservance_type_id int not null comment '违规类别编号',
    inboservance_type_name varchar(32) not null comment '违规类别名称',
    primary key(inobservance_type_id)
) comment '违规类别表' ;

insert into inobservance_type(inobservance_type_id, inboservance_type_name) values(1,'未签到');
insert into inobservance_type(inobservance_type_id, inboservance_type_name) values(2,'未签离');
insert into inobservance_type(inobservance_type_id, inboservance_type_name) values(3,'临时离开未归');
insert into inobservance_type(inobservance_type_id, inboservance_type_name) values(4,'临时离开未签离');



create table inobservance (
    id int not null auto_increment comment '自增主键',
    user_id varchar(32) not null comment '读者编号',
    seat_id varchar(32) not null comment '座位编号',
    reserve_time timestamp comment '预约时间',
    inobservance_type_id int not null comment '违规类别',
    create_time timestamp not null default current_timestamp comment '生成记录时间',
    primary key(id)
) comment '违规记录表' ;

insert into inobservance(user_id, seat_id, reserve_time, inobservance_type_id, create_time) values('3140602023','101-1001-10001','2017-12-26 10:11:09',3,'2017-12-26 12:12:12');

create table blacklist (
    id int not null auto_increment comment '自增主键',
    user_id varchar(32) not null comment '读者编号',
    inobservance_type_id int not null comment '违规类别',
    count int not null default '1' comment '违规次数',
    create_time timestamp not null default current_timestamp comment '创建时间',
    update_time timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key(id)
) comment '黑名单表' ;

insert into blacklist(user_id, inobservance_type_id, count,create_time) values('3140602023',3,1,'2017-12-26 12:12:12');







insert into blacklist(user_id,inobservance_type_id)
