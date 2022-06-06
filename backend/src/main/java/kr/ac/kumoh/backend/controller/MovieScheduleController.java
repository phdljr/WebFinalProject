package kr.ac.kumoh.backend.controller;

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

    @GetMapping("/schedules/{movieName}")
    public List<MovieScheduleDTO> getMovieSchedule(@PathVariable(name = "movieName") String movieName) {
        List<MovieSchedule> movieSchedules = scheduleRepository.getAllMovieSchedules(movieName);

        List<MovieScheduleDTO> movieScheduleDTOS = new ArrayList<>();
        movieSchedules.forEach(ms -> {
            MovieScheduleDTO movieScheduleDTO = new MovieScheduleDTO(ms.getMovie().getTitle(),
                    ms.getTheater().getScreen(),
                    ms.getScreenDate(),
                    ms.getScreenTime(),
                    ms.getPrice()
            );
            movieScheduleDTOS.add(movieScheduleDTO);
        });

        return movieScheduleDTOS;
    }
}
