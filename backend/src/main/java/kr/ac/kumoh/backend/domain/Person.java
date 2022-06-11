package kr.ac.kumoh.backend.domain;

import lombok.Getter;

import javax.persistence.*;


@NamedEntityGraph(
        name = "person.movie",
        attributeNodes = {
                @NamedAttributeNode("movie")
        }
)
@Entity
@Getter
public class Person {

    @Id
    @GeneratedValue
    @Column(name = "PERSON_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

    private String name;

    private String occupation;
}
