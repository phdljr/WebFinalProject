package kr.ac.kumoh.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Setter @Getter
public class UserBookDetailsDTO {

    private String movieName;
    private int floor;
    private String theaterName;
    private String screen;
    private String screenDate;
    private String screenTime;
    private int price;
    private int numOfPeople;
    private List<String> rows = new ArrayList<>();
    private List<Integer> columns = new ArrayList<>();
}
