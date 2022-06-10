package kr.ac.kumoh.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservedSeatDTO {

    private int numOfRows;
    private int numOfColumns;
    private int price;
    private String runtime;
    private List<String> rows = new ArrayList<>();
    private List<Integer> columns = new ArrayList<>();
}
