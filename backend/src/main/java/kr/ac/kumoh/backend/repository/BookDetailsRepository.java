package kr.ac.kumoh.backend.repository;

import kr.ac.kumoh.backend.domain.BookDetails;
import kr.ac.kumoh.backend.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookDetailsRepository extends JpaRepository<BookDetails, Long> {

    @EntityGraph(value = "User.BookingDetails", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select bd from BookDetails bd " +
            "where bd.user.userId = :userId")
    List<BookDetails> getUserMovieSchedule(@Param("userId") String userId);

    @EntityGraph(value = "Man.BookingDetails.Movie", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select bd from BookDetails bd " +
            "where bd.movieSchedule.movie.title = :movieName")
    List<BookDetails> getNumOfMovieTickets(@Param("movieName") String movieName);

    @EntityGraph(value = "Man.BookingDetails.Movie", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select bd from BookDetails bd " +
            "where bd.movieSchedule.movie.title = :movieName and bd.user.gender = '남자'")
    List<BookDetails> getNumOfManMovieTickets(@Param("movieName") String movieName);

    @EntityGraph(value = "Man.BookingDetails.Movie", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select bd from BookDetails bd " +
            "where bd.movieSchedule.movie.title = :movieName")
    List<BookDetails> getNumOfAgeMovieTickets(@Param("movieName") String movieName);
}
