package kr.ac.kumoh.backend.repository;

import kr.ac.kumoh.backend.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Query("select s from Seat s, Book b, BookDetails bd, MovieSchedule ms, Theater t " +
            "where s.book.id = b.id and " +
            "bd.book.id = b.id and " +
            "bd.movieSchedule.id = ms.id and " +
            "ms.theater.id = t.id and " +
            "t.screen = :screenName and t.theaterName = :theaterName " +
            "and ms.screenTime = :screenTime")
    List<Seat> getReservedSeats(@Param("theaterName") String theaterName, @Param("screenName") String screenName, @Param("screenTime") String screenTime);
}
