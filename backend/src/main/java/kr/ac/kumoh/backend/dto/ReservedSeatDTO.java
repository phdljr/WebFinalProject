package kr.ac.kumoh.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservedSeatDTO {

    private List<String> rows = new ArrayList<>();
    private List<Integer> columns = new ArrayList<>();
}
