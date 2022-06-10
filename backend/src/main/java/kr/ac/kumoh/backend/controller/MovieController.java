package kr.ac.kumoh.backend.controller;

import kr.ac.kumoh.backend.Service.MovieService;
import kr.ac.kumoh.backend.domain.StatusOfUser;
import kr.ac.kumoh.backend.dto.MovieDTO;
import kr.ac.kumoh.backend.dto.MovieDetailInfo;
import kr.ac.kumoh.backend.dto.RateMovieDTO;
import kr.ac.kumoh.backend.dto.Top10MovieDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@Slf4j
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/movie/{movieName}")
    public MovieDetailInfo getMovieDetailInfo(@PathVariable String movieName) {
        return movieService.getMovieDetailInfo(movieName);
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

    // 평점순으로 Top10 영화 출력 (아직 미완성)
    @GetMapping(value = "/movies/rates")
    public List<MovieDTO> getMoviesByRates() {
        return null;
    }

    @PostMapping(value = "/movie/rate")
    public StatusOfUser rateMovie(@RequestBody RateMovieDTO rateMovieDTO) {
        return movieService.giveGrade(rateMovieDTO);
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
