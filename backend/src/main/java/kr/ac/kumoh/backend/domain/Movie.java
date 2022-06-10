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

    private String title;
    // 영상물 등급
    private String mediaRating;
    private String releaseDate;
    private String runtime;
    private String genre;

    // 평점
    private double avgOfGrade;
    private int numOfGrades;

    public void calcGrade(double grade) {
        this.numOfGrades += 1;
        double avg = (avgOfGrade * (numOfGrades - 1) + grade) / numOfGrades;
        this.avgOfGrade = Math.round(avg * 100) / 100.0;
    }

    public Movie(String title, String mediaRating, String releaseDate, String runtime) {
        this.title = title;
        this.mediaRating = mediaRating;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
    }
}
