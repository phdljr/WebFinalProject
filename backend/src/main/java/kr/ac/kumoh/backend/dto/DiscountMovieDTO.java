package kr.ac.kumoh.backend.dto;

import lombok.Getter;


@Getter
public class DiscountMovieDTO {

    private String theaterName;
    private String screenName;
    private String screenTime;
    private String discountPolicy;
    private int discountRate;
}
