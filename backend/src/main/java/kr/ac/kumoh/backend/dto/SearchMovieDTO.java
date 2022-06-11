package kr.ac.kumoh.backend.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class SearchMovieDTO {

    private String title;
    private double rate;
    private double grade;
}
