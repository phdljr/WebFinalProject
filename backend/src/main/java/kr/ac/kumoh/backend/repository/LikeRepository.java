package kr.ac.kumoh.backend.repository;

import kr.ac.kumoh.backend.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query("select l from Like l join fetch l.comment where l.userId = :userId")
    List<Like> getUserLikes(@Param("userId") String userId);
}
