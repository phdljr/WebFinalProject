package kr.ac.kumoh.backend.dto;

import lombok.Getter;


@Getter
public class DeleteCommentDTO {

    private String comment;
    private String commentDate;
    private String userId;
}
