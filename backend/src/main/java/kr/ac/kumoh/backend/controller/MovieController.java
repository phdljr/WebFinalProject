package kr.ac.kumoh.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@RestController
@Slf4j
public class MovieController {

    @GetMapping(value = "/movies", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getMovieImages() {

        byte[] images = null;
        String imagePath = ".//backend/src//main//resources//static//movies//1쥬라기월드.jpg";

        try {
            InputStream inputStream = new FileInputStream(imagePath);
            images = IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            log.warn("IOException");
        } catch (NullPointerException e) {
            log.warn("Null Pointer Exception");
        }
        return images;
    }
}
