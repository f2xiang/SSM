

-- 创建数据库
CREATE DATABASE seckill;

-- 使用数据库
USE seckill;

-- 创建表  （秒杀库存表）
CREATE TABLE seckill(
  `seckill_id` bigint not null auto_increment comment '商品库存id',
  `name` varchar(120) not null comment '商品名称',
  `number` int not null comment '库存数量',
  `start_time` timestamp not null comment '秒杀开启时间',
  `end_time` timestamp not null comment '秒杀结束时间',
  `create_time` timestamp not null default current_timestamp comment '创建时间',
  primary key (seckill_id),
  key idx_start_time(start_time),
  key idx_end_time(end_time),
  key idx_create_time(create_time)
)ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET = UTF8 comment = '秒杀库存表'

-- 初始化数据
insert into 
    seckill (name, number, start_time, end_time)
values
    ('1000元秒杀iphone6', 100, '2016-11-11 00:00:00', '2016-11-12 00:00:00'),
    ('500元秒杀iphone5', 200, '2016-12-12 00:00:00', '2016-12-13 00:00:00'),
    ('300元秒杀小米2', 300, '2016-11-10 00:00:00', '2016-11-11 00:00:00'),
    ('200元秒杀红米2', 400, '2016-11-01 00:00:00', '2016-11-02 00:00:00');


-- 秒杀成功明细表
-- 用户登录认证 相关 信息 
CREATE TABLE success_killed(
	`seckill_id` bigint not null comment '秒杀商品id',
	`user_phone` bigint not null comment '用户手机号',
	`state` int(5) not null default -1 comment '状态标识: -1:无效   0: 成功   1: 已付款',
	`create_time` timestamp not null comment '秒杀成功时间',
	primary key (seckill_id, user_phone),   /*联合主键 */
	key idx_create_time(create_time)
)ENGINE = INNODB  DEFAULT CHARSET = UTF8 comment = '秒杀成功明细表';


