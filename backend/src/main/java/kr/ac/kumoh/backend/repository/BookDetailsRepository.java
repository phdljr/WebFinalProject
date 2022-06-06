package kr.ac.kumoh.backend.repository;

import kr.ac.kumoh.backend.domain.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDetailsRepository extends JpaRepository<BookDetails, Long> {
}
