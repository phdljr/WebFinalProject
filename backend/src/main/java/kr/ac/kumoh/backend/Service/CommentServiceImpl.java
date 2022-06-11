package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.*;
import kr.ac.kumoh.backend.dto.*;
import kr.ac.kumoh.backend.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static java.util.Objects.isNull;
import static kr.ac.kumoh.backend.domain.ResponseStatus.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final MovieGradeRepository movieGradeRepository;

    @Override
    public ResponseStatus addComment(CommentDTO commentDTO) {

        ResponseStatus status = Success;
        String currDateTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String userId = commentDTO.getUserId();
        String movieName = commentDTO.getMovieName();
        String comment = commentDTO.getComment();
        Double grade = commentDTO.getGrade();

        Comment findComment = commentRepository.findUserComment(movieName, userId);
        if (isNull(findComment)) {
            giveGrade(new RateMovieDTO(userId, movieName, grade));

            User findUser = userRepository.findByUserId(userId);
            Movie findMovie = movieRepository.findByTitle(movieName);

            Comment newComment = new Comment(findMovie, findUser, comment, currDateTime);
            commentRepository.save(newComment);
        } else {
            status = AlreadyExist;
        }

        return status;
    }

    public void giveGrade(RateMovieDTO rateMovieDTO) {
        String movieName = rateMovieDTO.getMovieName();
        String userId = rateMovieDTO.getUserId();
        Double grade = rateMovieDTO.getGrade();

        Movie movie = movieRepository.findByTitle(movieName);
        movie.addGrade(grade);
        movieRepository.save(movie);

        MovieGrade movieGrade = new MovieGrade(userId, movie, grade);
        movieGradeRepository.save(movieGrade);
    }

    @Override
    public ResponseStatus reviseComment(RevisedCommentDTO revisedCommentDTO) {

        String currDateTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Comment findComment = commentRepository.findUserComment(
                revisedCommentDTO.getMovieName(), revisedCommentDTO.getCommentUserId());

        findComment.reviseComment(revisedCommentDTO.getNewComment());
        findComment.setCommentDate(currDateTime);
        commentRepository.save(findComment);

        return Success;
    }

    @Override
    public ResponseStatus deleteComment(DeleteCommentDTO deleteCommentDTO) {

        String movieName = deleteCommentDTO.getMovieName();
        String userId = deleteCommentDTO.getUserId();

        Comment findComment = commentRepository.findUserComment(movieName, userId);

        List<Like> findLikes = likeRepository.findAllByComment_Id(findComment.getId());

        likeRepository.deleteAll(findLikes);
        commentRepository.delete(findComment);

        subtractGrade(new RateMovieDTO(userId, movieName, 0.0));

        return Success;
    }

    public void subtractGrade(RateMovieDTO rateMovieDTO) {
        String movieName = rateMovieDTO.getMovieName();
        String userId = rateMovieDTO.getUserId();

        // 영화 찾기
        Movie movie = movieRepository.findByTitle(movieName);

        // 영화 평점 찾기
        MovieGrade movieGrade = movieGradeRepository.findByMovieAndUserId(movie, userId).get();

        movie.subtractGrade(movieGrade.getGrade());
        movieGradeRepository.delete(movieGrade);
        movieRepository.save(movie);
    }

    @Override
    public ResponseStatus addLike(AddLikeDTO addLikeDTO) {
        ResponseStatus status = Fail;
        Comment findComment = null;

        try {
            String movieName = addLikeDTO.getMovieName();
            String commentUserId = addLikeDTO.getCommentUserId();
            System.out.println("movieName = " + movieName);
            System.out.println("commentUserId = " + commentUserId);
            findComment = commentRepository.findUserComment(movieName, commentUserId);
        } catch (Exception ignored) {
            log.warn("Doesn't match LocalDateTime Format");
        }
        System.out.println("findComment = " + findComment);
        if (!isNull(findComment)) {
            log.info("좋아요 +1");
            findComment.addLike();
            commentRepository.save(findComment);
            status = Success;

            Like like = new Like(findComment, addLikeDTO.getUserId());
            System.out.println("like = " + like);
            likeRepository.save(like);
        }

        return status;
    }

    @Override
    public ResponseStatus removeLike(RemoveLikeDTO removeLikeDTO) {

        Comment findComment = commentRepository.findUserComment(
                removeLikeDTO.getMovieName(), removeLikeDTO.getCommentUserId());

        findComment.removeLike();
        commentRepository.save(findComment);

        Like findLike = likeRepository.findByUserIdAndComment(removeLikeDTO.getUserId(), findComment); // 수정
        likeRepository.delete(findLike);

        return Success;
    }

    @Override
    public List<MovieCommentDTO> getMovieComments(Movie movie) {
        String movieName = movie.getTitle();
        List<Comment> movieComments = commentRepository.getMovieComments(movieName);

        List<MovieCommentDTO> movieCommentDTOS = new ArrayList<>();
        for (Comment movieComment : movieComments) {
            Optional<MovieGrade> movieGrade
                    = movieGradeRepository.findByMovieAndUserId(movie, movieComment.getUser().getUserId());

            double grade = movieGrade.map(MovieGrade::getGrade).orElse(-1.0);

            MovieCommentDTO commentDTO = MovieCommentDTO.builder()
                    .userId(movieComment.getUser().getUserId())
                    .commentDate(movieComment.getCommentDate())
                    .comment(movieComment.getComment())
                    .like(movieComment.getNumOfLike())
                    .grade(grade)
                    .build();

            movieCommentDTOS.add(commentDTO);
        }

        return movieCommentDTOS;
    }
}
