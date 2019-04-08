create table transaction
(
	transation_id varchar(32) null,
	party_id varchar(32) null,
	user_id varchar(40) null,
	money int null,
	payment_time timestamp null
)
;

create table user
(
	open_id varchar(40) not null
		primary key,
	user_name varchar(20) null,
	header int null,
	account int null,
	session int null,
	login_time int null
)
comment '��Ҫ���ڱ�ʶ�ͼ�¼�û�������Ϣ�����忴΢�ŵ�½��΢�Žӿ�' charset=utf8
;

create table party
(
	party_id varchar(32) not null
		primary key,
	party_subject varchar(45) null comment '�ۻ�����',
	party_detail varchar(200) null comment '�ۻ���ϸ��Ϣ',
	party_date timestamp null comment '�ۻ��������',
	deposit decimal(10,2) null comment 'Ѻ��',
	latitude float null comment '�ۻ�ص�γ��',
	longtitude float null comment '�ۻ�ص㾭��',
	originator varchar(40) null comment '������',
	total_sum decimal(10,2) null comment '�ܽ�Ǯ��',
	participate_time timestamp null,
	constraint user_org_id
		foreign key (originator) references user (open_id)
)
charset=utf8
;

create index user_org_id_idx
	on party (originator)
;

create table party_record
(
	record_id varchar(32) not null
		primary key,
	party_id varchar(32) not null,
	user_id varchar(40) not null,
	status enum('unfinished', 'finished') null comment '����δ��ɣ������',
	constraint party_record_party_party_id_fk
		foreign key (party_id) references party (party_id),
	constraint party_record_user_open_id_fk
		foreign key (user_id) references user (open_id)
)
charset=utf8
;

create index party_id_FK_idx
	on party_record (party_id)
;

create index user_id_FK_idx
	on party_record (user_id)
;

