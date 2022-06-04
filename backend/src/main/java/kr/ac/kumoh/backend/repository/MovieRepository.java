package kr.ac.kumoh.backend.repository;

import kr.ac.kumoh.backend.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieRepository extends JpaRepository<Movie, Long> {
}
