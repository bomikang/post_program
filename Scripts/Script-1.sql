-- 우편번호
DROP TABLE IF EXISTS post_program.post RESTRICT;

-- 우편번호검색
DROP SCHEMA IF EXISTS post_program;

-- 우편번호검색
CREATE SCHEMA post_program;

-- 우편번호
CREATE TABLE post_program.post (
	zipcode   CHAR(5)     NULL COMMENT '우편번호', -- 우편번호
	sido      VARCHAR(20) NULL COMMENT '시도', -- 시도
	sigungu   VARCHAR(20) NULL COMMENT '시군구', -- 시군구
	doro      VARCHAR(80) NULL COMMENT '도로', -- 도로
	building1 INT(5)      NULL COMMENT '건물번호1', -- 건물번호1
	building2 INT(5)      NULL COMMENT '건물번호2' -- 건물번호2
)
COMMENT '우편번호';

select * from post;

load data local infile 'C:/workspace_java/post_program/DataFiles/강원도.txt' into table post
character set 'euckr' fields terminated by '|' ignore 1 lines
(@zipcode, @sido, @d, @sigungu, @d, @d, @d, @d, @doro, @d, @d, @building1, @building2, @d, @d, @d, @d, @d, @d ,@d, @d, @d, @d, @d, @d, @d)
set zipcode = @zipcode, sido = @sido, sigungu = @sigungu, doro = @doro, building1 = @building1, building2 = @building2;

-- C:\workspace_java\post_program\DataFiles => 집

load data local infile 'C:/workspace_java/post_program/DataFiles/경기도.txt' into table post
character set 'euckr' fields terminated by '|' ignore 1 lines
(@zipcode, @sido, @d, @sigungu, @d, @d, @d, @d, @doro, @d, @d, @building1, @building2, @d, @d, @d, @d, @d, @d ,@d, @d, @d, @d, @d, @d, @d)
set zipcode = @zipcode, sido = @sido, sigungu = @sigungu, doro = @doro, building1 = @building1, building2 = @building2;

delete from post;

select * from post where sido = '강원도';

create index idx_post_sido on post(sido);
create index idx_post_doro on post(doro);

show index from post;

-- type이 ALL이 아니면 인덱스를 타고 있다는 뜻.
explain select * from post where sido = '강원도';
explain select * from post where doro = '임곡로';

select * from post where sido = '경기도';
select * from post where doro like '임곡%';

-- 시도 불러오기 쿼리
select distinct sido from post;

-- 시도와 도로명을 선택하고 검색하면 걔네만 보이게
select zipcode, sido, sigungu, doro, building1, building2 from post where sido='강원도' and doro='임곡로';
