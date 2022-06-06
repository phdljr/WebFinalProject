package kr.ac.kumoh.backend.repository;

import kr.ac.kumoh.backend.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
