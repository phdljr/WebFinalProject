package kr.ac.kumoh.backend.repository;

import kr.ac.kumoh.backend.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LikeRepository extends JpaRepository<Like, Long> {
}
