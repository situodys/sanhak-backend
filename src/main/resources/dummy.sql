DELIMITER $$
DROP PROCEDURE IF EXISTS loopInsert$$

CREATE PROCEDURE loopInsert()
BEGIN
    DECLARE i INT DEFAULT 1;

    WHILE i <= 500 DO
        INSERT INTO post(post_id , cafe_name, category_name , title, author, content, register_at, link, similarity)
          VALUES(i, concat('카페이름',i), concat('게시판이름',i), concat('제목', i),  concat('작성자', i),  concat('내용', i), now(),  concat('링크', i), i/10);
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER $$
CALL loopInsert;

DELIMITER $$
DROP PROCEDURE IF EXISTS loopInsert2$$

CREATE PROCEDURE loopInsert2()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE k INT DEFAULT 1;
    DECLARE j INT DEFAULT 501;
    WHILE k <= 5 DO
        SET i = 1;
        WHILE i <= 500 DO
            INSERT INTO comment(comment_id, content, post_id)
              VALUES(j, concat('내용',j), i);
            SET i = i + 1;
            SET j = j + 1;
        END WHILE;
        SET k = k + 1;
    END WHILE;
END$$
DELIMITER $$

CALL loopInsert2;