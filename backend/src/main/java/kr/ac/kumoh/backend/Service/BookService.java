package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.ResponseStatus;
import kr.ac.kumoh.backend.dto.ReserveMovieDTO;

public interface BookService {

    ResponseStatus saveUserMovieReservation(ReserveMovieDTO reserveMovieDTO);
}
