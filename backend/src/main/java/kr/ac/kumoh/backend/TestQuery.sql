-- 영화 데이터 넣기
insert into movie(MOVIE_ID, AVG_OF_GRADE, NUM_OF_GRADES, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE, GENRE)
values(100, 0.0, 0, '12세', '2022년 6월 1일', '147분',  '쥬라기 월드-도미니언', '액션, 어드벤처');
insert into movie(MOVIE_ID, AVG_OF_GRADE, NUM_OF_GRADES, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE, GENRE)
values(101, 0.0, 0, '15세', '2022년 5월 18일', '106분',  '범죄도시 2', '범죄, 액션');
insert into movie(MOVIE_ID, AVG_OF_GRADE, NUM_OF_GRADES, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE, GENRE)
values(102, 0.0, 0, '12세', '2022년 6월 8일', '129분',  '브로커', '드라마');
insert into movie(MOVIE_ID, AVG_OF_GRADE, NUM_OF_GRADES, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE, GENRE)
values(103, 0.0, 0, '12세', '2021년 6월 22일', '130분',  '탑건-매버릭', '액션');
insert into movie(MOVIE_ID, AVG_OF_GRADE, NUM_OF_GRADES, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE, GENRE)
values(104, 0.0, 0, '15세', '2022년 5월 4일', '126분',  '닥터 스트레인지-대혼돈의 멀티버스', '액션, 어드벤처, 환타지');
insert into movie(MOVIE_ID, AVG_OF_GRADE, NUM_OF_GRADES, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE, GENRE)
values(105, 0.0, 0, '전체', '2022년 6월 15일', '105분',  '버즈 라이트이어', '애니메이션');
insert into movie(MOVIE_ID, AVG_OF_GRADE, NUM_OF_GRADES, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE, GENRE)
values(106, 0.0, 0, '전체', '2022년 6월 1일', '96분',  '극장판 포켓몬스터DP-기라티나와 하늘의 꽃다발 쉐이미', '애니메이션');
insert into movie(MOVIE_ID, AVG_OF_GRADE, NUM_OF_GRADES, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE, GENRE)
values(107, 0.0, 0, '15세', '2022년 6월 15일', '137분', '마녀(魔女) Part2. The Other One', '액션');
insert into movie(MOVIE_ID, AVG_OF_GRADE, NUM_OF_GRADES, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE, GENRE)
values(108, 0.0, 0, '15세', '2022년 6월 8일', '126분',  '이공삼칠', '드라마');
insert into movie(MOVIE_ID, AVG_OF_GRADE, NUM_OF_GRADES, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE, GENRE)
values(109, 0.0, 0, '전체', '2022년 6월 1일', '96분', '애프터 양', '드라마');
insert into movie(MOVIE_ID, AVG_OF_GRADE, NUM_OF_GRADES, MEDIA_RATING, RELEASE_DATE, RUNTIME, TITLE, GENRE)
values(110, 0.0, 0, '청불', '2016년 9월 1일', '167분',  '아가씨 확장판', '드라마, 스틸러');

-- 영화관 데이터 넣기
insert into theater(THEATER_ID, FLOOR, SCREEN, NUM_OF_COLUMNS, NUM_OF_ROWS, NUM_OF_SEATS, THEATER_NAME)
values(92, 1, '1관', 10, 10, 100, '부산 CGV');
insert into theater(THEATER_ID, FLOOR, SCREEN, NUM_OF_COLUMNS, NUM_OF_ROWS, NUM_OF_SEATS, THEATER_NAME)
values(93, 2, '2관', 12, 12, 144, '부산 CGV');
insert into theater(THEATER_ID, FLOOR, SCREEN, NUM_OF_COLUMNS, NUM_OF_ROWS, NUM_OF_SEATS, THEATER_NAME)
values(94, 1, '1관', 10, 10, 100, '대전 CGV');
insert into theater(THEATER_ID, FLOOR, SCREEN, NUM_OF_COLUMNS, NUM_OF_ROWS, NUM_OF_SEATS, THEATER_NAME)
values(95, 2, '2관', 12, 12, 144, '대전 CGV');
insert into theater(THEATER_ID, FLOOR, SCREEN, NUM_OF_COLUMNS, NUM_OF_ROWS, NUM_OF_SEATS, THEATER_NAME)
values(96, 1, '1관', 10, 10, 100, '대구 CGV');
insert into theater(THEATER_ID, FLOOR, SCREEN, NUM_OF_COLUMNS, NUM_OF_ROWS, NUM_OF_SEATS, THEATER_NAME)
values(97, 2, '2관', 12, 12, 144, '대구 CGV');
insert into theater(THEATER_ID, FLOOR, SCREEN, NUM_OF_COLUMNS, NUM_OF_ROWS, NUM_OF_SEATS, THEATER_NAME)
values(98, 1, '1관', 10, 10, 100, '구미 CGV');
insert into theater(THEATER_ID, FLOOR, SCREEN, NUM_OF_COLUMNS, NUM_OF_ROWS, NUM_OF_SEATS, THEATER_NAME)
values(99, 2, '2관', 12, 12, 144, '구미 CGV');

-- 상영 시간표 데이터 넣기
-- 구미 CGV
insert into movie_schedule(MS_ID, PRICE, SCREEN_DATE, SCREEN_TIME, MOVIE_ID, THEATER_ID, REMAINING_SEAT, DISCOUNT_RATE, DISCOUNT_POLICY)
values(201, 12000, '2021년 6월 14일', '12:00', 101, 98, 100, 0, 'none');
insert into movie_schedule(MS_ID, PRICE, SCREEN_DATE, SCREEN_TIME, MOVIE_ID, THEATER_ID, REMAINING_SEAT, DISCOUNT_RATE, DISCOUNT_POLICY)
values(202, 12000, '2021년 6월 14일', '12:00', 101, 99, 144, 0, 'none');
insert into movie_schedule(MS_ID, PRICE, SCREEN_DATE, SCREEN_TIME, MOVIE_ID, THEATER_ID, REMAINING_SEAT, DISCOUNT_RATE, DISCOUNT_POLICY)
values(203, 12000, '2021년 6월 14일', '12:30', 101, 98, 100, 0, 'none');
insert into movie_schedule(MS_ID, PRICE, SCREEN_DATE, SCREEN_TIME, MOVIE_ID, THEATER_ID, REMAINING_SEAT, DISCOUNT_RATE, DISCOUNT_POLICY)
values(204, 12000, '2021년 6월 14일', '14:00', 101, 99, 144, 0, 'none');
-- 대구 CGV
insert into movie_schedule(MS_ID, PRICE, SCREEN_DATE, SCREEN_TIME, MOVIE_ID, THEATER_ID, REMAINING_SEAT, DISCOUNT_RATE, DISCOUNT_POLICY)
values(205, 12000, '2021년 6월 14일', '14:00', 100, 96, 100, 0, 'none');
insert into movie_schedule(MS_ID, PRICE, SCREEN_DATE, SCREEN_TIME, MOVIE_ID, THEATER_ID, REMAINING_SEAT, DISCOUNT_RATE, DISCOUNT_POLICY)
values(206, 12000, '2021년 6월 14일', '14:30', 102, 97, 144, 0, 'none');
insert into movie_schedule(MS_ID, PRICE, SCREEN_DATE, SCREEN_TIME, MOVIE_ID, THEATER_ID, REMAINING_SEAT, DISCOUNT_RATE, DISCOUNT_POLICY)
values(207, 12000, '2021년 6월 14일', '15:00', 103, 96, 100, 0, 'none');
insert into movie_schedule(MS_ID, PRICE, SCREEN_DATE, SCREEN_TIME, MOVIE_ID, THEATER_ID, REMAINING_SEAT, DISCOUNT_RATE, DISCOUNT_POLICY)
values(208, 12000, '2021년 6월 14일', '15:30', 104, 97, 144, 0, 'none');
-- 대전 CGV
insert into movie_schedule(MS_ID, PRICE, SCREEN_DATE, SCREEN_TIME, MOVIE_ID, THEATER_ID, REMAINING_SEAT, DISCOUNT_RATE, DISCOUNT_POLICY)
values(209, 12000, '2021년 6월 14일', '16:00', 105, 94, 100, 0, 'none');
insert into movie_schedule(MS_ID, PRICE, SCREEN_DATE, SCREEN_TIME, MOVIE_ID, THEATER_ID, REMAINING_SEAT, DISCOUNT_RATE, DISCOUNT_POLICY)
values(210, 12000, '2021년 6월 14일', '17:00', 106, 95, 144, 0, 'none');
insert into movie_schedule(MS_ID, PRICE, SCREEN_DATE, SCREEN_TIME, MOVIE_ID, THEATER_ID, REMAINING_SEAT, DISCOUNT_RATE, DISCOUNT_POLICY)
values(211, 12000, '2021년 6월 14일', '18:00', 107, 95, 144, 0, 'none');
-- 부산 CGV
insert into movie_schedule(MS_ID, PRICE, SCREEN_DATE, SCREEN_TIME, MOVIE_ID, THEATER_ID, REMAINING_SEAT, DISCOUNT_RATE, DISCOUNT_POLICY)
values(212, 12000, '2021년 6월 14일', '19:00', 108, 92, 100, 0, 'none');
insert into movie_schedule(MS_ID, PRICE, SCREEN_DATE, SCREEN_TIME, MOVIE_ID, THEATER_ID, REMAINING_SEAT, DISCOUNT_RATE, DISCOUNT_POLICY)
values(213, 12000, '2021년 6월 14일', '20:00', 109, 92, 100, 0, 'none');
insert into movie_schedule(MS_ID, PRICE, SCREEN_DATE, SCREEN_TIME, MOVIE_ID, THEATER_ID, REMAINING_SEAT, DISCOUNT_RATE, DISCOUNT_POLICY)
values(214, 12000, '2021년 6월 14일', '21:00', 110, 93, 144, 0, 'none');

-- //쥬라기 100
-- 감독: 콜린 트레보로우
-- 배우: 크리스 프랫 ,  브라이스 달라스 하워드 ,  드완다 와이즈 ,  로라 던 ,  제프 골드브럼 ,  샘 닐
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(308, '콜린 트레보로우', '감독', 100);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(309, '크리스 프랫', '배우', 100);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(310, '브라이스 달라스 하워드', '배우', 100);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(311, '드완다 와이즈', '배우', 100);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(312, '로라 던', '배우', 100);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(313, '제프 골드브럼', '배우', 100);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(314, '샘 닐', '배우', 100);

-- //브로커 102
-- 감독 : 고레에다 히로카즈
-- 배우 : 송강호 ,  강동원 ,  배두나 ,  이지은 ,  이주영
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(315, '고레에다 히로카즈', '감독', 102);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(316, '송강호', '배우', 102);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(317, '강동원', '배우', 102);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(318, '배두나', '배우', 102);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(319, '이지은', '배우', 102);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(320, '이주영', '배우', 102);

-- // 탑건-매버릭 103
-- 감독 : 조셉 코신스키 /
-- 배우 : 톰 크루즈 ,  마일즈 텔러 ,  제니퍼 코넬리
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(321, '조셉 코신스키', '감독', 103);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(322, '톰 크루즈', '배우', 103);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(323, '마일즈 텔러', '배우', 103);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(324, '제니퍼 코넬리', '배우', 103);

-- 104 닥터 스트레인지-대혼돈의 멀티버스
-- 감독 : 샘 레이미 / 배우 :
-- 베네딕트 컴버배치 ,  엘리자베스 올슨 ,  베네딕트 웡 ,  레이첼 맥아담스 ,  치웨텔 에지오포 ,  소치틀 고메즈
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(325, '샘 레이미', '감독', 104);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(326, '베네딕트 컴버배치', '배우', 104);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(327, '엘리자베스 올슨', '배우', 104);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(328, '베네딕트 웡', '배우', 104);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(329, '레이첼 맥아담스', '배우', 104);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(330, '치웨텔 에지오포', '배우', 104);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(331, '소치틀 고메즈', '배우', 104);

-- 105 버즈 라이트이어
-- 감독 : 앤거스 맥클레인 / 배우 :
-- 크리스 에반스 ,  타이카 와이티티 ,  피터 손
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(332, '앤거스 맥클레인', '감독', 105);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(333, '크리스 에반스', '배우', 105);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(334, '타이카 와이티티', '배우', 105);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(335, '피터 손', '배우', 105);

-- 106 극장판 포켓몬스터DP-기라티나와 하늘의 꽃다발 쉐이미
-- 감독 : 유야마 쿠니히코 /
-- 배우 : 이선호 ,  김영선
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(336, '유야마 쿠니히코', '감독', 106);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(337, '이선호', '배우', 106);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(338, '김영선', '배우', 106);

-- 107 마녀(魔女) Part2. The Other One
-- 감독 : 박훈정 /
-- 배우 : 신시아 ,  박은빈 ,  서은수 ,  진구 ,  성유빈 ,  조민수 ,  이종석 ,  김다미
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(339, '박훈정', '감독',107 );
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(340, '신시아', '배우',107 );
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(341, '박은빈', '배우',107 );
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(342, '서은수', '배우',107 );
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(343, '진구', '배우',107 );
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(344, '성유빈', '배우',107 );
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(345, '조민수', '배우',107 );
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(346, '이종석', '배우',107 );
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(347, '김다미', '배우',107 );

-- 108 이공삼칠
-- 감독 : 모홍진 / 프로듀서 : 김대근 /
-- 배우 : 홍예지 ,  김지영 ,  김미화 ,  황석정 ,  신은정 ,  전소민 ,  윤미경 ,  정인기
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(348, '모홍진', '감독',108 );
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(349, '홍예지', '배우',108 );
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(350, '김지영', '배우',108 );
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(351, '김미화', '배우',108 );
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(352, '황석정', '배우',108 );
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(353, '신은정', '배우',108 );
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(354, '전소민', '배우',108 );
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(355, '윤미경', '배우',108 );
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(356, '정인기', '배우',108 );

-- 109 애프터 양
-- 감독 : 코고나다
-- 배우 : 콜린 파렐 ,  조디 터너 스미스 ,  저스틴 H.민 ,  말레아 엠마 찬드로위자야
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(357, '코고나다', '감독', 109);
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(358, '콜린 파렐', '배우',109 );
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(359, '조디 터너 스미스', '배우',109 );
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(360, '저스틴 H.민', '배우',109 );
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(361, '말레아 엠마 찬드로위자야', '배우',109);

-- 110 아가씨 확장판
-- 감독 : 박찬욱
-- 배우 : 김민희 ,  김태리 ,  하정우 ,  조진웅 ,  김해숙 ,  문소리
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(362, '박찬욱', '감독',110 );
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(363, '김민희', '배우',110 );
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(364, '김태리', '배우',110 );
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(365, '하정우', '배우',110 );
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(366, '조진웅', '배우',110 );
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(367, '김해숙', '배우',110 );
insert into person(PERSON_ID, NAME, OCCUPATION, MOVIE_ID) values(368, '문소리', '배우',110 );

-- 유저 데이터 넣기
insert into user(USERS_ID, AGE, GENDER, USER_ID, USER_PW) values(1, 23, '남자', 'id1', 'pw1');
insert into user(USERS_ID, AGE, GENDER, USER_ID, USER_PW) values(2, 33, '남자', 'id2', 'pw2');
insert into user(USERS_ID, AGE, GENDER, USER_ID, USER_PW) values(3, 19, '여자', 'id3', 'pw3');
insert into user(USERS_ID, AGE, GENDER, USER_ID, USER_PW) values(4, 31, '여자', 'id4', 'pw4');

-- 영화 예약 데이터 넣기

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
