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
import java.io.*;
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
    private final List<String> fileNames = getFileNames();
    private final MovieRepository movieRepository;

    @PostConstruct
    void Init() {
        Movie movie;

        for (String fileName : fileNames) {
            String path = imagePath + "//" + fileName;
            movie = new Movie(fileName, 1, path);
            movieRepository.save(movie);
        }
    }

    @Override
    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public List<MovieDTO> getTop10Movies() {
        List<MovieDTO> movieDTOS = new ArrayList<>();
        byte[] image;

        for (String fileName : fileNames) {
            image = getImage(imagePath + "//" + fileName);
            MovieDTO movieDTO = MovieDTO.builder()
                    .MovieName(fileName)
                    .rank("1")
                    .image(image)
                    .build();
            movieDTOS.add(movieDTO);
        }

        return movieDTOS;
    }

    private byte[] getImage(String imagePath) {

        byte[] image = null;

        try {
            InputStream inputStream = new FileInputStream(imagePath);
            image = IOUtils.toByteArray(inputStream);
        } catch (FileNotFoundException e) {
            log.warn("그런 파일이 없습니다!");
        } catch (IOException e) {
            log.warn("IOException");
        }

        return image;
    }

    private List<String> getFileNames() {

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
