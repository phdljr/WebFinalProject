package kr.ac.kumoh.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@Builder
@AllArgsConstructor
public class MovieDetailInfo {

    private String director;
    private List<String> actors;
    private double ticketSales;
    private String genre;
    private String releaseDate;
    private String mediaRate;
    private String runtime;
    private double genderDistribution;
    private List<Integer> ageDistribution;
    private List<MovieCommentDTO> comments;
    private double avgOfGrade;
}
