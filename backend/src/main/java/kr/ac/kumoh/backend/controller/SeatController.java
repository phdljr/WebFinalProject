package kr.ac.kumoh.backend.controller;

import kr.ac.kumoh.backend.domain.Seat;
import kr.ac.kumoh.backend.dto.GetReservedSeatDTO;
import kr.ac.kumoh.backend.dto.ReservedSeatDTO;
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

    @PostMapping("/reservedSeats")
    public ReservedSeatDTO getReservedSeats(@RequestBody GetReservedSeatDTO getReservedSeatDTO) {

        String screenName = getReservedSeatDTO.getScreenName();
        String screenTime = getReservedSeatDTO.getScreenTime();

        List<Seat> reservedSeats = seatRepository.test(screenName, screenTime);

        ReservedSeatDTO reservedSeatDTO = new ReservedSeatDTO();
        reservedSeats.forEach(seat -> {
            reservedSeatDTO.getRows().add(seat.getSeatRow());
            reservedSeatDTO.getColumns().add(seat.getSeatColumn());
        });

        return reservedSeatDTO;
    }
}
