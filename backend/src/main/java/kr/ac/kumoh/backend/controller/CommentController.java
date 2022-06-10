package kr.ac.kumoh.backend.controller;

import kr.ac.kumoh.backend.Service.CommentService;
import kr.ac.kumoh.backend.Service.LikeService;
import kr.ac.kumoh.backend.domain.StatusOfUser;
import kr.ac.kumoh.backend.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final LikeService likeService;

    @PostMapping("/addComment")
    public StatusOfUser addMovieComment(@RequestBody CommentDTO commentDTO) {
        return commentService.addComment(commentDTO);
    }

    @PostMapping("/reviseComment")
    public StatusOfUser reviseMovieComment(@RequestBody RevisedCommentDTO revisedCommentDTO) {
        return commentService.reviseComment(revisedCommentDTO);
    }

    @PostMapping("/deleteComment")
    public StatusOfUser deleteMovieComment(@RequestBody DeleteCommentDTO deleteCommentDTO) {
        return commentService.deleteComment(deleteCommentDTO);
    }

    @PostMapping("/addLike")
    public StatusOfUser addLike(@RequestBody AddLikeDTO addLikeDTO) {
        return commentService.addLike(addLikeDTO);
    }

    @PostMapping("/removeLike")
    public StatusOfUser removeLike(@RequestBody RemoveLikeDTO removeLikeDTO) {
        return commentService.removeLike(removeLikeDTO);
    }

    @GetMapping("/{userId}/likes")
    public List<String> usersLike(@PathVariable("userId") String userId) {
        return likeService.getCommentsThatUserHitLikeButton(userId);
    }
}
