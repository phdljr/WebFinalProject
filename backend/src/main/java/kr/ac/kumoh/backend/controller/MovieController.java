package kr.ac.kumoh.backend.controller;

import kr.ac.kumoh.backend.Service.MovieService;
import kr.ac.kumoh.backend.domain.Movie;
import kr.ac.kumoh.backend.domain.Person;
import kr.ac.kumoh.backend.domain.ResponseStatus;
import kr.ac.kumoh.backend.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
@Slf4j
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/search/{param}")
    public List<SearchMovieDTO> searchMovie(@PathVariable("param") String param) {

        return movieService.searchMovie(param);
    }

    @GetMapping("/movie/{movieName}")
    public MovieDetailInfo getMovieDetailInfo(@PathVariable("movieName") String movieName) {
        return movieService.getMovieDetailInfo(movieName);
    }

    @PostMapping("/movie/discount")
    public ResponseStatus discountMovie(@RequestBody DiscountMovieDTO discountMovieDTO) {
        return movieService.discountMovie(discountMovieDTO);
    }

    /**
     * 예매율순으로 Top10 영화 출력
     *
     * @return 값이 -1이면, 그 영화가 상영되는 시간이 없음
     */
    @GetMapping(value = "/movies/sales")
    public List<Top10MovieDTO> getMoviesBySales() {
        return movieService.getTop10TicketSales();
    }

    /**
     * 평점순으로 Top10 영화 출력
     *
     * @return 값이 0이면, 그 영화에 대한 평점이 아직 없음
     */
    @GetMapping(value = "/movies/rates")
    public List<Top10MovieDTO> getMoviesByGrades() {
        return movieService.getTop10MovieGrades();
    }

    @GetMapping("/movie/{movieName}/age")
    public List<Integer> getAgeReservationDistribution(@PathVariable("movieName") String movieName) {
        return movieService.getAgeReservationDistribution(movieName);
    }

    @GetMapping("/movie/{movieName}/gender")
    public double getGenderReservationDistribution(@PathVariable("movieName") String movieName) {
        return movieService.getGenderReservationDistribution(movieName);
    }
}
