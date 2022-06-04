package kr.ac.kumoh.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
@Builder
public class MovieDTO {

    private String MovieName;
    private int rank;
    private byte[] image;
}
