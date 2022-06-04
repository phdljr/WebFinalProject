package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.dto.Movie;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

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
public class ImageServiceImpl implements ImageService {

    private final String imagePath = ".//backend/src//main//resources//static//movies";

    @Override
    public List<Movie> loadMovieImages() {

        List<String> fileNames = getFileNames();
        List<Movie> movies = new ArrayList<>();
        byte[] image;

        try {
            for (String fileName : fileNames) {
                InputStream inputStream = new FileInputStream(imagePath + "//" + fileName);
                image = IOUtils.toByteArray(inputStream);

                Movie movie = Movie.builder()
                        .MovieName(fileName)
                        .rank("1")
                        .image(image)
                        .build();
                movies.add(movie);
            }
        } catch (IOException e) {
            log.warn("IOException");
        } catch (NullPointerException e) {
            log.warn("그런 폴더가 없습니다!");
        }

        return movies;
    }

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
