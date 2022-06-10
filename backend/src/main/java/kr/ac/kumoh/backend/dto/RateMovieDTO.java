package kr.ac.kumoh.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RateMovieDTO {

    private String userId;
    private String movieName;
    private Double grade;
}
