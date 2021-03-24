drop table if exists sys_equipment_info;
create table sys_equipment_info
(
    equipment_info_id       int(11)         not null auto_increment    comment '设备id',
	equipment_code     varchar(64)     not null                   comment '设备编码',
	authorization_code     varchar(64)     not null                   comment '设备授权码',
	run_time     bigint         not null                   comment '运行时间毫秒',
	status        char(1)         not null                   comment '状态（0正常 1停用）',
    create_by     varchar(64)     default ''                 comment '创建者',
    create_time   datetime                                   comment '创建时间',
    update_by     varchar(64) 	  default ''			     comment '更新者',
	update_time   datetime                                   comment '更新时间',
    remark 		  varchar(500) 	  default null 				 comment '备注',
	primary key (equipment_info_id)
) engine=innodb default charset=utf8 comment = '设备信息表';

-- ----------------------------
-- 初始化-岗位信息表数据
-- ----------------------------
insert into sys_equipment_info values(1, 'N0001',  'jfdksljfldjs',    114545465655656564, '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '');