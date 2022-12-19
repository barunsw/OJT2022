create table tb_address (
	seq int(11) not null auto_increment,
	name varchar(100) not null,
	birthday varchar(100),
	gender varchar(100),
	phoneNumber varchar(100),
	address varchar(100),
	primary key (seq)
) ENGINE=InnoDB DEFAULT CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI;