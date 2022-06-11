package kr.ac.kumoh.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TheaterMovieScheduleDTO {

    private String mediaRating;
    private String movieName;
    private String genre;
    private String runtime;
    private String releaseDate;
    private int theaterFloor;
    private String theaterScreen;
    private int numOfSeats;
    private String screenTime;
    private int remainingNumOfSeats;
    private String discountPolicy;
    private int discountRate;
}
