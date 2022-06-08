package kr.ac.kumoh.backend.Service;


import kr.ac.kumoh.backend.dto.Top10MovieDTO;

import java.util.List;
import java.util.Map;


public interface MovieService {

    List<Top10MovieDTO> getTop10TicketSales();

    double getMovieTicketSales(String movieName);

    double getGenderReservationDistribution(String movieName);

    List<Integer> getAgeReservationDistribution(String movieName);
}
