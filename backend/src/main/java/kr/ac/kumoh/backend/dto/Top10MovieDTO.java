package kr.ac.kumoh.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class Top10MovieDTO {

    private String title;
    private Double rate;
    private int grade;
}
