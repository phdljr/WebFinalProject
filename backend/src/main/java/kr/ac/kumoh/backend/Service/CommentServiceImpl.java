package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.Comment;
import kr.ac.kumoh.backend.domain.Movie;
import kr.ac.kumoh.backend.domain.StatusOfUser;
import kr.ac.kumoh.backend.domain.User;
import kr.ac.kumoh.backend.dto.CommentDTO;
import kr.ac.kumoh.backend.dto.AddLikeDTO;
import kr.ac.kumoh.backend.dto.MovieCommentDTO;
import kr.ac.kumoh.backend.repository.CommentRepository;
import kr.ac.kumoh.backend.repository.MovieRepository;
import kr.ac.kumoh.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import static java.util.Objects.isNull;
import static kr.ac.kumoh.backend.domain.StatusOfUser.Fail;
import static kr.ac.kumoh.backend.domain.StatusOfUser.Success;


@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final CommentRepository commentRepository;

    @Override
    public StatusOfUser addComment(CommentDTO commentDTO) {

        String currDateTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String userId = commentDTO.getUserId();
        String movieName = commentDTO.getMovieName();
        String comment = commentDTO.getComment();

        User findUser = userRepository.findByUserId(userId);
        Movie findMovie = movieRepository.findByTitle(movieName);

        Comment newComment = new Comment(findMovie, findUser, comment, currDateTime);
        commentRepository.save(newComment);

        return Success;
    }

    @Override
    public StatusOfUser addLike(AddLikeDTO addLikeDTO) {
        StatusOfUser status = Fail;
        Comment findComment = null;

        try {
            findComment = commentRepository.findByCommentAndCommentDate(
                    addLikeDTO.getComment(), addLikeDTO.getCommentDate());
        } catch (Exception ignored) {
            log.warn("Doesn't match LocalDateTime Format");
        }

        if (!isNull(findComment)) {
            log.info("좋아요 +1");
            findComment.addLike();
            commentRepository.save(findComment);
            status = Success;
        }

        return status;
    }

    @Override
    public List<MovieCommentDTO> getMovieComments(String movieName) {
        List<Comment> movieComments = commentRepository.getMovieComments(movieName);

        List<MovieCommentDTO> movieCommentDTOS = new ArrayList<>();
        for (Comment movieComment : movieComments) {
            MovieCommentDTO commentDTO = MovieCommentDTO.builder()
                    .userName(movieComment.getUser().getUserId())
                    .commentDate(movieComment.getCommentDate())
                    .comment(movieComment.getComment())
                    .like(movieComment.getNumOfLike()).build();

            movieCommentDTOS.add(commentDTO);
        }

        return movieCommentDTOS;
    }
}
