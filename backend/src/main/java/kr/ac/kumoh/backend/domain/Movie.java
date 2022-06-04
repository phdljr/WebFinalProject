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
public class Movie {

    @Id
    @GeneratedValue
    @Column(name = "MOVIE_ID")
    private Long id;

    private String movieName;
    private int rank;
    private String imagePath;
}
