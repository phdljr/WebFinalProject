package kr.ac.kumoh.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
@Builder
public class Movie {

    private String MovieName;
    private String rank;
    private byte[] image;
}
