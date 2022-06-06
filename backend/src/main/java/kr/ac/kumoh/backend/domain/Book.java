package kr.ac.kumoh.backend.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "BOOK_ID")
    private Long id;

    private int numOfPeople;
    private int price;

    public Book(int numOfPeople, int price) {
        this.numOfPeople = numOfPeople;
        this.price = price;
    }

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Seat> seats = new ArrayList<>();

    public void addSeat(Seat seat) {
        seats.add(seat);
        seat.setBook(this);
    }
}
