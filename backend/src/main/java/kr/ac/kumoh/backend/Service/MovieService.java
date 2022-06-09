package kr.ac.kumoh.backend.Service;


import kr.ac.kumoh.backend.dto.MovieDetailInfo;
import kr.ac.kumoh.backend.dto.Top10MovieDTO;

import java.util.List;


public interface MovieService {

    MovieDetailInfo getMovieDetailInfo(String movieName);
    List<Top10MovieDTO> getTop10TicketSales();

    double getMovieTicketSales(String movieName);

    double getGenderReservationDistribution(String movieName);

    List<Integer> getAgeReservationDistribution(String movieName);
}
