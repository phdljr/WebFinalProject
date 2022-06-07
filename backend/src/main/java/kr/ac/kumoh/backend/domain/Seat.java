package kr.ac.kumoh.backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "SEATS")
public class Seat {

    @Id
    @GeneratedValue
    @Column(name = "SEAT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    private String seatRow;
    private int seatColumn;

    public Seat(String row, int column) {
        this.seatRow = row;
        this.seatColumn = column;
    }
}
