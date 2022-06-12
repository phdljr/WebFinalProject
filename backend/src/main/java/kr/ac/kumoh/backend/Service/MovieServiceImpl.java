package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.Service.discount.DiscountServiceImpl;
import kr.ac.kumoh.backend.domain.*;
import kr.ac.kumoh.backend.dto.*;
import kr.ac.kumoh.backend.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static java.util.Objects.isNull;
import static kr.ac.kumoh.backend.domain.ResponseStatus.*;


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final CommentService commentService;
    private final MovieRepository movieRepository;
    private final PersonRepository personRepository;
    private final DiscountServiceImpl discountService;
    private final BookDetailsRepository bookDetailsRepository;
    private final MovieScheduleRepository movieScheduleRepository;

    @Override
    public List<SearchMovieDTO> searchMovie(String param) {

        List<Movie> findMovie = movieRepository.searchMovieByTitle(param);
        List<Person> findPerson = personRepository.searchPersonByName(param);

        Set<Movie> result = new HashSet<>();
        if (!findMovie.isEmpty() || !findPerson.isEmpty()) {
            result.addAll(findMovie);
            findPerson.forEach(m -> result.add(m.getMovie()));
        }

        List<SearchMovieDTO> searchMovieDTOS = new ArrayList<>();
        result.forEach(r -> {
            SearchMovieDTO searchMovieDTO = SearchMovieDTO.builder()
                    .title(r.getTitle())
                    .rate(getMovieTicketSales(r.getTitle()))
                    .grade(r.getAvgOfGrade()).build();
            searchMovieDTOS.add(searchMovieDTO);
        });

        return searchMovieDTOS;
    }

    @Override
    public MovieDetailInfo getMovieDetailInfo(String movieName) {

        List<Person> moviePersons = personRepository.getAllPerson(movieName);                   // 배우 & 감독
        Movie movie = movieRepository.findByTitle(movieName);                                   // 영화 정보
        double ticketSales = getMovieTicketSales(movieName);                                    // 예매율
        double genderReservationDistribution = getGenderReservationDistribution(movieName);     // 성별 예매 분포
        List<Integer> ageReservationDistribution = getAgeReservationDistribution(movieName);    // 나이별 예매 분포
        List<MovieCommentDTO> movieComments = commentService.getMovieComments(movie);       // 댓글
        double avgOfGrade = movie.getAvgOfGrade();                                              // 평균 평점

        // 배우 & 감독 데이터
        String director = null;
        List<String> person = new ArrayList<>();
        for (Person moviePerson : moviePersons) {
            if (moviePerson.getOccupation().equals("감독"))
                director = moviePerson.getName();
            else
                person.add(moviePerson.getName());
        }

        // 나머지 데이터
        // 예매율 / 장르 / 개봉일 / 등급 / 런타임 / 성별 예매 분포 / 나이별 예매 분포 / 댓글 / 평균 평점
        MovieDetailInfo movieDetailInfo = MovieDetailInfo.builder()
                .director(director)
                .actors(person)
                .ticketSales(ticketSales)
                .genre(movie.getGenre())
                .releaseDate(movie.getReleaseDate())
                .mediaRate(movie.getMediaRating())
                .runtime(movie.getRuntime())
                .genderDistribution(genderReservationDistribution)
                .ageDistribution(ageReservationDistribution)
                .comments(movieComments)
                .avgOfGrade(avgOfGrade).build();

        return movieDetailInfo;
    }

    @Override
    public ResponseStatus discountMovie(DiscountMovieDTO discountMovieDTO) {

        ResponseStatus status = Success;

        MovieSchedule findMovieSchedule = movieScheduleRepository.getCertainMovieSchedule(
                discountMovieDTO.getTheaterName(), discountMovieDTO.getScreenName(), discountMovieDTO.getScreenTime());
        if (isNull(findMovieSchedule))
            return Fail;

        String discountPolicy = discountMovieDTO.getDiscountPolicy();
        findMovieSchedule.setPrice(12000);
        if (!discountPolicy.equals("none")) {

            int discountRate = discountMovieDTO.getDiscountRate();
            int price = findMovieSchedule.getPrice();

            int discountedPrice = discountService.getDiscountedPrice(price, discountPolicy, discountRate);

            if (discountedPrice == -1)
                status = PriceIsNegative;
            else if (discountedPrice == -2)
                status = WrongDiscountPolicy;
            else {
                findMovieSchedule.setPrice(discountedPrice);
                findMovieSchedule.setDiscountPolicyAndRate(discountPolicy, discountRate);
            }
        } else {
            findMovieSchedule.setDiscountPolicyAndRate("none", 0);
        }

        return status;
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

        int underTeens = 0;
        int teenage = 0;
        int twenties = 0;
        int thirties = 0;
        int forties = 0;
        int fifties = 0;
        int etc = 0;
        double total = numOfAgeMovieTickets.size();
        for (BookDetails numOfAgeMovieTicket : numOfAgeMovieTickets) {
            int age = numOfAgeMovieTicket.getUser().getAge();

            switch (age / 10) {
                case 0:
                    underTeens += 1;
                    break;
                case 1:
                    teenage += 1;
                    break;
                case 2:
                    twenties += 1;
                    break;
                case 3:
                    thirties += 1;
                    break;
                case 4:
                    forties += 1;
                    break;
                case 5:
                    fifties += 1;
                    break;
                default:
                    etc += 1;
            }
        }

        List<Integer> rates = new ArrayList<>();
        rates.add(underTeens);
        rates.add(teenage);
        rates.add(twenties);
        rates.add(thirties);
        rates.add(forties);
        rates.add(fifties);
        rates.add(etc);

        return rates;
    }

    @Override
    public List<Top10MovieDTO> getTop10TicketSales() {

        Map<String, Double> moviesSaleTickets = new HashMap<>();
        List<Movie> movieList = movieRepository.findAll();

        for (Movie movie : movieList) {
            String movieTitle = movie.getTitle();
            double ticketSales = getMovieTicketSales(movieTitle);
            moviesSaleTickets.put(movieTitle, ticketSales);
        }

        List<Top10MovieDTO> top10MovieDTOS = new ArrayList<>();
        moviesSaleTickets.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue(Comparator.reverseOrder()).thenComparing(Map.Entry.comparingByKey()))
                .forEachOrdered(x -> {
                    String movieName = x.getKey();
                    Movie findMovie = movieRepository.findByTitle(movieName);
                    Top10MovieDTO top10MovieDTO = Top10MovieDTO.builder()
                            .title(movieName)
                            .mediaRating(findMovie.getMediaRating())
                            .rate(x.getValue())
                            .grade(findMovie.getAvgOfGrade())
                            .build();

                    top10MovieDTOS.add(top10MovieDTO);
                });
        return top10MovieDTOS;
    }

    public List<Top10MovieDTO> getTop10MovieGrades() {

        List<Movie> movies = movieRepository.findAll();

        List<Top10MovieDTO> top10MovieDTOS = new ArrayList<>();
        for (Movie movie : movies) {
            Top10MovieDTO top10MovieDTO = Top10MovieDTO.builder()
                    .title(movie.getTitle())
                    .mediaRating(movie.getMediaRating())
                    .grade(movie.getAvgOfGrade())
                    .rate(getMovieTicketSales(movie.getTitle())).build();
            top10MovieDTOS.add(top10MovieDTO);
        }

        Collections.sort(top10MovieDTOS);

        return top10MovieDTOS;
    }

    @Override
    public double getMovieTicketSales(String movieName) {

        int totalNumOfReservedSeat = 0;
        int totalNumOfTotalSeat = 0;
        double ticketSales;

        List<MovieSchedule> movieSchedules = movieScheduleRepository.getAllTheaterCertainMovieSchedules(movieName);
        if (movieSchedules.size() > 0) {
            for (MovieSchedule movieSchedule : movieSchedules) {
                int numOfSeats = movieSchedule.getTheater().getNumOfSeats();
                int remainingSeat = movieSchedule.getRemainingSeat();
                totalNumOfReservedSeat += (numOfSeats - remainingSeat);     // 예약된 좌석수 계산
                totalNumOfTotalSeat += numOfSeats;
            }

            ticketSales = (double) totalNumOfReservedSeat / totalNumOfTotalSeat;
            ticketSales = Math.round(ticketSales * 10000) / 10000.0;
        } else {
            ticketSales = -1;
        }

        return ticketSales;
    }
}
