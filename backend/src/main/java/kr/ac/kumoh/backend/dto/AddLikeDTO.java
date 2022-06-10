package kr.ac.kumoh.backend.dto;

import lombok.Getter;


@Getter
public class AddLikeDTO {

    private String commentUserId;
    private String movieName;
    private String userId;
}
