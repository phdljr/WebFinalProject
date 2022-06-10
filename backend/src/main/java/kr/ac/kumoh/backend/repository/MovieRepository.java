package kr.ac.kumoh.backend.repository;

import kr.ac.kumoh.backend.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("select m from Movie m")
    List<String> findAllMovieName();

    Movie findByTitle(String title);
}
