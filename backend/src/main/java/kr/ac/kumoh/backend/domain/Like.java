package kr.ac.kumoh.backend.domain;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name = "LIKES")
@NoArgsConstructor
@ToString
public class Like {

    @Id
    @GeneratedValue
    @Column(name = "LIKE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMENT_ID")
    private Comment comment;

    @Column(name = "USER_NAME")
    private String userId;


    public Like(Comment comment, String user) {
        this.comment = comment;
        this.userId = user;
    }
}
