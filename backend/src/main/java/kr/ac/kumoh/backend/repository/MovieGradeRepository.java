package kr.ac.kumoh.backend.repository;

import kr.ac.kumoh.backend.domain.MovieGrade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieGradeRepository extends JpaRepository<MovieGrade, Long> {

    Optional<MovieGrade> findByUserId(String userId);
}
