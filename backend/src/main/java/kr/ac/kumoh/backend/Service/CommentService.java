package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.StatusOfUser;
import kr.ac.kumoh.backend.dto.CommentDTO;
import kr.ac.kumoh.backend.dto.AddLikeDTO;

public interface CommentService {
    StatusOfUser addComment(CommentDTO commentDTO);

    StatusOfUser addLike(AddLikeDTO addLikeDTO);
}
