package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.Movie;
import kr.ac.kumoh.backend.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    List<String> getFileNames();
    void saveMovie(Movie movie);
    List<MovieDTO> loadMovieImages();
}
