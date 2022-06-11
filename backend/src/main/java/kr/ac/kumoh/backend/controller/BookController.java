package kr.ac.kumoh.backend.controller;

import kr.ac.kumoh.backend.Service.BookService;
import kr.ac.kumoh.backend.domain.ResponseStatus;
import kr.ac.kumoh.backend.dto.ReserveMovieDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/reserve")
    public ResponseStatus reserveMovie(@RequestBody ReserveMovieDTO reserveMovieDTO) {
//        log.info("예약 정보: " + reserveMovieDTO.getUserId());
//        log.info("예약 정보: " + reserveMovieDTO.getNumOfPeople());
//        log.info("예약 정보: " + reserveMovieDTO.getPrice());
//        log.info("예약 정보: " + reserveMovieDTO.getRows());
//        log.info("예약 정보: " + reserveMovieDTO.getColumns());
        return bookService.saveUserMovieReservation(reserveMovieDTO);
    }
}
