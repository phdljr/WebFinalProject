package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.Movie;
import kr.ac.kumoh.backend.dto.MovieDTO;
import kr.ac.kumoh.backend.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final String imagePath = ".//backend/src//main//resources//static//movies";
    private final MovieRepository movieRepository;

    @PostConstruct
    void Init() {
        Movie movie;
        List<String> fileNames = getFileNames();
        for (String fileName : fileNames) {
            String path = imagePath + "//" + fileName;
            movie = new Movie(fileName, 1, path);
            movieRepository.save(movie);
        }
    }

    @Override
    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public List<MovieDTO> loadMovieImages() {

        List<String> fileNames = getFileNames();
        List<MovieDTO> movieDTOS = new ArrayList<>();
        byte[] image;

        try {
            for (String fileName : fileNames) {
                InputStream inputStream = new FileInputStream(imagePath + "//" + fileName);
                image = IOUtils.toByteArray(inputStream);

                MovieDTO movieDTO = MovieDTO.builder()
                        .MovieName(fileName)
                        .rank("1")
                        .image(image)
                        .build();
                movieDTOS.add(movieDTO);
            }
        } catch (IOException e) {
            log.warn("IOException");
        } catch (NullPointerException e) {
            log.warn("그런 폴더가 없습니다!");
        }

        return movieDTOS;
    }

    @Override
    public List<String> getFileNames() {

        List<String> fileNames = null;
        try {
            File file = new File(imagePath);
            fileNames = Stream.of(file.listFiles())
                    .map(File::getName)
                    .collect(Collectors.toList());
        } catch (NullPointerException e) {
            log.warn("그런 폴더가 없습니다!");
        }

        return fileNames;
    }
}
