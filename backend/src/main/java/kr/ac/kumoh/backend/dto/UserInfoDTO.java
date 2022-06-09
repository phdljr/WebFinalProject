package kr.ac.kumoh.backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoDTO {

    // 예매 내역은 나중에 추가
    private String gender;
    private int age;

    @Builder
    public UserInfoDTO(int age, String gender) {
        this.age = age;
        this.gender = gender;
    }
}
