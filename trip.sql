use trip;

DROP TABLE MEMBER
;
CREATE TABLE MEMBER(
   user_no			BIGINT			NOT NULL 	AUTO_INCREMENT PRIMARY KEY,
   user_id			VARCHAR(15)		NOT NULL 	COMMENT '아이디(계정)',
   user_pw			VARCHAR(255)	NOT NULL,
   user_nickname	VARCHAR(30)		NOT NULL,
   user_email		VARCHAR(255)	NOT NULL,
   reg_date			DATETIME		NOT NULL	DEFAULT now(),
   user_email_yn 	BOOLEAN			NOT NULL 	DEFAULT 0,
   enabled			BOOLEAN			NOT NULL	DEFAULT 1,
   rolename			VARCHAR(255) 	NOT NULL	DEFAULT 'ROLE_USER',

	CONSTRAINT uq_Member_user_id 		UNIQUE (user_id),
	CONSTRAINT uq_Member_user_nickname 	UNIQUE (user_nickname),
    CONSTRAINT uq_Member_user_email		UNIQUE (user_email)
);

DROP TABLE BOARD;
CREATE TABLE BOARD(
   board_no			BIGINT			NOT NULL	AUTO_INCREMENT PRIMARY KEY,
   user_no 			BIGINT			NOT NULL,
   user_nickname 	VARCHAR(30)		NOT NULL,
   board_title		VARCHAR(255)	NOT NULL,
   board_summary	LONGTEXT		NOT NULL,
   board_view		INT				NOT NULL	DEFAULT 0,
   input_date		TIMESTAMP		NOT NULL	DEFAULT	NOW(),
   recommend		INT				NOT NULL	DEFAULT 0,
   local_category	VARCHAR(255)	NOT NULL
);

DROP TABLE RECOMMEND;
CREATE TABLE RECOMMEND(
    board_no 	INT 			NOT NULL,
    user_id 	VARCHAR(255) 	NOT NULL
    );

DROP TABLE REPLY;
CREATE TABLE REPLY(
	reply_id 			INT 						AUTO_INCREMENT PRIMARY KEY,
	board_no 			INT ,
	user_no 			INT ,
	reply_content 		VARCHAR(500) 	NOT NULL,
	reply_input_date	DATETIME 		NOT NULL 	DEFAULT now(),
	user_nickname		VARCHAR(255)
);

DROP TABLE NOTICE;
CREATE TABLE NOTICE(
   user_no     	BIGINT,
   board_no		INT,
   board_title		VARCHAR(255),
   reply_id 		INT,
   reply_content	VARCHAR(255),
   message     	VARCHAR(255)
);

commit;