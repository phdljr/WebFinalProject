package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.Like;

import java.util.List;

public interface LikeService {

    List<String> getCommentsThatUserHitLikeButton(String userId, String movieName);
}
