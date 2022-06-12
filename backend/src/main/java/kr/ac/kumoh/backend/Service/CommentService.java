package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.Movie;
import kr.ac.kumoh.backend.domain.ResponseStatus;
import kr.ac.kumoh.backend.dto.*;

import java.util.List;

public interface CommentService {
    ResponseStatus addComment(CommentDTO commentDTO);

    ResponseStatus reviseComment(RevisedCommentDTO revisedCommentDTO);

    ResponseStatus deleteComment(DeleteCommentDTO addLikeDTO);

    ResponseStatus addLike(AddLikeDTO addLikeDTO);

    List<MovieCommentDTO> getMovieComments(Movie movie);

    ResponseStatus removeLike(RemoveLikeDTO addLikeDTO);
}
