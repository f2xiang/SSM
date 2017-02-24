

-- �������ݿ�
CREATE DATABASE seckill;

-- ʹ�����ݿ�
USE seckill;

-- ������  ����ɱ����
CREATE TABLE seckill(
  `seckill_id` bigint not null auto_increment comment '��Ʒ���id',
  `name` varchar(120) not null comment '��Ʒ����',
  `number` int not null comment '�������',
  `start_time` timestamp not null comment '��ɱ����ʱ��',
  `end_time` timestamp not null comment '��ɱ����ʱ��',
  `create_time` timestamp not null default current_timestamp comment '����ʱ��',
  primary key (seckill_id),
  key idx_start_time(start_time),
  key idx_end_time(end_time),
  key idx_create_time(create_time)
)ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET = UTF8 comment = '��ɱ����'

-- ��ʼ������
insert into 
    seckill (name, number, start_time, end_time)
values
    ('1000Ԫ��ɱiphone6', 100, '2016-11-11 00:00:00', '2016-11-12 00:00:00'),
    ('500Ԫ��ɱiphone5', 200, '2016-12-12 00:00:00', '2016-12-13 00:00:00'),
    ('300Ԫ��ɱС��2', 300, '2016-11-10 00:00:00', '2016-11-11 00:00:00'),
    ('200Ԫ��ɱ����2', 400, '2016-11-01 00:00:00', '2016-11-02 00:00:00');


-- ��ɱ�ɹ���ϸ��
-- �û���¼��֤ ��� ��Ϣ 
CREATE TABLE success_killed(
	`seckill_id` bigint not null comment '��ɱ��Ʒid',
	`user_phone` bigint not null comment '�û��ֻ���',
	`state` int(5) not null default -1 comment '״̬��ʶ: -1:��Ч   0: �ɹ�   1: �Ѹ���',
	`create_time` timestamp not null comment '��ɱ�ɹ�ʱ��',
	primary key (seckill_id, user_phone),   /*�������� */
	key idx_create_time(create_time)
)ENGINE = INNODB  DEFAULT CHARSET = UTF8 comment = '��ɱ�ɹ���ϸ��';


