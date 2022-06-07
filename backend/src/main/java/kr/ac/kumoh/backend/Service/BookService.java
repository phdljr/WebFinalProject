package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.StatusOfUser;
import kr.ac.kumoh.backend.dto.ReserveMovieDTO;

public interface BookService {

    StatusOfUser saveUserMovieReservation(ReserveMovieDTO reserveMovieDTO);
}
