package kr.ac.kumoh.backend.repository;

import kr.ac.kumoh.backend.domain.MovieSchedule;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MovieScheduleRepository extends JpaRepository<MovieSchedule, Long> {

    @EntityGraph(value = "MovieSchedule.Movie.Theater.graph", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select ms from MovieSchedule ms")
    List<MovieSchedule> getAllMovieSchedules();
}
