package kr.ac.kumoh.backend.repository;

import kr.ac.kumoh.backend.domain.BookDetails;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookDetailsRepository extends JpaRepository<BookDetails, Long> {

    @EntityGraph(value = "User.BookingDetails", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select bd from BookDetails bd where bd.user.userId = :userId")
    List<BookDetails> getUserMovieSchedule(@Param("userId") String userId);
}
