package kr.ac.kumoh.backend.repository;

import kr.ac.kumoh.backend.domain.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c join fetch c.user join fetch c.movie " +
            "where c.user.userId = :commentUserId and c.movie.title = :movieName")
    Comment findUserComment(@Param("movieName") String movieName,
                            @Param("commentUserId") String commentUserId);

    @EntityGraph(value = "Comment.User.Movie", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select c from Comment c where c.movie.title = :movieName")
    List<Comment> getMovieComments(@Param("movieName") String movieName);
}
