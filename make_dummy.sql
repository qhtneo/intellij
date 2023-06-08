use trip;

DELIMITER $$ 
CREATE PROCEDURE makedummy()
BEGIN
    DECLARE i INT DEFAULT 1; 
    WHILE (i <= 100) DO 
        INSERT INTO BOARD(user_no, user_nickname, board_title, board_summary, local_category)
        VALUES(1, 'aaaa', 'dummy', 'bulabula', '서울특별시');
        SET i = i + 1;
    END WHILE;
    
	UPDATE BOARD SET local_category = '전라남도' 		WHERE board_no < 100;
	UPDATE BOARD SET local_category = '충청남도' 		WHERE board_no < 88;
	UPDATE BOARD SET local_category = '대전광역시' 	WHERE board_no < 75;
	UPDATE BOARD SET local_category = '경상북도' 		WHERE board_no < 50;
	UPDATE BOARD SET local_category = '제주특별자치도' WHERE board_no < 43;
	UPDATE BOARD SET local_category = '강원도'		WHERE board_no < 35;
	UPDATE BOARD SET local_category = '대구광역시' 	WHERE board_no < 25;
	UPDATE BOARD SET local_category = '부산광역시' 	WHERE board_no < 17;
	UPDATE BOARD SET local_category = '서울특별시' 	WHERE board_no < 10;
    
	INSERT INTO MEMBER (USER_id, USER_PW, USER_NICKNAME, USER_EMAIL)
			VALUES ('admin01', '{bcrypt}$2a$10$JD5PYjwzAK/kAcgFtXDeX.jFMEtBt8opv/tEDO5bXNYTxSVh6AqJi', 'Admin', 'admin@admin.com');
	INSERT INTO MEMBER (USER_id, USER_PW, USER_NICKNAME, USER_EMAIL)
			VALUES ('aaaa', '{bcrypt}$2a$10$EU.KFqAZHtq.QENqncjpKeIcxuqwXY9LvzC45/oOsZlT3zKr.FjUe', 'aaaa', 'aaaa@aaaa.com');
	UPDATE MEMBER SET rolename ='ROLE_ADMIN' where user_id ='admin01';

END$$
DELIMITER ;

CALL makedummy();
commit;