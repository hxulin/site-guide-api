create table sg_client_conf
(
   id                   	bigint not null comment '主键',
   local_dns_address      varchar(50) comment '本地DNS网关地址',
   min_time             	int comment '更新本地IP的最短时间间隔',
   max_time             	int comment '更新本地IP的最长时间间隔',
   create_time          	datetime comment '创建时间',
   update_time          	datetime comment '上次修改时间',
   primary key (id)
);


create table sg_user
(
   id                   	bigint not null comment '主键',
   nickname               varchar(50) comment '昵称',
   auth_code             	varchar(50) comment '下载授权码',
   lan_ip             	  varchar(50) comment '局域网IP',
   last_updated           datetime comment 'IP地址最近一次更新时间',
   status                 tinyint comment '状态: 0正常, 1禁用',
   create_time          	datetime comment '创建时间',
   update_time          	datetime comment '上次修改时间',
   primary key (id),
   unique key uniq_nickname (nickname)
);

create table sg_project
(
   id                   	bigint not null comment '主键',
   name                   varchar(50) comment '项目名称',
   entrance_url           varchar(500) comment '访问地址',
   type                   tinyint comment '项目类型: 0前端项目, 1后端项目',
   sequence               int comment '排序号',
   status                 tinyint comment '状态: 0正常, 1关闭',
   create_time          	datetime comment '创建时间',
   update_time          	datetime comment '上次修改时间',
   primary key (id)
);

create table sg_hot_pages
(
   id                   	bigint not null comment '主键',
   name                   varchar(50) comment '页面名称',
   url                    varchar(500) comment '访问地址',
   sequence               int comment '排序号',
   status                 tinyint comment '状态: 0展示, 1隐藏',
   remark                 varchar(50) comment '备注',
   create_time          	datetime comment '创建时间',
   update_time          	datetime comment '上次修改时间',
   primary key (id)
);

create table sg_task
(
   id                   	bigint not null comment '主键',
   uid                    bigint comment '所属用户',
   name                   varchar(50) comment '任务名称',
   heartbeat              int comment '心跳时间, 单位: 秒',
   status                 tinyint comment '状态: 0 运行中, 1 已停止, 2 正在启动, 3 正在关停',
   remark                 varchar(50) comment '备注',
   create_time          	datetime comment '创建时间',
   update_time          	datetime comment '上次修改时间',
   primary key (id)
);

create table sg_instruction
(
   id                   	bigint not null comment '主键',
   task_id                 bigint comment '所属任务',
   `desc`                 varchar(50) comment '指令描述',
   version                int comment '指令版本',
   content                varchar(20000) comment '指令内容',
   status                 tinyint comment '状态: 0 已上线, 1 待上线',
   update_time          	datetime comment '上次修改时间',
   primary key (id)
);

/* 增加任务扫描心跳时间 */
alter table sg_user add column last_heartbeat datetime comment '任务扫描心跳时间';
