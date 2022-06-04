package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.Movie;
import kr.ac.kumoh.backend.dto.MovieDTO;

import java.util.List;


public interface MovieService {

    void addMovie(Movie movie);

    List<MovieDTO> getTop10Movies();
}
