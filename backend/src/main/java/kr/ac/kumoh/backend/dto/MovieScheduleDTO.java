package kr.ac.kumoh.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MovieScheduleDTO {

    // 영화 이름
    private String movie;
    // 상영관
    private String theater;
    // 상영일
    private String screenDate;
    // 상영 시작 시각
    private String screenTime;
    // 가격
    private int price;

    private int getRemainingSeat;

    private int numOfTheaterSeats;

    private String discountPolicy;
}
