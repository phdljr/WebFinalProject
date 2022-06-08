package kr.ac.kumoh.backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@NamedEntityGraph(
        name = "Man.BookingDetails.Movie",
        attributeNodes = {
                @NamedAttributeNode("user"),
                @NamedAttributeNode(value = "movieSchedule", subgraph = "get.Movie")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "get.Movie",
                        attributeNodes = {
                                @NamedAttributeNode("movie")
                        }
                )
        }
)
@NamedEntityGraph(
        name = "User.BookingDetails",
        attributeNodes = {
                @NamedAttributeNode("user"),
                @NamedAttributeNode(value = "movieSchedule", subgraph = "get.Theater.Movie"),
                @NamedAttributeNode(value = "book", subgraph = "get.SeatInfo")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "get.SeatInfo",
                        attributeNodes = {
                                @NamedAttributeNode("seats")
                        }
                ),
                @NamedSubgraph(
                        name = "get.Theater.Movie",
                        attributeNodes = {
                                @NamedAttributeNode("theater"),
                                @NamedAttributeNode("movie")
                        }
                )
        }
)
@Entity
@Table(name = "BOOKING_DETAILS")
@Setter
@Getter
@NoArgsConstructor
public class BookDetails {

    @Id
    @GeneratedValue
    @Column(name = "BD_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MS_ID")
    private MovieSchedule movieSchedule;
}
