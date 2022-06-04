package kr.ac.kumoh.backend.repository;

import kr.ac.kumoh.backend.dto.MovieDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends JpaRepository<MovieDTO, Long> {
}
