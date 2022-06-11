package kr.ac.kumoh.backend.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class MovieCommentDTO {

    private String userId;
    private String commentDate;
    private String comment;
    private int like;
    private double grade;
}
