package kr.ac.kumoh.backend.domain;

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
    @Column(name = "USER_ID")
    private Long user_id;

    private String id;
    private String pw;
    private String gender;
    private int age;

    public User(Long user_id, String pw, String gender, int age) {
        this.user_id = user_id;
        this.pw = pw;
        this.gender = gender;
        this.age = age;
    }
}
