package kr.ac.kumoh.backend.controller;

import kr.ac.kumoh.backend.Service.CommentService;
import kr.ac.kumoh.backend.Service.LikeService;
import kr.ac.kumoh.backend.domain.ResponseStatus;
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
    public ResponseStatus addMovieComment(@RequestBody CommentDTO commentDTO) {
        return commentService.addComment(commentDTO);
    }

    @PostMapping("/reviseComment")
    public ResponseStatus reviseMovieComment(@RequestBody RevisedCommentDTO revisedCommentDTO) {
        return commentService.reviseComment(revisedCommentDTO);
    }

    @PostMapping("/deleteComment")
    public ResponseStatus deleteMovieComment(@RequestBody DeleteCommentDTO deleteCommentDTO) {
        return commentService.deleteComment(deleteCommentDTO);
    }

    @PostMapping("/addLike")
    public ResponseStatus addLike(@RequestBody AddLikeDTO addLikeDTO) {
        return commentService.addLike(addLikeDTO);
    }

    @PostMapping("/removeLike")
    public ResponseStatus removeLike(@RequestBody RemoveLikeDTO removeLikeDTO) {
        return commentService.removeLike(removeLikeDTO);
    }

    @GetMapping("/{movieName}/{userId}/likes")
    public List<String> usersLike(@PathVariable("userId") String userId, @PathVariable("movieName") String movieName) {
        return likeService.getCommentsThatUserHitLikeButton(userId, movieName);
    }
}
