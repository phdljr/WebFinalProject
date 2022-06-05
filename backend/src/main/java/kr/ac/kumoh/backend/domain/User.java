package kr.ac.kumoh.backend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(name = "USERS_ID")
    private Long id;

    private String userId;
    private String userPw;
    private String gender;
    private int age;

    @Builder
    public User(String userId, String userPw, String gender, int age) {
        this.userId = userId;
        this.userPw = userPw;
        this.gender = gender;
        this.age = age;
    }
}
