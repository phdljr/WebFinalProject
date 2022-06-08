package kr.ac.kumoh.backend.controller;

import kr.ac.kumoh.backend.Service.MovieService;
import kr.ac.kumoh.backend.dto.MovieDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RestController
@Slf4j
public class MovieController {

    private final MovieService movieService;

    /**
     * 예매율순으로 Top10 영화 출력
     *
     * @return 값이 -1이면, 그 영화가 상영되는 시간이 없음
     */
    @GetMapping(value = "/movies/sales")
    public Map<String, Double> getMoviesBySales() {

        return movieService.getTop10TicketSales();
    }

    // 평점순으로 Top10 영화 출력 (아직 미완성)
    @GetMapping(value = "/movies/rates")
    public List<MovieDTO> getMoviesByRates() {
        return null;
    }

    @GetMapping("/{movieName}/distribution")
    public double getGenderReservationDistribution(String movieName) {
        return movieService.getGenderReservationDistribution(movieName);
    }
}
