package kr.ac.kumoh.backend.Service;


import kr.ac.kumoh.backend.domain.StatusOfUser;
import kr.ac.kumoh.backend.dto.DiscountMovieDTO;
import kr.ac.kumoh.backend.dto.MovieDetailInfo;
import kr.ac.kumoh.backend.dto.RateMovieDTO;
import kr.ac.kumoh.backend.dto.Top10MovieDTO;

import java.util.List;


public interface MovieService {

    MovieDetailInfo getMovieDetailInfo(String movieName);

    StatusOfUser discountMovie(DiscountMovieDTO discountMovieDTO);

    List<Top10MovieDTO> getTop10TicketSales();

    List<Top10MovieDTO> getTop10MovieGrades();

    double getMovieTicketSales(String movieName);

    double getGenderReservationDistribution(String movieName);

    List<Integer> getAgeReservationDistribution(String movieName);
}
