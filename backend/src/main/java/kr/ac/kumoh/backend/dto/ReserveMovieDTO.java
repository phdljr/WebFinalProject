package kr.ac.kumoh.backend.dto;

import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReserveMovieDTO {

    private String userId;
    private int numOfPeople;
    private int price;
    private List<String> rows;
    private List<Integer> columns;
}
