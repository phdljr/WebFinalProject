package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.MovieSchedule;
import kr.ac.kumoh.backend.repository.MovieRepository;
import kr.ac.kumoh.backend.repository.MovieScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieScheduleRepository movieScheduleRepository;
    private final MovieRepository movieRepository;

    @Override
    public Map<String, Double> getTop10TicketSales() {

        Map<String, Double> moviesSaleTickets = new HashMap<>();
        List<String> allMovieName = movieRepository.findAllMovieName();

        for (String movieName : allMovieName) {
            double ticketSales = getMovieTicketSales(movieName);
            moviesSaleTickets.put(movieName, ticketSales);
        }

        Map<String, Double> sortMoviesSaleTicketsByDesc = new LinkedHashMap<>();
        moviesSaleTickets.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue(Comparator.reverseOrder()).thenComparing(Map.Entry.comparingByKey()))
                .forEachOrdered(x -> sortMoviesSaleTicketsByDesc.put(x.getKey(), x.getValue()));

        return sortMoviesSaleTicketsByDesc;
    }

    @Override
    public double getMovieTicketSales(String movieName) {

        int totalNumOfReservedSeat = 0;
        int totalNumOfTotalSeat = 0;
        double ticketSales;

        List<MovieSchedule> movieSchedules = movieScheduleRepository.getAllMovieSchedules(movieName);
        if (movieSchedules.size() > 0) {
            for (MovieSchedule movieSchedule : movieSchedules) {
                int numOfSeats = movieSchedule.getTheater().getNumOfSeats();
                int remainingSeat = movieSchedule.getRemainingSeat();
                totalNumOfReservedSeat += (numOfSeats - remainingSeat);     // 예약된 좌석수 계산
                totalNumOfTotalSeat += numOfSeats;
            }

            ticketSales = (double) totalNumOfReservedSeat / totalNumOfTotalSeat;
            ticketSales = Math.round(ticketSales * 100) / 100.0;
        } else {
            ticketSales = -1;
        }

        return ticketSales;
    }
}
