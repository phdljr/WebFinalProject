package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.Comment;
import kr.ac.kumoh.backend.domain.Movie;
import kr.ac.kumoh.backend.domain.StatusOfUser;
import kr.ac.kumoh.backend.domain.User;
import kr.ac.kumoh.backend.dto.CommentDTO;
import kr.ac.kumoh.backend.repository.CommentRepository;
import kr.ac.kumoh.backend.repository.MovieRepository;
import kr.ac.kumoh.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import static kr.ac.kumoh.backend.domain.StatusOfUser.Success;


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

        Comment newComment = new Comment();
        newComment.setCommentDate(currDateTime);
        newComment.setUser(findUser);
        newComment.setMovie(findMovie);
        newComment.setComment(comment);

        commentRepository.save(newComment);

        return Success;
    }
}
