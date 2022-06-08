package kr.ac.kumoh.backend.Service;


import java.util.List;
import java.util.Map;


public interface MovieService {

    Map<String, Double> getTop10TicketSales();

    double getMovieTicketSales(String movieName);

    double getGenderReservationDistribution(String movieName);

    List<Integer> getAgeReservationDistribution(String movieName);
}
