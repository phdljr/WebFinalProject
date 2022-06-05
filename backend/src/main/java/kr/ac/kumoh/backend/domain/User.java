package kr.ac.kumoh.backend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(name = "USERS_ID")
    private Long id;

//    @ManyToMany
//    @JoinTable(
//            name = "USER_BOOK",
//            joinColumns = @JoinColumn(name = "USER_ID"),
//            inverseJoinColumns = @JoinColumn(name = "BOOK_ID")
//    )
//    private List<Book> book;

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
