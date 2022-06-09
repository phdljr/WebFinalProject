-- 테이블 검색
SELECT * FROM MOVIE;
SELECT * FROM THEATER;
SELECT * FROM MOVIE_SCHEDULE;
SELECT * FROM BOOKING_DETAILS;
SELECT * FROM USER;
SELECT * FROM BOOK;
SELECT * FROM SEATS;
SELECT * FROM COMMENTS;

-- 영화 데이터 넣기
insert into movie(MOVIE_ID, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE ) values(100, '15세', '2021년 6월 6일', '150분',  '태극기 휘날리며');
insert into movie(MOVIE_ID, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE ) values(101, '12세', '2022년 6월 1일', '147분',  '쥬라기 월드-도미니언');
insert into movie(MOVIE_ID, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE ) values(102, '15세', '2022년 5월 18일', '106분',  '범죄도시 2');
insert into movie(MOVIE_ID, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE ) values(103, '12세', '2022년 6월 8일', '129분',  '브로커');
insert into movie(MOVIE_ID, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE ) values(104, '12세', '2021년 6월 1일', '101분',  '카시오페아');
insert into movie(MOVIE_ID, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE ) values(105, '15세', '2022년 5월 4일', '126분',  '닥터 스트레인지-대혼돈의 멀티버스');

-- 영화관 데이터 넣기
insert into theater(THEATER_ID, FLOOR, SCREEN, NUM_OF_COLUMNS, NUM_OF_ROWS, NUM_OF_SEATS) values(98, 1, '1관', 13, 13, 169);
insert into theater(THEATER_ID, FLOOR, SCREEN, NUM_OF_COLUMNS, NUM_OF_ROWS, NUM_OF_SEATS) values(99, 2, '2관', 8, 8, 64);

-- 상영 시간표 데이터 넣기
insert into movie_schedule(MS_ID, PRICE, SCREEN_DATE, SCREEN_TIME, MOVIE_ID, THEATER_ID, REMAINING_SEAT)
values(200, 9000, '2021년 6월 8일', '10:00', 102, 98, 96);
insert into movie_schedule(MS_ID, PRICE, SCREEN_DATE, SCREEN_TIME, MOVIE_ID, THEATER_ID, REMAINING_SEAT)
values(201, 12000, '2021년 6월 8일', '14:00', 101, 99, 35);
insert into movie_schedule(MS_ID, PRICE, SCREEN_DATE, SCREEN_TIME, MOVIE_ID, THEATER_ID, REMAINING_SEAT)
values(203, 12000, '2021년 6월 8일', '15:00', 101, 99, 35);
insert into movie_schedule(MS_ID, PRICE, SCREEN_DATE, SCREEN_TIME, MOVIE_ID, THEATER_ID, REMAINING_SEAT)
values(204, 12000, '2021년 6월 8일', '16:00', 101, 99, 35);
insert into movie_schedule(MS_ID, PRICE, SCREEN_DATE, SCREEN_TIME, MOVIE_ID, THEATER_ID, REMAINING_SEAT)
values(205, 12000, '2021년 6월 8일', '17:00', 101, 98, 96);
insert into movie_schedule(MS_ID, PRICE, SCREEN_DATE, SCREEN_TIME, MOVIE_ID, THEATER_ID, REMAINING_SEAT)
values(206, 12000, '2021년 6월 8일', '18:00', 101, 98, 96);

