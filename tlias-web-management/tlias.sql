create table dept
(
    id          int unsigned auto_increment comment 'ID, 主键'
        primary key,
    name        varchar(10) not null comment '部门名称',
    create_time datetime    null comment '创建时间',
    update_time datetime    null comment '修改时间',
    constraint name
        unique (name)
)
    comment '部门表';

create table emp
(
    id          int unsigned auto_increment comment 'ID,主键'
        primary key,
    username    varchar(20)                  not null comment '用户名',
    password    varchar(32) default '123456' null comment '密码',
    name        varchar(10)                  not null comment '姓名',
    gender      tinyint unsigned             not null comment '性别, 1:男, 2:女',
    phone       char(11)                     not null comment '手机号',
    job         tinyint unsigned             null comment '职位, 1 班主任, 2 讲师 , 3 学工主管, 4 教研主管, 5 咨询师',
    salary      int unsigned                 null comment '薪资',
    image       varchar(255)                 null comment '头像',
    entry_date  date                         null comment '入职日期',
    dept_id     int unsigned                 null comment '部门ID',
    create_time datetime                     null comment '创建时间',
    update_time datetime                     null comment '修改时间',
    constraint phone
        unique (phone),
    constraint username
        unique (username)
)
    comment '员工表';

create table emp_expr
(
    id      int unsigned auto_increment comment 'ID, 主键'
        primary key,
    emp_id  int unsigned null comment '员工ID',
    begin   date         null comment '开始时间',
    end     date         null comment '结束时间',
    company varchar(50)  null comment '公司名称',
    job     varchar(50)  null comment '职位'
)
    comment '工作经历';

create table emp_log
(
    id           int unsigned auto_increment comment 'ID, 主键'
        primary key,
    operate_time datetime      null comment '操作时间',
    info         varchar(2000) null comment '日志信息'
)
    comment '员工日志表';


