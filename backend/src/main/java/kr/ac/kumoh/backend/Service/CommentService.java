package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.StatusOfUser;
import kr.ac.kumoh.backend.dto.CommentDTO;

public interface CommentService {
    StatusOfUser addComment(CommentDTO commentDTO);
}
