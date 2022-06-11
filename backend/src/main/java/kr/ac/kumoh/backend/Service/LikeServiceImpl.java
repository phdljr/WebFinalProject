package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.Comment;
import kr.ac.kumoh.backend.domain.Like;
import kr.ac.kumoh.backend.repository.CommentRepository;
import kr.ac.kumoh.backend.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    public List<String> getCommentsThatUserHitLikeButton(String userId) {
        List<Like> userLikes = likeRepository.getUserLikes(userId);

        List<String> likes = new ArrayList<>();
        userLikes.forEach(like -> {
            Comment comment = like.getComment();
            String commentUserId = comment.getUser().getUserId();
            likes.add(commentUserId);
        });

        return likes;
    }
}
