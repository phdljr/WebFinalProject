package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.dto.ReserveMovieDTO;

public interface BookService {

    void saveUserMovieReservation(ReserveMovieDTO reserveMovieDTO);
}
