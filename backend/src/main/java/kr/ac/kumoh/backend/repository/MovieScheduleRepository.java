package kr.ac.kumoh.backend.repository;

import kr.ac.kumoh.backend.domain.MovieSchedule;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MovieScheduleRepository extends JpaRepository<MovieSchedule, Long> {

    // 특정 극장 전체 상영 시간표
    @EntityGraph(value = "MovieSchedule.Movie.Theater.graph", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select ms from MovieSchedule ms where ms.theater.theaterName = :theaterName")
    List<MovieSchedule> getTheaterMovieSchedules(@Param("theaterName") String theaterName);

    // 특정 극장 특정 영화 상영 시간표
    @EntityGraph(value = "MovieSchedule.Movie.Theater.graph", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select ms from MovieSchedule ms where ms.movie.title = :movieName and ms.theater.theaterName = :theaterName")
    List<MovieSchedule> getCertainMovieSchedules(@Param("movieName") String movieName, @Param("theaterName") String theaterName);

    // 모든 극장 특정 영화 상영 시간표
    @EntityGraph(value = "MovieSchedule.Movie.Theater.graph", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select ms from MovieSchedule ms where ms.movie.title = :movieName")
    List<MovieSchedule> getAllTheaterCertainMovieSchedules(@Param("movieName") String movieName);

    // 특정 극장 특정 영화 상영 시간표 중 하나
    @EntityGraph(value = "MovieSchedule.Movie.Theater.graph", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select ms from MovieSchedule ms " +
            "where ms.screenTime = :screenTime " +
            "and ms.theater.screen = :screenName and ms.theater.theaterName = :theaterName")
    MovieSchedule getCertainMovieSchedule(@Param("theaterName") String theaterName,
                                          @Param("screenName") String screenName,
                                          @Param("screenTime") String screenTime);

    // 특정 극장 특정 영화 상영 시간표 중 하나 + 영화 이름 확인
    @EntityGraph(value = "MovieSchedule.Movie.Theater.graph", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select ms from MovieSchedule ms " +
            "where ms.screenTime = :screenTime and ms.movie.title = :movieName " +
            "and ms.theater.screen = :screenName and ms.theater.theaterName = :theaterName")
    MovieSchedule getMovieSchedule(
            @Param("movieName") String movieName,
            @Param("theaterName") String theaterName,
            @Param("screenName") String screenName,
            @Param("screenTime") String screenTime
    );
}
