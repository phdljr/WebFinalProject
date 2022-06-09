package kr.ac.kumoh.backend.repository;

import kr.ac.kumoh.backend.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment findByCommentAndCommentDate(String comment, String commentDate);
}
