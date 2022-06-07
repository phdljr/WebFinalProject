package kr.ac.kumoh.backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
public class UserInfoDTO {

    // 예매 내역은 나중에 추가
    private String gender;
    private int age;
    List<UserBookDetailsDTO> reservations = new ArrayList<>();

    public UserInfoDTO(String gender, int age, List<UserBookDetailsDTO> userBookDetailsDTOS) {
        this.gender = gender;
        this.age = age;
        this.reservations = userBookDetailsDTOS;
    }
}
