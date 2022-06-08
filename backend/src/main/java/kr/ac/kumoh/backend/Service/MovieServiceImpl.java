package kr.ac.kumoh.backend.Service;

import io.swagger.models.auth.In;
import kr.ac.kumoh.backend.domain.BookDetails;
import kr.ac.kumoh.backend.domain.MovieSchedule;
import kr.ac.kumoh.backend.domain.User;
import kr.ac.kumoh.backend.dto.Top10MovieDTO;
import kr.ac.kumoh.backend.repository.BookDetailsRepository;
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

    private final MovieRepository movieRepository;
    private final MovieScheduleRepository movieScheduleRepository;
    private final BookDetailsRepository bookDetailsRepository;

    @Override
    public List<Top10MovieDTO> getTop10TicketSales() {

        Map<String, Double> moviesSaleTickets = new HashMap<>();
        List<String> allMovieName = movieRepository.findAllMovieName();

        for (String movieName : allMovieName) {
            double ticketSales = getMovieTicketSales(movieName);
            moviesSaleTickets.put(movieName, ticketSales);
        }

        List<Top10MovieDTO> top10MovieDTOS = new ArrayList<>();
        Map<String, Double> sortMoviesSaleTicketsByDesc = new LinkedHashMap<>();
        moviesSaleTickets.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue(Comparator.reverseOrder()).thenComparing(Map.Entry.comparingByKey()))
                .forEachOrdered(x -> {
                    Top10MovieDTO top10MovieDTO = Top10MovieDTO.builder()
                            .title(x.getKey())
                            .grade(12)
                            .rate(x.getValue()).build();

                    top10MovieDTOS.add(top10MovieDTO);
                });

        return top10MovieDTOS;
    }

    @Override
    public double getGenderReservationDistribution(String movieName) {
        List<BookDetails> numOfMovieTickets = bookDetailsRepository.getNumOfMovieTickets(movieName);
        List<BookDetails> numOfManMovieTickets = bookDetailsRepository.getNumOfManMovieTickets(movieName);
        return (double) numOfManMovieTickets.size() / numOfMovieTickets.size();
    }

    @Override
    public List<Integer> getAgeReservationDistribution(String movieName) {

        List<BookDetails> numOfAgeMovieTickets = bookDetailsRepository.getNumOfAgeMovieTickets(movieName);

        int teenage = 0; int twenties = 0; int thirties = 0; int forties = 0; int fifties = 0; int etc = 0;
        double total = numOfAgeMovieTickets.size();
        System.out.println("total = " + total);
        for (BookDetails numOfAgeMovieTicket : numOfAgeMovieTickets) {
            int age = numOfAgeMovieTicket.getUser().getAge();

            switch (age/10) {
                case 1:
                    teenage += 1; break;
                case 2:
                    twenties += 1; break;
                case 3:
                    thirties += 1; break;
                case 4:
                    forties += 1; break;
                case 5:
                    fifties += 1; break;
                default:
                    etc += 1;
            }
        }

        List<Integer> rates = new ArrayList<>();
        rates.add(teenage);
        rates.add(twenties);
        rates.add(thirties);
        rates.add(forties);
        rates.add(fifties);
        rates.add(etc);

        return rates;
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
