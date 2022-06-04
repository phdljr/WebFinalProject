package kr.ac.kumoh.backend.repository;

import kr.ac.kumoh.backend.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAllByOrderByRankAsc();
}
