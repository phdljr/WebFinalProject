-- 테이블 검색
SELECT * FROM MOVIE;
SELECT * FROM THEATER;
SELECT * FROM MOVIE_SCHEDULE;
SELECT * FROM BOOKING_DETAILS;
SELECT * FROM USER;
SELECT * FROM BOOK;
SELECT * FROM SEATS;
SELECT * FROM PERSON;
SELECT * FROM COMMENTS;

-- 영화 데이터 넣기
insert into movie(MOVIE_ID, AVG_OF_GRADE, NUM_OF_GRADES, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE, GENRE)
values(100, 0.0, 0, '12세', '2022년 6월 1일', '147분',  '쥬라기 월드-도미니언', '액션, 어드벤처');
insert into movie(MOVIE_ID, AVG_OF_GRADE, NUM_OF_GRADES, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE, GENRE)
values(101, 0.0, 0,'15세', '2022년 5월 18일', '106분',  '범죄도시 2', '범죄, 액션');
insert into movie(MOVIE_ID, AVG_OF_GRADE, NUM_OF_GRADES, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE, GENRE)
values(102, 0.0, 0,'12세', '2022년 6월 8일', '129분',  '브로커', '드라마');
insert into movie(MOVIE_ID, AVG_OF_GRADE, NUM_OF_GRADES, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE, GENRE)
values(103, 0.0, 0,'12세', '2021년 6월 22일', '130분',  '탑건-매버릭', '액션');
insert into movie(MOVIE_ID, AVG_OF_GRADE, NUM_OF_GRADES, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE, GENRE)
values(104, 0.0, 0,'15세', '2022년 5월 4일', '126분',  '닥터 스트레인지-대혼돈의 멀티버스', '액션, 어드벤처, 환타지');
insert into movie(MOVIE_ID, AVG_OF_GRADE, NUM_OF_GRADES, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE, GENRE)
values(105, 0.0, 0,'전체', '2022년 6월 15일', '105분',  '버즈 라이트이어', '애니메이션');
insert into movie(MOVIE_ID, AVG_OF_GRADE, NUM_OF_GRADES, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE, GENRE)
values(106, 0.0, 0,'전체', '2022년 6월 1일', '96분',  '극장판 포켓몬스터DP-기라티나와 하늘의 꽃다발 쉐이미', '애니메이션');
insert into movie(MOVIE_ID, AVG_OF_GRADE, NUM_OF_GRADES, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE, GENRE)
values(107, 0.0, 0,'15세', '2022년 6월 15일', '137분', '마녀(魔女) Part2. The Other One', '액션');
insert into movie(MOVIE_ID, AVG_OF_GRADE, NUM_OF_GRADES, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE, GENRE)
values(108, 0.0, 0,'15세', '2022년 6월 8일', '126분',  '이공삼칠', '드라마');

-- 영화관 데이터 넣기
insert into theater(THEATER_ID, FLOOR, SCREEN, NUM_OF_COLUMNS, NUM_OF_ROWS, NUM_OF_SEATS, THEATER_NAME) values(97, 1, '1관', 7, 12, 84, '구미 CGV');
insert into theater(THEATER_ID, FLOOR, SCREEN, NUM_OF_COLUMNS, NUM_OF_ROWS, NUM_OF_SEATS, THEATER_NAME) values(98, 1, '1관', 10, 10, 100, '대구 CGV');
insert into theater(THEATER_ID, FLOOR, SCREEN, NUM_OF_COLUMNS, NUM_OF_ROWS, NUM_OF_SEATS, THEATER_NAME) values(99, 2, '2관', 9, 9, 81, '대구 CGV');

-- 상영 시간표 데이터 넣기
insert into movie_schedule(MS_ID, PRICE, SCREEN_DATE, SCREEN_TIME, MOVIE_ID, THEATER_ID, REMAINING_SEAT)
values(200, 12000, '2021년 6월 14일', '11:30', 101, 97, 84);
insert into movie_schedule(MS_ID, PRICE, SCREEN_DATE, SCREEN_TIME, MOVIE_ID, THEATER_ID, REMAINING_SEAT)
values(201, 12000, '2021년 6월 14일', '12:00', 101, 98, 100);
insert into movie_schedule(MS_ID, PRICE, SCREEN_DATE, SCREEN_TIME, MOVIE_ID, THEATER_ID, REMAINING_SEAT)
values(203, 12000, '2021년 6월 14일', '12:30', 101, 99, 81);
insert into movie_schedule(MS_ID, PRICE, SCREEN_DATE, SCREEN_TIME, MOVIE_ID, THEATER_ID, REMAINING_SEAT)
values(204, 12000, '2021년 6월 14일', '13:00', 101, 99, 81);


-- 감독 & 배우 데이터 넣기
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(300, '이상용', '감독', 102);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(301, '마동석', '배우', 102);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(302, '손석구', '배우', 102);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(303, '최귀화', '배우', 102);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(304, '박지환', '배우', 102);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(305, '허동원', '배우', 102);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(306, '하준', '배우', 102);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(307, '정재광', '배우', 102);
