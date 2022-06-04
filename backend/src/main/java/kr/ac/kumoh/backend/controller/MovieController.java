package kr.ac.kumoh.backend.controller;

import kr.ac.kumoh.backend.Service.ImageService;
import kr.ac.kumoh.backend.dto.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RequiredArgsConstructor
@RestController
@Slf4j
public class MovieController {

    private final ImageService imageService;

    @GetMapping(value = "/movies")
    public List<Movie> getMovieImages() {

        List<Movie> movies = imageService.loadMovieImages();

        return movies;
    }
}
