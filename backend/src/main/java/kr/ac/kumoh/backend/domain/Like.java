package kr.ac.kumoh.backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


//@NamedEntityGraph(
//        name = "like.comment.user",
//        attributeNodes = {
//                @NamedAttributeNode(value = "comment", subgraph = "comment.user")
//        },
//        subgraphs = {
//                @NamedSubgraph(
//                        name = "comment.user",
//                        attributeNodes = {
//                                @NamedAttributeNode("user")
//                        }
//                )
//        }
//)
@NamedEntityGraph(
        name = "like.comment.movie",
        attributeNodes = {
                @NamedAttributeNode(value = "comment", subgraph = "comment.movie")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "comment.movie",
                        attributeNodes = {
                                @NamedAttributeNode("movie"),
                                @NamedAttributeNode("user")
                        }
                )
        }
)
@Entity
@Table(name = "LIKES")
@NoArgsConstructor
@ToString
@Getter
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
