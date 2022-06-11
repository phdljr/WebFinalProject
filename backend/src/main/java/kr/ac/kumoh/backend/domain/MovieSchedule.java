package kr.ac.kumoh.backend.domain;

import lombok.Getter;

import javax.persistence.*;


@NamedEntityGraph(
        name = "MovieSchedule.Movie.Theater.graph",
        attributeNodes = {
                @NamedAttributeNode("movie"),
                @NamedAttributeNode("theater")
        }
)
@Entity
@Getter
@Table(name = "MOVIE_SCHEDULE")
public class MovieSchedule {

    @Id
    @GeneratedValue
    @Column(name = "MS_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "THEATER_ID")
    private Theater theater;

    // 상영일
    private String screenDate;

    // 상영 시작 시각
    private String screenTime;

    // 가격 (추후에 할인 정책 세우기)
    private int price;

    private int remainingSeat;

    private String discountPolicy;

    private int discountRate;

    public void subRemainingSeat(int numOfPeople) {
        this.remainingSeat -= numOfPeople;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDiscountPolicyAndRate(String discountPolicy, int discountRate) {
        this.discountPolicy = discountPolicy;
        this.discountRate = discountRate;
    }
}
