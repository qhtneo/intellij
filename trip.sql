use trip;

CREATE TABLE Member(
	user_no int	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	user_id VARCHAR(15) NOT NULL unique,
	user_pw VARCHAR(300) NOT NULL,
	user_nickname VARCHAR(30) NOT NULL unique,
	user_email VARCHAR(100) NOT NULL unique,
	reg_date DATETIME NOT NULL default now(),
	user_email_yn BOOLEAN NOT NULL DEFAULT 1,
	enabled boolean default 1,
    rolename varchar(20) default 'ROLE USER'
);

CREATE TABLE BOARD(
   board_no INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   user_no   int   NOT NULL,
   user_nickname VARCHAR(30),
   board_title   VARCHAR(300) NOT NULL,
   board_summary LONGTEXT NOT NULL,
   board_view   INT   NOT NULL DEFAULT 0,
   input_date   TIMESTAMP DEFAULT NOW(),
   recommend INT NOT NULL   DEFAULT 0,
    foreign key (user_no) references Member(user_no), 
    foreign key (user_nickname) references Member(user_nickname)
);

CREATE TABLE recommend(
    board_no int not null,
    user_id varchar(15) not null,
	foreign key (board_no) references board(board_no) on delete cascade,
    foreign key (user_id) references member(user_id) on delete cascade on update cascade
    );

CREATE TABLE Reply(
	reply_id int auto_increment primary key,
	board_no int ,
    user_no int ,
    reply_content varchar(500) not null,
    reply_input_date DATETIME not null default now(),
    user_nickname varchar(30),
    foreign key(board_no) references BOARD(board_no) on delete cascade,
    foreign key(user_no) references member(user_no) on delete set null on update cascade,
    foreign key(user_nickname) references member(user_nickname) on delete set null
);


desc user;
desc board;
desc reply;