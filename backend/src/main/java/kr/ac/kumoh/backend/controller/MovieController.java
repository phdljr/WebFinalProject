package kr.ac.kumoh.backend.controller;

import kr.ac.kumoh.backend.Service.MovieService;
import kr.ac.kumoh.backend.dto.MovieDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
@Slf4j
public class MovieController {

    private final MovieService movieService;

    // 예매율순으로 Top10 영화 출력 (아직 미완성)
    @GetMapping(value = "/movies/sales")
    public List<MovieDTO> getMoviesBySales() {

        return movieService.getTop10Movies();
    }

    // 평점순으로 Top10 영화 출력 (아직 미완성)
    @GetMapping(value = "/movies/rates")
    public List<MovieDTO> getMoviesByRates() {

        return movieService.getTop10Movies();
    }
}
