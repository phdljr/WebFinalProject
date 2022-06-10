package kr.ac.kumoh.backend.controller;

import kr.ac.kumoh.backend.domain.MovieSchedule;
import kr.ac.kumoh.backend.domain.Seat;
import kr.ac.kumoh.backend.dto.GetReservedSeatDTO;
import kr.ac.kumoh.backend.dto.ReservedSeatDTO;
import kr.ac.kumoh.backend.repository.MovieScheduleRepository;
import kr.ac.kumoh.backend.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class SeatController {

    private final SeatRepository seatRepository;
    private final MovieScheduleRepository movieScheduleRepository;

    @PostMapping("/reservedSeats")
    public ReservedSeatDTO getReservedSeats(@RequestBody GetReservedSeatDTO getReservedSeatDTO) {

        String theaterName = getReservedSeatDTO.getTheaterName();
        String screenName = getReservedSeatDTO.getScreenName();
        String screenTime = getReservedSeatDTO.getScreenTime();

        List<Seat> reservedSeats = seatRepository.getReservedSeats(theaterName, screenName, screenTime);
        MovieSchedule theater = movieScheduleRepository.getCertainMovieSchedule(theaterName, screenName, screenTime);

        int numOfRows = theater.getTheater().getNumOfRows();
        int numOfColumns = theater.getTheater().getNumOfColumns();
        int price = theater.getPrice();
        String runtime = theater.getMovie().getRuntime();

        ReservedSeatDTO reservedSeatDTO = new ReservedSeatDTO();
        reservedSeatDTO.setNumOfColumns(numOfColumns);
        reservedSeatDTO.setNumOfRows(numOfRows);
        reservedSeatDTO.setPrice(price);
        reservedSeatDTO.setRuntime(runtime);
        reservedSeats.forEach(seat -> {
            reservedSeatDTO.getRows().add(seat.getSeatRow());
            reservedSeatDTO.getColumns().add(seat.getSeatColumn());
        });

        return reservedSeatDTO;
    }
}
