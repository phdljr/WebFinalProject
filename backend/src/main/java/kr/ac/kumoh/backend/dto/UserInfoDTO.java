package kr.ac.kumoh.backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class UserInfoDTO {

    // 예매 내역은 나중에 추가
    private String gender;
    private int age;

    public UserInfoDTO(String gender, int age) {
        this.gender = gender;
        this.age = age;
    }
}
