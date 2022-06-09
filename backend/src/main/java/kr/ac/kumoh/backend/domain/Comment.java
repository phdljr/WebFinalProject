package kr.ac.kumoh.backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@Table(name = "COMMENTS")
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "COMMENT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID")
    private User user;

    private String comment;

    private int numOfLike;

    private String commentDate;

    public void addLike() {
        this.numOfLike += 1;
    }

    public Comment(Movie movie, User user, String comment, String commentDate) {
        this.movie = movie;
        this.user = user;
        this.comment = comment;
        this.numOfLike = 0;
        this.commentDate = commentDate;
    }
}
