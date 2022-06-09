package kr.ac.kumoh.backend.repository;

import kr.ac.kumoh.backend.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Long> {

}
