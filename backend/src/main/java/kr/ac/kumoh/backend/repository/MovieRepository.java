package kr.ac.kumoh.backend.repository;

import kr.ac.kumoh.backend.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MovieRepository extends JpaRepository<Movie, Long> {

    Movie findByTitle(String title);

    @Query("select m from Movie m where m.title like CONCAT('%',:movieName,'%')")
    List<Movie> searchMovieByTitle(@Param("movieName") String movieName);
}
