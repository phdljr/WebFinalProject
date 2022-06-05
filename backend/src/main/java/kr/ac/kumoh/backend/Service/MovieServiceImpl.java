package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.Movie;
import kr.ac.kumoh.backend.dto.MovieDTO;
import kr.ac.kumoh.backend.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @PostConstruct
    void Init() {
//        Movie movieA = new Movie("(라이브뷰잉)Ensemble Stars!! DREAM LIVE -6th Tour “Synchronic Spheres”- Live Viewing", 5);
//        Movie movieB = new Movie("그대가 조국", 10);
//        Movie movieC = new Movie("극장판 포켓몬스터DP-기라티나와 하늘의 꽃다발 쉐이미", 4);
//        Movie movieD = new Movie("닥터 스트레인지-대혼돈의 멀티버스", 7);
//        Movie movieE = new Movie("마녀(魔女) Part2. The Other One", 6);
//        Movie movieF = new Movie("범죄도시 2", 2);
//        Movie movieG = new Movie("브로커", 3);
//        Movie movieH = new Movie("애프터 양", 8);
//        Movie movieI = new Movie("쥬라기 월드-도미니언", 1);
//        Movie movieJ = new Movie("카시오페아", 9);
//
//        addMovie(movieA);
//        addMovie(movieB);
//        addMovie(movieC);
//        addMovie(movieD);
//        addMovie(movieE);
//        addMovie(movieF);
//        addMovie(movieG);
//        addMovie(movieH);
//        addMovie(movieI);
//        addMovie(movieJ);
    }

    @Override
    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public List<MovieDTO> getTop10Movies() {
        List<MovieDTO> movieDTOS = new ArrayList<>();
        List<Movie> movies = movieRepository.findAllByOrderByRankAsc();

        for (Movie movie : movies) {
            MovieDTO movieDTO = MovieDTO.builder()
                    .MovieName(movie.getMovieName())
                    .build();
            movieDTOS.add(movieDTO);
        }

        return movieDTOS;
    }
}
