CREATE TABLE mvc_member(
	num INT primary key auto_increment,
	id VARCHAR(50) UNIQUE NOT NULL,
	pass VARCHAR(50) NOT NULL,
	name VARCHAR(50),
	age INT(3) default 0,
	gender VARCHAR(10),
	joinYN char(1) DEFAULT 'Y',
	regdate TIMESTAMP default now(),
	updatedate TIMESTAMP default now()
);

DESC mvc_member;

SELECT * FROM mvc_member;

-- 비밀번호 찾기에 사용될 코드를 저장할 테이블

CREATE TABLE test_code(
	id VARCHAR(50),
	code char(5)
);

SELECT * FROM test_code;





