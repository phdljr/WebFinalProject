package kr.ac.kumoh.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class GetReservedSeatDTO {

    private String theaterName;
    private String screenName;
    private String screenTime;
}
