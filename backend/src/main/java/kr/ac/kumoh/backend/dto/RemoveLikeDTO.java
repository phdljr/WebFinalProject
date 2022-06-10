package kr.ac.kumoh.backend.dto;

import lombok.Getter;


@Getter
public class RemoveLikeDTO {
    private String commentUserId;
    private String movieName;
    private String userId;
}

