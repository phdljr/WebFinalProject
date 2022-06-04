package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.dto.Movie;

import java.util.List;

public interface ImageService {

    List<Movie> loadMovieImages();
}
