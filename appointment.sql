create table user_info (
    user_id varchar(32) not null comment '读者编号',
    order_status tinyint(2) default '0' comment '预约状态',
    sign_in_status tinyint(2) default '0' comment '签到状态',
    sign_out_status tinyint(2) default '0' comment '签离状态',
    primary key(user_id)
) comment '读者信息表' ;
 
create table administrator (
    admin_id varchar(8) not null comment '管理员编号',
    admin_name varchar(64) not null comment '管理员姓名',
    admin_account varchar(64) not null comment '管理员账号',
    admin_account varchar(64) not null comment '管理员密码',
    primary key(admin_id)
) comment '管理员表' ; 

create table floor (
    floor_id varchar(32) not null comment '楼层编号',
    floor_name varchar(64) not null comment '楼层名称',
    primary key(floor_id)
) comment '楼层表' ; 

create table reading_room (
    reading_room_id varchar(32) not null comment '阅览室编号',
    reading_room_id varchar(64) not null comment '阅览室名称',
    floor_id varchar(32) comment '阅览室所属楼层',
    seat_count int not null comment '座位数量',
    primary key(reading_room_id),
    foreign key(floor_id) references floor(floor_id) on delete set null
) comment '阅览室表' ;

create
table seat (

    seat_id varchar(32) not null comment '座位编号',

    seat_status tinyint(2) default '0' comment
'使用状态',

    reading_room_id varchar(32) comment '座位所属阅览室',

    primary key(seat_id),

    foreign key(reading_room_id) references
reading_room(reading_room_id) on delete set null

)
comment '座位表' ;

 

create
table order (

    id int not null auto_increment comment '自增主键',

    user_id varchar(32) not null comment '读者编号',

    seat_id varchar(32) not null comment '座位编号',

    order_time timestamp(8) not null default
current_timestamp comment '预约时间',

    create_time timestamp not null default
current_timestamp comment '创建时间',

    primary key(id)

)
comment '预约表' ;

