package kr.ac.kumoh.backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Entity
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "BOOK_ID")
    private Long id;

    private int NumOfPeople;
    private int price;

    @ManyToMany
    private List<User> users;

    @ElementCollection(fetch = FetchType.LAZY)
    @MapKeyColumn(name = "ROWS")
    @Column(name = "COLUMNS")
    @CollectionTable(name = "SEATS", joinColumns = @JoinColumn(name = "BOOK_ID"))
    private Map<String, Integer> seats = new HashMap<>();
}
