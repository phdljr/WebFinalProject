package kr.ac.kumoh.backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "GRADES")
@Getter
@NoArgsConstructor
public class MovieGrade {

    @Id
    @GeneratedValue
    @Column(name = "GRADE_ID")
    private Long id;

    @Column(name = "USER_NAME")
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

    private double grade;

    public MovieGrade(String userId, Movie movie, double grade) {
        this.userId = userId;
        this.movie = movie;
        this.grade = grade;
    }
}
