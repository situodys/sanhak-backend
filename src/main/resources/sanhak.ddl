CREATE TABLE `hyundaitransys`.`cafe` (
  `cafe_id` BIGINT NOT NULL AUTO_INCREMENT,
  `cafe_name` VARCHAR(100) NULL,
  `category_name` VARCHAR(50) NULL,
  PRIMARY KEY (`cafe_id`));

CREATE TABLE `hyundaitransys`.`post` (
  `post_id` BIGINT NOT NULL AUTO_INCREMENT,
  `cafe_name` VARCHAR(100) NULL,
  `category_name` VARCHAR(50) NULL,
  `title` VARCHAR(200) NULL,
  `author` VARCHAR(50) NULL,
  `content` VARCHAR(1500) NULL,
  `register_at` DATETIME(6) NULL,
  `link` VARCHAR(400) NULL,
  `similarity` DOUBLE (7,5) NULL,
  PRIMARY KEY (`post_id`));

CREATE TABLE `hyundaitransys`.`comment` (
  `comment_id` BIGINT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(1000) NULL,
  `post_id` BIGINT NULL,
  PRIMARY KEY (`comment_id`),
  FOREIGN KEY (post_id) REFERENCES post (post_id)
);