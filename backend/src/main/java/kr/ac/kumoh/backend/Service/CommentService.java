package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.StatusOfUser;
import kr.ac.kumoh.backend.dto.*;

import java.util.List;

public interface CommentService {
    StatusOfUser addComment(CommentDTO commentDTO);

    StatusOfUser reviseComment(RevisedCommentDTO revisedCommentDTO);

    StatusOfUser deleteComment(DeleteCommentDTO addLikeDTO);

    StatusOfUser addLike(AddLikeDTO addLikeDTO);

    List<MovieCommentDTO> getMovieComments(String movieName);

    StatusOfUser removeLike(RemoveLikeDTO addLikeDTO);
}
