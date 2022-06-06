package kr.ac.kumoh.backend.domain;

import javax.persistence.*;


@Entity
@Table(name = "BOOKING_DETAILS")
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
}
