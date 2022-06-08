package kr.ac.kumoh.backend.Service;

import java.util.Map;


public interface MovieService {

    Map<String, Double> getTop10TicketSales();

    double getMovieTicketSales(String movieName);
}
