package kr.ac.kumoh.backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MovieCommentDTO {

    private String userId;
    private String commentDate;
    private String comment;
    private int like;

    @Builder
    public MovieCommentDTO(String userName, String commentDate, String comment, int like) {
        this.userId = userName;
        this.commentDate = commentDate;
        this.comment = comment;
        this.like = like;
    }
}