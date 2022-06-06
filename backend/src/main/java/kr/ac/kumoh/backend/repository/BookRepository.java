package kr.ac.kumoh.backend.repository;

import kr.ac.kumoh.backend.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
