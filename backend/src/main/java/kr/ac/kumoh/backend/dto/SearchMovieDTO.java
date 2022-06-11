package kr.ac.kumoh.backend.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class SearchMovieDTO {

    private String movieName;
    private double rate;
    private double grade;
}
