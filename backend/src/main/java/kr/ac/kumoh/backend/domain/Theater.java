package kr.ac.kumoh.backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Getter
@NoArgsConstructor
public class Theater {

    @Id
    @GeneratedValue
    @Column(name = "THEATER_ID")
    private Long id;

    // 몇 층
    private int floor;
    // 몇 관
    private String screen;

    // 좌석 클래스
}
