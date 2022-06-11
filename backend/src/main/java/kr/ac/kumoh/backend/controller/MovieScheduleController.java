package kr.ac.kumoh.backend.controller;

import kr.ac.kumoh.backend.domain.Movie;
import kr.ac.kumoh.backend.domain.Theater;
import kr.ac.kumoh.backend.dto.TheaterMovieScheduleDTO;
import kr.ac.kumoh.backend.repository.MovieScheduleRepository;
import kr.ac.kumoh.backend.domain.MovieSchedule;
import kr.ac.kumoh.backend.dto.MovieScheduleDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
public class MovieScheduleController {

    private final MovieScheduleRepository scheduleRepository;

    @GetMapping("/schedules/{theaterName}")
    public List<TheaterMovieScheduleDTO> getMovieSchedules(@PathVariable(name = "theaterName") String theaterName) {

        List<MovieSchedule> theaterMovieSchedules = scheduleRepository.getTheaterMovieSchedules(theaterName);

        List<TheaterMovieScheduleDTO> theaterMovieScheduleDTOS
                = new ArrayList<>();
        for (MovieSchedule theaterMovieSchedule : theaterMovieSchedules) {
            Movie movie = theaterMovieSchedule.getMovie();
            Theater theater = theaterMovieSchedule.getTheater();

            TheaterMovieScheduleDTO movieSchedule = new TheaterMovieScheduleDTO(
                    movie.getMediaRating(), movie.getTitle(), movie.getGenre(), movie.getRuntime(), movie.getReleaseDate(),
                    theater.getFloor(), theater.getScreen(), theater.getNumOfSeats(),
                    theaterMovieSchedule.getScreenTime(), theaterMovieSchedule.getRemainingSeat(),
                    theaterMovieSchedule.getDiscountPolicy(), theaterMovieSchedule.getDiscountRate());

            theaterMovieScheduleDTOS.add(movieSchedule);
        }
        return theaterMovieScheduleDTOS;
    }

    @GetMapping("/schedules/{theaterName}/{movieName}")
    public List<MovieScheduleDTO> getMovieSchedule(@PathVariable(name = "movieName") String movieName,
                                                   @PathVariable(name = "theaterName") String theaterName) {

        List<MovieSchedule> movieSchedules = scheduleRepository.getCertainMovieSchedules(movieName, theaterName);

        List<MovieScheduleDTO> movieScheduleDTOS = new ArrayList<>();
        movieSchedules.forEach(ms -> {
            MovieScheduleDTO movieScheduleDTO = new MovieScheduleDTO(ms.getMovie().getTitle(),
                    ms.getTheater().getScreen(),
                    ms.getScreenDate(),
                    ms.getScreenTime(),
                    ms.getPrice(),
                    ms.getRemainingSeat(),
                    ms.getTheater().getNumOfSeats(),
                    ms.getDiscountPolicy()
            );
            movieScheduleDTOS.add(movieScheduleDTO);
        });

        return movieScheduleDTOS;
    }
}
