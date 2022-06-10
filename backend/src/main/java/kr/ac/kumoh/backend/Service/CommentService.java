package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.StatusOfUser;
import kr.ac.kumoh.backend.dto.CommentDTO;
import kr.ac.kumoh.backend.dto.AddLikeDTO;
import kr.ac.kumoh.backend.dto.MovieCommentDTO;
import kr.ac.kumoh.backend.dto.RevisedCommentDTO;

import java.util.List;

public interface CommentService {
    StatusOfUser addComment(CommentDTO commentDTO);

    StatusOfUser reviseComment(RevisedCommentDTO revisedCommentDTO);

    StatusOfUser deleteComment(AddLikeDTO addLikeDTO);

    StatusOfUser addLike(AddLikeDTO addLikeDTO);

    List<MovieCommentDTO> getMovieComments(String movieName);

    StatusOfUser removeLike(AddLikeDTO addLikeDTO);
}
