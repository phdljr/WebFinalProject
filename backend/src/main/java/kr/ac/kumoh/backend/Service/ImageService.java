package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.dto.MovieDTO;

import java.util.List;

public interface ImageService {
    List<MovieDTO> loadMovieImages();
}
