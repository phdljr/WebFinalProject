package kr.ac.kumoh.backend.repository;

import kr.ac.kumoh.backend.domain.Comment;
import kr.ac.kumoh.backend.domain.Like;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface LikeRepository extends JpaRepository<Like, Long> {
//
//    @EntityGraph(value = "like.comment.user", type = EntityGraph.EntityGraphType.LOAD)
//    @Query("select l from Like l where l.userId = :userId")
//    List<Like> getUserLikes(@Param("userId") String userId);

    @EntityGraph(value = "like.comment.movie", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select l from Like l where l.userId = :userId and l.comment.movie.title = :movieName")
    List<Like> getUserLikes(@Param("userId") String userId, @Param("movieName") String movieName);

    Like findByUserIdAndComment(String userId, Comment comment);

    List<Like> findAllByComment_Id(Long commentId);
}
