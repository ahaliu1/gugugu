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
comment '主要用于标识和记录用户基本信息，具体看微信登陆的微信接口' charset=utf8
;

create table party
(
	party_id varchar(32) not null
		primary key,
	party_subject varchar(45) null comment '聚会主题',
	party_detail varchar(200) null comment '聚会详细信息',
	party_date timestamp null comment '聚会具体日期',
	deposit decimal(10,2) null comment '押金',
	latitude float null comment '聚会地点纬度',
	longtitude float null comment '聚会地点经度',
	originator varchar(40) null comment '发起者',
	total_sum decimal(10,2) null comment '总交钱数',
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
	status enum('unfinished', 'finished') null comment '加入未完成，活动结束',
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

